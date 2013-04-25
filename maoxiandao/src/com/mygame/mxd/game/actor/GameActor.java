package com.mygame.mxd.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygame.mxd.game.Debug;
import com.mygame.mxd.game.GameLevel;
import com.mygame.mxd.game.GameStage;

public abstract class GameActor extends Actor{
	public static int STATUS_IDLE = 0;
	public static int STATUS_MOVE = -1;
	public static int STATUS_JUMP = -2;
	public static int STATUS_ATTACK = -3;
	public static int STATUS_HURT = -4;
	public static int STATUS_MAGIC = -5;
	public static int STATUS_DIE = -6;
	
	public GameLevel mGameLevel;
	protected boolean moveLeft = false;
	protected Animation animationMove = null;
	protected Animation animationAttack = null;
	protected Animation animationIdle = null;
	protected Animation animationHurt = null;
	
	private Animation animation = null;
	
	private int status = 0;
	
	/*随便图片每个元素有81x81，但是真实的有效的区域只有很小的一部分，引入padding*/
	public float paddingTop = 0;
	public float paddingBottom = 0;
	public float paddingLeft = 0;
	public float paddingRight = 0;
	private float aniTime = 0;
	private float transparency = 1;
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		if(checkPostion()){
			checkPostionCallBack();
		}
		aniTime += ((GameStage) getStage()).getDelta();
		float time = aniTime;
		boolean aniLoop = true;
		if(status == STATUS_MOVE){
			animation = animationMove;
		}else if(status == STATUS_ATTACK){
			animation = animationAttack;
			aniLoop = false;
		}else if(status == STATUS_HURT){
			animation = animationHurt;
		}else if(status == STATUS_DIE){
			animation = animationIdle;
		}else{
			animation = animationIdle;
		}
		Sprite sprite = (Sprite)animation.getKeyFrame(time, aniLoop);
		sprite.setColor(1, 1, 1, transparency);
		sprite.setPosition(getX(), getY());
		if(!moveLeft) sprite.setScale(Math.abs(sprite.getScaleX()) * -1, sprite.getScaleY());
		else sprite.setScale(Math.abs(sprite.getScaleX()) * 1, sprite.getScaleY());

		sprite.draw(batch, parentAlpha);
	}
	
	abstract public void checkPostionCallBack();
	abstract public boolean checkPostion();
	/*有时候砍怪血量很少的时候，怪物并不会受伤，因此会继续前进*/
	abstract public boolean isHurt();

	public void setPadding(float paddingTop, float paddingBottom, float paddingLeft, float paddingRight){
		this.paddingTop = paddingTop;
		this.paddingBottom = paddingBottom;
		this.paddingLeft = paddingLeft;
		this.paddingRight = paddingRight;
	}
	
	public void idle(){
		setStatus(STATUS_IDLE);
	}
	
	public void setAnimation(Animation ani){
		animation = ani;
	}
	
	public void setMoveLeft(boolean moveLeft){
		this.moveLeft = moveLeft;
	}
	
	public boolean getMoveLeft(){
		return moveLeft;
	}
	
	public void setStatus(int status){
		this.status = status;
	}
	
	public int getStatus(){
		return status;
	}
	
	public void resetAniTime(){
		aniTime = 0;
	}
	
	public float getAniTime(){
		return aniTime;
	}
	
	public float getRealX(){
		float ret;
		if(moveLeft){
			ret = getX() + paddingLeft;
		}else{
			ret = getX() + paddingRight;
		}
		return ret;
	}
	
	public float getRealY(){
		float ret;
		ret = getY() + paddingBottom;
		return ret;
	}
	
	public void setRealX(float x){
		if(moveLeft){
			setX(x- paddingLeft);
		}else{
			setX(x- paddingRight);
		}
	}
	
	public void setRealY(float y){
		setY(y- paddingBottom);
	}
	
	public void setRealPosition(float x, float y){
		setRealX(x);
		setRealY(y);
	}
	
	public float getRealWidth(){
		return (getWidth() - paddingLeft - paddingRight) * getScaleX();
	}
	
	public float getRealHeight(){
		return (getHeight() - paddingTop - paddingBottom) * getScaleY();
	}
	
	public void setGameLevel(GameLevel gameLevel){
		this.mGameLevel = gameLevel;
	}
	
	public void setTransparency(float trans){
		transparency = trans;
	}
	
	public float getTransparency(){
		return transparency;
	}
}
 