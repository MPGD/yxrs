package com.mygame.mxd.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.mygame.mxd.game.DataSet;
import com.mygame.mxd.game.GameScene;
import com.mygame.mxd.game.GameStage;
import com.mygame.mxd.game.actor.Badboy;
import com.mygame.mxd.game.actor.GameAccidentDetect;
import com.mygame.mxd.game.actor.GameController;
import com.mygame.mxd.game.actor.XiaoMing;

public class GameScreen extends BaseScreen {
	private GameStage gs;
	private GameController mGameCtrl;
	private XiaoMing xiaoming;
	private GameAccidentDetect mGameAccidentDetect;

	public GameScreen(Game game) {
		super(game);
	}

	@Override
	public void show() {
		gs = new GameStage(DataSet.ScreenWidth, DataSet.ScreenHeight, true);
		GameScene gameScene = new GameScene();
		gameScene.setTexture("data/bg.jpg");
		gs.setScene(gameScene);
		xiaoming = new XiaoMing();
		gs.addActor(xiaoming);

		Badboy bd = new Badboy();
		gs.addActor(bd);

		mGameCtrl = new GameController(xiaoming);
		mGameAccidentDetect = new GameAccidentDetect(this);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		mGameAccidentDetect.detect();
		if (mGameAccidentDetect.isNeedControl())
			mGameCtrl.process();
		gs.act(delta);
		gs.update(delta);
		gs.draw();
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		gs.dispose();
	}

	public GameStage getGameStage() {
		return gs;
	}

	public XiaoMing getXiaoMing() {
		return xiaoming;
	}

}
