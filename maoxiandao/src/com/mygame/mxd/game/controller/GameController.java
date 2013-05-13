package com.mygame.mxd.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.mygame.mxd.game.DataSet;
import com.mygame.mxd.game.GameStage;
import com.mygame.mxd.game.actor.GameActor;
import com.mygame.mxd.game.actor.XiaoMing;
import com.mygame.mxd.game.gameutils.GamePad;

public class GameController {
	private XiaoMing xiaoming = null;
	GameStage mGameStage;
	private GamePad mGamePad = null;
	private Button mJump = null;
	private Button mAttack = null;
	public GameController(XiaoMing xm){
		xiaoming = xm;
		mGameStage = (GameStage)xiaoming.getStage();
	}
	
	public void process(){
		xiaoming.idle();
		
		if(mGamePad != null){
			if(GamePad.MOVE_LEFT == mGamePad.getGamePadInfo()) xiaoming.move(true);
			if(GamePad.MOVE_RIGHT == mGamePad.getGamePadInfo()) xiaoming.move(false);
			if(mJump.isPressed()) xiaoming.jump();
			if(mAttack.isPressed()) xiaoming.attack();
		}
		
		if(Gdx.input.isKeyPressed(Keys.A)){
			xiaoming.move(true);
		}
		else if(Gdx.input.isKeyPressed(Keys.D)){
			xiaoming.move(false);
		}
		
		if(Gdx.input.isKeyPressed(Keys.J)){
			xiaoming.jump();
		}
		
		if(Gdx.input.isKeyPressed(Keys.K)){
			xiaoming.attack();
		}

	}
	
	public void setGamePad(GamePad gamePad){
		mGamePad = gamePad;
	}
	public void setJumpButton(Button jump){
		mJump = jump;
	}
	public void setAttackButton(Button attack){
		mAttack = attack;
	}
}
