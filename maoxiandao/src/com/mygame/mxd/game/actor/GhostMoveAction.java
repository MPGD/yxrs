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
			ghost.setRealX(ghost.getRealX() - ghost.getSpeed());
			if(ghost.getRealX() <= 0){
				ghost.setRealX(0);
				ghost.setMoveLeft(false);
			}
		}else{
			ghost.setRealX(ghost.getRealX() + ghost.getSpeed());
			if(ghost.getRealX() >= DataSet.ScreenWidth - ghost.getRealWidth()){
				ghost.setRealX(DataSet.ScreenWidth - ghost.getRealWidth());
				ghost.setMoveLeft(true);
			}
		}
		return false;
	}

}