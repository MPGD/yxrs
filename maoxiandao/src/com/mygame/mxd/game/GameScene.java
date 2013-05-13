package com.mygame.mxd.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class GameScene implements Disposable{
	private Texture background = null;
	
	public GameScene(String path){
		background = AssetManagerSingleton.manager.get(path);
	}
	
	public void setTexture(String path){
		background = AssetManagerSingleton.manager.get(path);
	}
	
	public void dispose(){
		if(background != null) background.dispose();
	}

	public Texture getTexture(){
		return background;
	} 
	
}
