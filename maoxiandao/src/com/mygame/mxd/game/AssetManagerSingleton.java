package com.mygame.mxd.game;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetManager;

public abstract class AssetManagerSingleton {

	public static AssetManager manager;

	public static AssetManager getManager() {

		if (manager == null) {
			manager = new AssetManager();
		}
		return manager;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void LoadRes(ArrayList<AssetInfo> list) {
		for (int i = 0; i < list.size(); i++) {
			manager.load(list.get(i).getFilePath(), list.get(i).getType());
		}
	}

	public boolean checkRes(String fileName) {
		return manager.isLoaded(fileName);
	}

	public float getProgress() {
		return manager.getProgress();
	}

	public int getQueued() {
		return manager.getQueuedAssets();
	}

	public int getLoaded() {
		return manager.getLoadedAssets();
	}

	public static void dispose() {
		manager.dispose();
	}
}
