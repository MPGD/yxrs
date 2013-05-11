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
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygame.mxd.game.actor.Badboy;
import com.mygame.mxd.game.actor.XiaoMing;
import com.mygame.mxd.screens.GameScreen;

/*游戏关卡*/
public class GameLevel implements Disposable{
	private GameScreen mGameScreen;
	private GameStage mGameStage;
	private GameScene mGameScene;
	private Music bgm;
	private XiaoMing xiaoming;
	private int level;
	private Sprite sPad;
	
	public ArrayList<GameBlock> gameBlocks = new ArrayList<GameBlock>();
	
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
					setScene(new GameScene(child.getAttribute("name")));
				}else if(child.getName().equals("bgm")){
				}else if(child.getName().equals("block")){
					addBlock(new GameBlock(Float.valueOf(child.getAttribute("x1")), Float.valueOf(child.getAttribute("x2")), Float.valueOf(child.getAttribute("y"))));
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
	
	public void setBGM(Music bgm){
		this.bgm = bgm;
	}

	public void addBlock(GameBlock gameBlock){
		gameBlocks.add(gameBlock);
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public void setStartLeft(float x, float y){
		xiaoming.setRealPosition(x, y);
	}
	
	public void setStartRight(float x, float y){
		
	}
	
	public void render(float delta){
		mGameStage.getSpriteBatch().begin();
		mGameStage.getSpriteBatch().draw(mGameScene.getTexture(), 0, 0, DataSet.ScreenWidth, DataSet.ScreenHeight);
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
