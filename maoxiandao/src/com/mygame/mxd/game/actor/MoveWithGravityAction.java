package com.mygame.mxd.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;

public class MoveWithGravityAction extends Action{

	private float gravity;
	private float v;
	
	public void setGravity(float gravity){
		this.gravity = gravity;
	}
	
	public void setV(float v){
		this.v = v;
	}
	
	@Override
	public boolean act(float delta) {
		// TODO Auto-generated method stub
		float t = delta;
		float deltaS = v * t - gravity * t * t /2;
		v = v - gravity * t;
		actor.setY(actor.getY() + deltaS);
		
		if(actor.getY() < 100) {
			actor.setY(100);
			return true;
		}
		return false;
	}

}
