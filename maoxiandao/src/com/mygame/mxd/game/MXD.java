package com.mygame.mxd.game;

import com.badlogic.gdx.Game;
import com.mygame.mxd.menu.Logo;
import com.mygame.mxd.screens.GameScreen;

public class MXD extends Game {
	@Override
	public void create () {
		//setScreen(new GameScreen(this));
		setScreen(new Logo(this));
	}
}
