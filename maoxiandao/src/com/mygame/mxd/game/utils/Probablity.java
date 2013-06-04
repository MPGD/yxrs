package com.mygame.mxd.game.utils;

import com.badlogic.gdx.Gdx;

public class Probablity {
	public static boolean checkEvent(float f){
		boolean ret = false;
		
		if(f < 0 || f > 1) Gdx.app.log("xujihao", "f is not between 0 and 1");
		float r = (float)Math.random();
		Gdx.app.log("xujihao", "f is " + f + " r is " + r);
		if(r < f){
			ret = true;
		}
		return ret;
	}
}
