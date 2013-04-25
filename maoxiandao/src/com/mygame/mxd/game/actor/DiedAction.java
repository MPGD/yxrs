package com.mygame.mxd.game.actor;

import com.badlogic.gdx.scenes.scene2d.Action;

public class DiedAction extends Action{
	
	private float time = 0;
	@Override
	public boolean act(float delta) {
		// TODO Auto-generated method stub
		GameActor gActor = (GameActor)actor;
		gActor.setTransparency(gActor.getTransparency() - 0.4f * delta);
		time += delta;
		if(time > 2) return true;
		return false;
	}

}
