package com.mygame.mxd.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;
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
	private ArrayList<Block> blocks = new ArrayList<Block>();
	
	public GameLevel(GameScreen gameScreen){
		mGameScreen = gameScreen;
		mGameStage = new GameStage(DataSet.ScreenWidth, DataSet.ScreenHeight, true);
		xiaoming = gameScreen.getXiaoMing();
		mGameStage.addActor(xiaoming);
	}
	
	public GameStage getGameStage(){
		return mGameStage;
	}
	
	public void setScene(GameScene scene){
		mGameScene = scene;
	}
	
	public void setBGM(Music bgm){
		this.bgm = bgm;
	}

	public void addBlock(Block block){
		blocks.add(block);
	}
	
	public void setLevel(int level){
		this.level = level;
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
