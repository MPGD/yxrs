package com.mygame.mxd.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class GameScene implements Disposable{
	private Texture background = null;
	
	
	public void setTexture(String path){
		background = new Texture(path);
	}
	
	public void dispose(){
		if(background != null) background.dispose();
	}

	public Texture getTexture(){
		return background;
	} 
	
}
