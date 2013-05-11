package com.mygame.mxd.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.mygame.mxd.game.utils.CollisionDetect;

public class MoveWithGravityAction extends Action{

	private float gravity;
	private float v;
	private float lastPosY = 0;
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
		GameActor gActor = (GameActor)actor;
		v = v - gravity * t;
		gActor.setRealY(gActor.getRealY() + deltaS);
		if(deltaS < 0){
			for(int i = 0; i < gActor.mGameLevel.gameBlocks.size(); i++){
				if(CollisionDetect.detect(gActor, gActor.mGameLevel.gameBlocks.get(i))){
					if(lastPosY > gActor.mGameLevel.gameBlocks.get(i).y){
						gActor.setRealY(gActor.mGameLevel.gameBlocks.get(i).y);
						return true;
					}
				}
			}
			
		}
		lastPosY = gActor.getRealY();
		return false;
	}

}
