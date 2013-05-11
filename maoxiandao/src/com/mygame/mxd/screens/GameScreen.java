package com.mygame.mxd.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygame.mxd.game.DataSet;
import com.mygame.mxd.game.GameLevel;
import com.mygame.mxd.game.GameScene;
import com.mygame.mxd.game.GameStage;
import com.mygame.mxd.game.GameUtilStage;
import com.mygame.mxd.game.actor.Badboy;
import com.mygame.mxd.game.actor.GameAccidentDetect;
import com.mygame.mxd.game.actor.XiaoMing;
import com.mygame.mxd.game.controller.GameController;
import com.mygame.mxd.game.gameutils.GamePad;
import com.mygame.mxd.game.utils.LevelLoader;

public class GameScreen extends BaseScreen {
	private GameController mGameCtrl;
	private XiaoMing xiaoming;
	private GameAccidentDetect mGameAccidentDetect;
	private GameLevel mGameLevel;
	private GameUtilStage mUtilStage;
	private GamePad mGamePad;
	public GameScreen(Game game) {
		super(game);
	}

	@Override
	public void show() {
		xiaoming = new XiaoMing();
		mUtilStage = new GameUtilStage(DataSet.ScreenWidth, DataSet.ScreenHeight, true);
		mGamePad = new GamePad();

		mUtilStage.addActor(mGamePad);
		mGameLevel = LevelLoader.load(this, 0);
		mGameCtrl = new GameController(xiaoming);
		mGameCtrl.setGamePad(mGamePad);
		mGameAccidentDetect = new GameAccidentDetect(this);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		mGameAccidentDetect.detect();
		if (mGameAccidentDetect.isNeedControl())
			mGameCtrl.process();
		mGameLevel.render(delta);
		mUtilStage.act(delta);
		mUtilStage.draw();
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		mGameLevel.dispose();
	}


	public XiaoMing getXiaoMing() {
		return xiaoming;
	}

	public GameLevel getGameLevel(){
		return mGameLevel;
	}
	
	
}
