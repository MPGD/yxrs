package com.mygame.mxd.game;

import java.util.List;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;

public class AssetManagerSingleton {

	public static AssetManager manager;

	public static AssetManager getManager() {

		if (manager == null) {
			manager = new AssetManager();
		}
		return manager;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void LoadResource(List<AssetDescriptor> list) {
		manager = getManager();
		for (AssetDescriptor asset : list) {
			manager.load(asset.fileName, asset.type);
		}
	}
}
