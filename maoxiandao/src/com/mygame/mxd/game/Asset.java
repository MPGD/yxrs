package com.mygame.mxd.game;

public class Asset<T> {

	private String file;

	private Class<T> type;

	public Asset(String file, Class<T> type) {
		this.file = file;
		this.type = type;
	}

	public String getFile() {
		return file;
	}

	public Class<T> getType() {
		return type;
	}
}
