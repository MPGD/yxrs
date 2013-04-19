package com.mygame.mxd.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.mygame.mxd.game.DataSet;
import com.mygame.mxd.game.GameLevel;
import com.mygame.mxd.game.GameScene;
import com.mygame.mxd.game.GameStage;
import com.mygame.mxd.game.actor.Badboy;
import com.mygame.mxd.game.actor.GameAccidentDetect;
import com.mygame.mxd.game.actor.GameController;
import com.mygame.mxd.game.actor.XiaoMing;
import com.mygame.mxd.game.utils.LevelLoader;

public class GameScreen extends BaseScreen {
	private GameController mGameCtrl;
	private XiaoMing xiaoming;
	private GameAccidentDetect mGameAccidentDetect;
	private GameLevel mGameLevel;
	public GameScreen(Game game) {
		super(game);
	}

	@Override
	public void show() {
		xiaoming = new XiaoMing();
		mGameLevel = LevelLoader.load(this, "data/level/level1.conf");
		mGameCtrl = new GameController(xiaoming);
		mGameAccidentDetect = new GameAccidentDetect(this);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		mGameAccidentDetect.detect();
		if (mGameAccidentDetect.isNeedControl())
			mGameCtrl.process();
		mGameLevel.render(delta);
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
