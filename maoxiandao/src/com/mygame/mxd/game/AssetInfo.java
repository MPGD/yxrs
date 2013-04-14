package com.mygame.mxd.game;

public class AssetInfo<T> {

	private String filePath;

	private Class<T> type;

	public AssetInfo(String filePath, Class<T> type) {
		this.filePath = filePath;
		this.type = type;
	}

	public String getFilePath() {
		return filePath;
	}

	public Class<T> getType() {
		return type;
	}
}
