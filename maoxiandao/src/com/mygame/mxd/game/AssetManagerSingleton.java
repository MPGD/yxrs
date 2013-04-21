package com.mygame.mxd.game;

import java.util.List;

import com.badlogic.gdx.assets.AssetManager;

public class AssetManagerSingleton {

	public static AssetManager manager;

	public static AssetManager getManager() {

		if (manager == null) {
			manager = new AssetManager();
		}
		return manager;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void LoadResource(List<Asset> list) {
		manager = getManager();
		for (Asset asset : list) {
			manager.load(asset.getFile(), asset.getType());
		}
	}
}
