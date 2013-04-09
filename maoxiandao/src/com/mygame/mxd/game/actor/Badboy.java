package com.mygame.mxd.game.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Badboy extends GameActor{
	
	public static String BADBOY_IMG_SRC = "data/ghost01.png";
	private float scale = 1f;
	@Override
	public boolean checkPostion() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Badboy(){
		Texture temp = new Texture(BADBOY_IMG_SRC);

		// 加载关键帧动画
		TextureRegion[] move = new TextureRegion(temp).split(81, 81)[0];
		TextureRegion[] idle = new TextureRegion(temp).split(81, 81)[1];
		TextureRegion[] hurt = new TextureRegion(temp).split(81, 81)[3];
		TextureRegion[] attack = new TextureRegion(temp).split(81, 81)[4];
		
		Sprite [] _move = new Sprite[4];
		Sprite [] _idle = new Sprite[4];
		Sprite [] _hurt = new Sprite[4];
		Sprite [] _attack = new Sprite[4];
		
		for(int i = 0; i < 4; i++){
			_move[i] = new Sprite(move[i]);
			_idle[i] = new Sprite(idle[i]);
			_hurt[i] = new Sprite(hurt[i]);
			_attack[i] = new Sprite(attack[i]);
			
			_move[i].setScale(scale);
			_idle[i].setScale(scale);
			_hurt[i].setScale(scale);
			_attack[i].setScale(scale);
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
		setScale(0.25f, 0.25f);
		setAnimation(animationIdle);
	}

}
