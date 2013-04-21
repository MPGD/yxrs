package com.mygame.mxd.menu;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.mygame.mxd.game.Asset;
import com.mygame.mxd.game.AssetManagerSingleton;
import com.mygame.mxd.game.DataSet;
import com.mygame.mxd.screens.BaseScreen;

public class Progress extends BaseScreen {

	private MenuStage stage;
	private ProgressBar bar;
	@SuppressWarnings("rawtypes")
	private ArrayList<Asset> AssetList;
	private String className;
	private Screen screen;
	private int skip = 0;

	public Progress(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("rawtypes")
	public Progress(Game game, ArrayList<Asset> AssetList, String className) {
		super(game);
		this.AssetList = AssetList;
		this.className = className;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub.
		stage = new MenuStage(DataSet.ScreenWidth, DataSet.ScreenHeight, true);
		bar = new ProgressBar(0, 0);
		stage.addActor(bar);
		AssetManagerSingleton.LoadResource(AssetList);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.draw();
		Gdx.app.debug("xue", "XXXXXXXXXXXXXXXXXXXXXXXXX:" + AssetManagerSingleton.manager.getProgress() * 100);
		if (AssetManagerSingleton.manager.update()) {
			skip++;
			Gdx.app.debug("xue", "ZZZZZZZZZZZZZZZZZZZZZZZ:" + AssetManagerSingleton.manager.getProgress() * 100);

			if (screen == null) {
				try {
					screen = (Screen) Class.forName(className).getConstructor(Game.class).newInstance(game);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (skip == 3) {
				game.setScreen(screen);
			}
		}
		bar.setProgress(AssetManagerSingleton.manager.getProgress() * 100);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		this.dispose();
	}
}
