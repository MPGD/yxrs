
package com.mygame.mxd.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.mygame.mxd.game.DataSet;
import com.mygame.mxd.game.GameScene;
import com.mygame.mxd.game.GameStage;
import com.mygame.mxd.game.actor.Badboy;
import com.mygame.mxd.game.actor.XiaoMing;
import com.mygame.mxd.game.controller.GameController;

public class GameScreen extends BaseScreen {
	GameStage gs;
	GameController mGameCtrl;
	public GameScreen (Game game) {
		super(game);
	}

	@Override
	public void show () {
		gs = new GameStage(DataSet.ScreenWidth, DataSet.ScreenHeight, true);
		GameScene gameScene = new GameScene();
		gameScene.setTexture("data/bg.jpg");
		gs.setScene(gameScene);
		XiaoMing xiaoming = new XiaoMing();
		gs.addActor(xiaoming);
		
		Badboy bd = new Badboy();
		gs.addActor(bd);
		
		mGameCtrl = new GameController(xiaoming);
		
	}

	@Override
	public void render (float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);  
        mGameCtrl.process();
        gs.act(delta);  
        gs.update(delta);
		gs.draw();
	}

	@Override
	public void hide () {

	}
	
	@Override
	public void dispose () {
		gs.dispose();
	}
}
