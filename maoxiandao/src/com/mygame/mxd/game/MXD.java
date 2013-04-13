package com.mygame.mxd.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygame.mxd.menu.Logo;
import com.mygame.mxd.screens.GameScreen;

public class MXD extends Game {
	@Override
	public void create() {

		DataSet.ScreenWidth = Gdx.graphics.getWidth();
		DataSet.ScreenHeight = Gdx.graphics.getHeight();

		// setScreen(new GameScreen(this));
		setScreen(new Logo(this));
	}
}
