package com.mygame.mxd.game.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygame.mxd.game.DataSet;
import com.mygame.mxd.game.MXD;

public class GameDesktop {
	public static void main (String[] argv) {
		new LwjglApplication(new MXD(), "mxd", DataSet.ScreenWidth, DataSet.ScreenHeight, true);

		// After creating the Application instance we can set the log level to
		// show the output of calls to Gdx.app.debug
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}
}
