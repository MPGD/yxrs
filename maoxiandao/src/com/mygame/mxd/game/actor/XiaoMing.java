package com.mygame.mxd.game.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.mygame.mxd.game.DataSet;

public class XiaoMing extends GameActor{
	
	public static String XIAOMING_IMG_SRC = "data/xiaoming.png";
	private boolean isJump = false;
	private float scaleX = 1f;
	private float scaleY = 1f;
	private int size = 81;
	private boolean controllable = true;
	
	public XiaoMing(){
		Texture temp = new Texture(XIAOMING_IMG_SRC);

		// 加载关键帧动画
		TextureRegion[] move = new TextureRegion(temp).split(size, size)[0];
		TextureRegion[] idle = new TextureRegion(temp).split(size, size)[1];
		TextureRegion[] hurt = new TextureRegion(temp).split(size, size)[3];
		TextureRegion[] attack = new TextureRegion(temp).split(size, size)[4];
		
		Sprite [] _move = new Sprite[4];
		Sprite [] _idle = new Sprite[4];
		Sprite [] _hurt = new Sprite[4];
		Sprite [] _attack = new Sprite[4];
		
		for(int i = 0; i < 4; i++){
			_move[i] = new Sprite(move[i]);
			_idle[i] = new Sprite(idle[i]);
			_hurt[i] = new Sprite(hurt[i]);
			_attack[i] = new Sprite(attack[i]);
			
			_move[i].setScale(scaleX, scaleY);
			_idle[i].setScale(scaleX, scaleY);
			_hurt[i].setScale(scaleX, scaleY);
			_attack[i].setScale(scaleX, scaleY);
		}

		
		animationMove = new Animation(0.1f, _move[0], _move[1], _move[2],
				_move[3]);
		animationIdle = new Animation(0.1f, _idle[0], _idle[1], _idle[2],
				_idle[3]);
		animationHurt = new Animation(0.1f, _hurt[0], _hurt[1], _hurt[2],
				_hurt[3]);
		animationAttack = new Animation(0.15f, _attack[0], _attack[1], _attack[2],
				_attack[3]);
		
		setY(100);
		setSize(size, size);
		setScale(scaleX, scaleY);
		setAnimation(animationIdle);
		setName("xiaoming");
		setPadding(17, 9, 17, 20);
	}

	@Override
	public boolean checkPostion() {
		// TODO Auto-generated method stub
		return false;
	}

	

	
	@Override
	public void idle() {
		// TODO Auto-generated method stub
		super.idle();
		controllable = true;
	}

	public void move(boolean left){
		if(left){
			moveLeft = true;
			setX(getX() - DataSet.MoveSpeed);
		}else {
			moveLeft = false;
			setX(getX() + DataSet.MoveSpeed);
		}
		setStatus(STATUS_MOVE);
	}
	
	public void jump(){
		if(isJump) return;
		
		MoveWithGravityAction jumpAction = new MoveWithGravityAction();
		jumpAction.setV(DataSet.JumpV);
		jumpAction.setGravity(DataSet.Gravity);
		
		RunnableAction resetJump = Actions.run(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				isJump = false;
			}
		});
		addAction(Actions.sequence(jumpAction, resetJump));
		setStatus(STATUS_JUMP);
		isJump = true;
	}
	
	public void attack(){
		setStatus(STATUS_ATTACK);
	}

	@Override
	public boolean isHurt() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void setControllable(boolean ctrl){
		controllable = ctrl;
	}
	
	public boolean getControllable(){
		return controllable;
	}
}
