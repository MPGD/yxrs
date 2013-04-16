package com.mygame.mxd.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.mygame.mxd.game.DataSet;

public class GhostMoveAction extends Action{
	private boolean beginFlag = false;
	private Badboy ghost = null;
	private float v;

	private void begin(){
		ghost = (Badboy) actor;
	}
	
	public void setSpeed(float v){
		this.v = v;
	}
	
	@Override
	public boolean act(float delta) {
		// TODO Auto-generated method stub
		if(!beginFlag) begin();
		if(ghost.getStatus() == ghost.STATUS_HURT)
			return false;
		if(ghost.getMoveLeft()){
			ghost.setX(ghost.getX() - ghost.getSpeed());
			if(ghost.getX() <= 0){
				ghost.setX(0);
				ghost.setMoveLeft(false);
			}
		}else{
			ghost.setX(ghost.getX() + ghost.getSpeed());
			if(ghost.getX() >= DataSet.ScreenWidth){
				ghost.setX(DataSet.ScreenWidth);
				ghost.setMoveLeft(true);
			}
		}
		return false;
	}

}