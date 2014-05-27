package com.mygame.mxd.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class GameScene implements Disposable{
	private Texture background_layer1 = null;
	private Texture background_layer2 = null;
	public GameScene(String front, String back){
		background_layer1 = AssetManagerSingleton.manager.get(front);
		background_layer2 = AssetManagerSingleton.manager.get(back);
	}
	
	public void setTexture(String front, String back){
		background_layer1 = AssetManagerSingleton.manager.get(front);
		background_layer2 = AssetManagerSingleton.manager.get(back);
	}
	
	public void dispose(){
		if(background_layer1 != null) background_layer1.dispose();
		if(background_layer2 != null) background_layer2.dispose();
	}

	public Texture getBackgroundLayer1(){
		return background_layer1;
	} 
	
	public Texture getBackgroundLayer2(){
		return background_layer2;
	} 
}
