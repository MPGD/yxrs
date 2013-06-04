package com.mygame.mxd.game.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class NumberActor extends Actor{
	public static int FONT_TYPE_1 = 1;
	public static int FONT_TYPE_2 = 2;
	public static int FONT_TYPE_3 = 2;
	private int n;
	private TextureRegion [] tr;
	private int type;
	
	public void setNumber(int n){
		this.n = n;
	}
	
	public void setType(int type){
		this.type = type;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

	}
	
	
	
}
