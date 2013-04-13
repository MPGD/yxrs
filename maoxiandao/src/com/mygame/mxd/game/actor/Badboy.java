package com.mygame.mxd.game.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Badboy extends GameActor{
	
	public static String BADBOY_IMG_SRC = "data/ghost01.png";
	private float scaleX = 1f;
	private float scaleY = 1f;
	private float speed = 2;
	private int size = 81;
	
	@Override
	public boolean checkPostion() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Badboy(){
		Texture temp = new Texture(BADBOY_IMG_SRC);

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
		setX(500);
		setSize(size, size);
		setScale(scaleX, scaleY);
		setAnimation(animationIdle);
		moveLeft = true;
		
		GhostMoveAction gMoveAction = new  GhostMoveAction();
		gMoveAction.setSpeed(3);
		
		addAction(gMoveAction);
		setName("guaiwu");
		setPadding(27, 13, 20, 20);
	}

	public float getSpeed(){
		return speed;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		moveLeft = !moveLeft;
		super.draw(batch, parentAlpha);
		moveLeft = !moveLeft;
	}

	@Override
	public boolean isHurt() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
}
