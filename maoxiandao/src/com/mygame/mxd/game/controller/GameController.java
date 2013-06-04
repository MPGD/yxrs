package com.mygame.mxd.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.mygame.mxd.game.DataSet;
import com.mygame.mxd.game.GameStage;
import com.mygame.mxd.game.actor.GameActor;
import com.mygame.mxd.game.actor.XiaoMing;
import com.mygame.mxd.game.gameutils.GamePad;
import com.mygame.mxd.game.gameutils.StatusBar;

public class GameController {
	public static int UP = 1;
	public static int DOWN = 2;
	public static int LEFT = 4;
	public static int RIGHT = 8;
	public static int JUMP = 16;
	public static int ATTACK = 32;
	
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
		int event = 0;
		if(((GamePad.MOVE_UP & mGamePad.getInfo()) != 0) || Gdx.input.isKeyPressed(Keys.W)){
			event |= UP;
		}
		if(((GamePad.MOVE_DOWN & mGamePad.getInfo()) != 0) || Gdx.input.isKeyPressed(Keys.S)){
			event |= DOWN;
		}
		if(((GamePad.MOVE_LEFT & mGamePad.getInfo()) != 0) || Gdx.input.isKeyPressed(Keys.A)){
			event |= LEFT;
		}
		if(((GamePad.MOVE_RIGHT & mGamePad.getInfo()) != 0) || Gdx.input.isKeyPressed(Keys.D)){
			event |= RIGHT;
		}
		if(mJump.isPressed() || Gdx.input.isKeyPressed(Keys.J)){
			event |= JUMP;
		}
		if(mAttack.isPressed() || Gdx.input.isKeyPressed(Keys.K)){
			event |= ATTACK;
		}
		xiaoming.process(event, mGamePad.getPadX(), mGamePad.getPadY());
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
