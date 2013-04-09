package com.mygame.mxd.game.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygame.mxd.game.GameStage;

public abstract class GameActor extends Actor{
	public static int STATUS_IDLE = 0;
	public static int STATUS_MOVE = -1;
	public static int STATUS_JUMP = -2;
	public static int STATUS_ATTACK = -3;
	public static int STATUS_HURT = -4;
	public static int STATUS_MAGIC = -5;
	
	protected boolean moveLeft = false;
	protected Animation animationMove = null;
	protected Animation animationAttack = null;
	protected Animation animationIdle = null;
	protected Animation animationHurt = null;
	
	private Animation animation = null;
	
	private int status = 0;
	
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		
		float delta = ((GameStage) getStage()).getTime();
		if(status == STATUS_MOVE){
			animation = animationMove;
		}else if(status == STATUS_ATTACK){
			animation = animationAttack;
		}else{
			animation = animationIdle;
		}
		Sprite sprite = (Sprite)animation.getKeyFrame(delta, true);
		sprite.setPosition(getX(), getY());
		
		if(!moveLeft) sprite.setScale(Math.abs(sprite.getScaleX()) * -1, sprite.getScaleY());
		else sprite.setScale(Math.abs(sprite.getScaleX()) * 1, sprite.getScaleY());

		sprite.draw(batch, parentAlpha);
	}
	
	abstract public boolean checkPostion();
	
	public void setAnimation(Animation ani){
		animation = ani;
	}
	
	public void setStatus(int status){
		this.status = status;
	}
}
 