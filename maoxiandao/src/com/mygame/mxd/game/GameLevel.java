package com.mygame.mxd.game;

import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygame.mxd.game.actor.Badboy;
import com.mygame.mxd.game.actor.XiaoMing;
import com.mygame.mxd.game.map.Ladder;
import com.mygame.mxd.game.map.Land;
import com.mygame.mxd.game.map.Rope;
import com.mygame.mxd.game.map.Slope;
import com.mygame.mxd.game.map.Wall;
import com.mygame.mxd.screens.GameScreen;

/*游戏关卡*/
public class GameLevel implements Disposable{
	private GameScreen mGameScreen;
	private GameStage mGameStage;
	private GameScene mGameScene;
	private Music bgm;
	public XiaoMing xiaoming;
	private int level;
	private Sprite sPad;
	
	public ArrayList<Land> lands = new ArrayList<Land>();
	public ArrayList<Rope> ropes = new ArrayList<Rope>();
	public ArrayList<Ladder> ladders = new ArrayList<Ladder>();
	public ArrayList<Slope> slopes = new ArrayList<Slope>();
	public ArrayList<Wall> walls = new ArrayList<Wall>();
	
	public GameLevel(GameScreen gameScreen){
		mGameScreen = gameScreen;
		mGameStage = new GameStage(DataSet.ScreenWidth, DataSet.ScreenHeight, true);
		xiaoming = gameScreen.getXiaoMing();
		xiaoming.setGameLevel(this);
		mGameStage.addActor(xiaoming);		
	}
	
	public boolean loadPrevLevel(){
		boolean ret = false;
		if(level > 0){
			loadLevel(--level);
			ret = true;
		}
		return ret;
	}
	
	public boolean loadNextLevel(){
		boolean ret = false;
		if(level < 2){
			loadLevel(++level);	
			ret = true;
		}
		return ret;
	}
	
	public void loadLevel(int level){
		mGameStage.clear();
		mGameStage.addActor(xiaoming);
		FileHandle mFileHandle;
		try {
			mFileHandle = Gdx.files.internal("data/level/level.xml");
			XmlReader xmlReader = new XmlReader();
			Element element;
			element = xmlReader.parse(mFileHandle);
			element = element.getChild(level);
			setLevel(Integer.valueOf(element.getAttribute("level")));
			for(int i = 0; i < element.getChildCount(); i++){
				Element child = element.getChild(i);
				if(child.getName().equals("background")){
					setScene(new GameScene(child.getAttribute("front"), child.getAttribute("back")));
				}else if(child.getName().equals("bgm")){
				}else if(child.getName().equals("land")){
					addLand(new Land(Float.valueOf(child.getAttribute("x1")), Float.valueOf(child.getAttribute("x2")), Float.valueOf(child.getAttribute("y"))));
				}else if(child.getName().equals("enemy")){
					Badboy ghost = new Badboy();
					ghost.setPosition(Float.valueOf(child.getAttribute("x")), Float.valueOf(child.getAttribute("y")));
					getGameStage().addActor(ghost);
					ghost.setGameLevel(this);
				}else if(child.getName().equals("startleft")){
					setStartLeft(Float.valueOf(child.getAttribute("x")), Float.valueOf(child.getAttribute("y")));
				}else if(child.getName().equals("startright")){
					setStartRight(Float.valueOf(child.getAttribute("x")), Float.valueOf(child.getAttribute("y")));
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public GameStage getGameStage(){
		return mGameStage;
	}
	
	public GameScreen getGameScreen(){
		return  mGameScreen;
	}
	public void setScene(GameScene scene){
		mGameScene = scene;
	}
	public GameScene getScene(){
		return mGameScene;
	}
	
	public void setBGM(Music bgm){
		this.bgm = bgm;
	}

	public void addLand(Land land){
		lands.add(land);
	}
	public void addRope(Rope rope){
		ropes.add(rope);
	}
	public void addLadder(Ladder ladder){
		ladders.add(ladder);
	}
	public void addWall(Wall wall){
		walls.add(wall);
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public void setStartLeft(float x, float y){
		xiaoming.setRealPosition(x, y);
	}
	
	public void setStartRight(float x, float y){
		
	}
	Vector3 lerpTarget = new Vector3();
	public void render(float delta){
		mGameStage.getCamera().position.lerp(lerpTarget.set(xiaoming.getRealX(), xiaoming.getRealY(), 0), 2f * delta);
		if(mGameStage.getCamera().position.x < DataSet.ScreenWidth / 2){
			mGameStage.getCamera().position.x = DataSet.ScreenWidth / 2;
		}else if(mGameStage.getCamera().position.x > mGameScene.getBackgroundLayer1().getWidth() - DataSet.ScreenWidth / 2){
			mGameStage.getCamera().position.x = mGameScene.getBackgroundLayer1().getWidth() - DataSet.ScreenWidth / 2;
		}
		
		if(mGameStage.getCamera().position.y < DataSet.ScreenHeight / 2){
			mGameStage.getCamera().position.y = DataSet.ScreenHeight / 2;
		}else if(mGameStage.getCamera().position.y > mGameScene.getBackgroundLayer1().getHeight() - DataSet.ScreenHeight / 2){
			mGameStage.getCamera().position.y = mGameScene.getBackgroundLayer1().getHeight() - DataSet.ScreenHeight / 2;
		}
		float layerWidth1 = mGameScene.getBackgroundLayer1().getWidth();
		float layerWidth2 = mGameScene.getBackgroundLayer2().getWidth();
		float halfScreenWidth = DataSet.ScreenWidth / 2;
		float cameraOffsetX = mGameStage.getCamera().position.x - DataSet.ScreenWidth / 2;
		float layerOffsetX = cameraOffsetX * (layerWidth1 - layerWidth2) / (layerWidth1 - halfScreenWidth * 2);
		float layer2StartPosY = Math.max(0, DataSet.ScreenHeight - mGameScene.getBackgroundLayer2().getHeight());
		float layerHeight1 = mGameScene.getBackgroundLayer1().getHeight();
		float layerHeight2 = mGameScene.getBackgroundLayer2().getHeight();
		float halfScreenHeight = DataSet.ScreenHeight / 2;
		float cameraOffsetY = mGameStage.getCamera().position.y - DataSet.ScreenHeight / 2;
		float layerOffsetY = layer2StartPosY + cameraOffsetY * (layerHeight1 - layerHeight2 - layer2StartPosY) / (layerHeight1 - halfScreenHeight * 2);
		mGameStage.getSpriteBatch().begin();
		mGameStage.getSpriteBatch().draw(mGameScene.getBackgroundLayer2(), layerOffsetX, layerOffsetY);
		mGameStage.getSpriteBatch().draw(mGameScene.getBackgroundLayer1(), 0, 0);
		mGameStage.getSpriteBatch().end();
		mGameStage.act(delta);
		mGameStage.update(delta);
		mGameStage.draw();
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		bgm.dispose();
		mGameScene.dispose();
		mGameStage.dispose();
	}
	
}
