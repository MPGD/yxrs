package com.mygame.mxd.game.actor;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.mygame.mxd.game.GameBlock;
import com.mygame.mxd.game.GameStage;
import com.mygame.mxd.game.utils.CollisionDetect;

public class ItemAction extends Action{
	private float gravity = 1500;
	private float v = 400;
	private float lastPosY = 0;
	
	@Override
	public boolean act(float delta) {
		// TODO Auto-generated method stub
		float t = delta;
		float deltaS = v * t - gravity * t * t /2;
		Item item = (Item)actor;
		v = v - gravity * t;
		item.setY(item.getY() + deltaS);
		actor.rotate(10 * delta);
		if(deltaS < 0){
			for(int i = 0; i < item.mGameLevel.gameBlocks.size(); i++){
				if(CollisionDetect.detect(item, item.mGameLevel.gameBlocks.get(i))){
					if(lastPosY > item.mGameLevel.gameBlocks.get(i).y){
						item.setY(item.mGameLevel.gameBlocks.get(i).y);
						return true;
					}
				}
			}
			
		}
		lastPosY = item.getY();
		return false;
	}

}
