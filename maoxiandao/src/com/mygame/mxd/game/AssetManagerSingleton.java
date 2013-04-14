package com.mygame.mxd.game;

import com.badlogic.gdx.assets.AssetManager;

public abstract class AssetManagerSingleton {

	public static AssetManager manager;

	public static AssetManager getManager() {

		if (manager == null) {
			manager = new AssetManager();
		}
		return manager;
	}

}
