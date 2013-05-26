package com.mygame.mxd.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygame.mxd.menu.Logo;
import com.mygame.mxd.screens.GameScreen;

public class MXD extends Game {
	@Override
	public void create() {

		// 使用于权重
		DataSet.SCREEN_WIDGHT = Gdx.graphics.getWidth();
		DataSet.SCREEN_HEIGHT = Gdx.graphics.getHeight();

		// setScreen(new GameScreen(this));
		setScreen(new Logo(this));
	}
}
