package com.mygame.mxd.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
/** 保持受伤状态持续一秒*/
public class HurtAction extends Action{
	private boolean beginFlag = false;
	private GameActor gActor;
	private float delta;
	private float time;
	private boolean hurtLeft;
	//受伤有向左和向右
	public HurtAction(boolean left){
		hurtLeft = left;
	}
	@Override
	public boolean act(float delta) {
		// TODO Auto-generated method stub
		if(! beginFlag){
			gActor =  (GameActor) actor;		
			this.delta = delta;
			this.time = delta;
			beginFlag = true;
		}
		boolean moveLeft = gActor.getMoveLeft();
		gActor.setStatus(GameActor.STATUS_HURT);
		if(hurtLeft){
			gActor.setX(gActor.getX() - 3);
		}else{
			gActor.setX(gActor.getX() + 3);
		}
		
		if(time - delta > 0.5) {
			gActor.idle();
			return true;
		}
		time += delta;
		return false;
	}

}
