package com.mygame.mxd.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygame.mxd.game.DataSet;
import com.mygame.mxd.game.GameStage;
import com.mygame.mxd.game.actor.GameActor;
import com.mygame.mxd.game.actor.XiaoMing;

public class GameController {
	private XiaoMing xiaoming = null;
	GameStage mGameStage;
	public GameController(XiaoMing xm){
		xiaoming = xm;
		mGameStage = (GameStage)xiaoming.getStage();
	}
	
	public void process(){
		xiaoming.idle();
		
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
}