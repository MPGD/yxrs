package com.mygame.mxd.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RemoveActorAction;
import com.badlogic.gdx.utils.Array;
import com.mygame.mxd.game.GameStage;
import com.mygame.mxd.game.utils.CollisionDetect;
import com.mygame.mxd.screens.GameScreen;

public class GameAccidentDetect {
	private GameStage mGameStage;
	private XiaoMing xiaoming;
	/* 如果行为被意外探测器锁定，不再处理按键控制 */
	private boolean needControl = true;
	private boolean hurtLeft = false;
	public GameAccidentDetect(GameScreen gameScreen) {
		mGameStage = gameScreen.getGameLevel().getGameStage();
		xiaoming = gameScreen.getXiaoMing();
	}

	public boolean isNeedControl() {
		return needControl;
	}

	public void detect() {
		needControl = true;

		if (xiaoming.getStatus() == GameActor.STATUS_HURT) {
			needControl = false;
			return;
		}
		if (!xiaoming.getControllable())
			needControl = false;

		if(xiaoming.getStatus() == GameActor.STATUS_ATTACK){
			detectAttack(xiaoming.getAttackArea());
		}
		
		if (detectCollision()) {
			needControl = false;
			if(xiaoming.isHurt())
				xiaoming.addAction(new HurtAction(hurtLeft));
		}
	}

	public void detectAttack(Rectangle attackArea){
		Array<Actor> children = mGameStage.getRoot().getChildren();
		for (int i = 0; i < children.size; i++) {
			if(children.get(i) instanceof Badboy){
				Badboy actor = (Badboy) children.get(i);
				if (actor.getName().equals("guaiwu") && !actor.isDied()) {
					if (CollisionDetect.detect(new Rectangle(actor.getRealX(),
							actor.getRealY(), actor.getRealWidth(), actor.getRealHeight()),
									attackArea)) {
						if(actor.isHurt() && actor.getStatus() != GameActor.STATUS_HURT){
							actor.hpCurr -= actor.loseHp;
							if(xiaoming.getRealX() >= actor.getRealX() ){
								hurtLeft = true;
							}else{
								hurtLeft = false;
							}
							Array<Action> arrAction = actor.getActions();
							actor.addAction(new HurtAction(hurtLeft));
						}
						if(actor.isDied()){
							actor.goDead();
						}
					}
				}
			}
		}

	}
	
	public boolean detectCollision() {
		boolean ret = false;

		Array<Actor> children = mGameStage.getRoot().getChildren();
		for (int i = 0; i < children.size; i++) {
			if(children.get(i) instanceof Badboy){
				Badboy actor = (Badboy) children.get(i);
				if (actor.getName().equals("guaiwu") && !actor.isDied()) {
					if (CollisionDetect.detect(new Rectangle(actor.getRealX(),
							actor.getRealY(), actor.getRealWidth(), actor.getRealHeight()),
									new Rectangle(xiaoming.getRealX(),
											xiaoming.getRealY(), xiaoming.getRealWidth(), xiaoming.getRealHeight()))) {
						if(actor.getRealX() >=  xiaoming.getRealX()){
							hurtLeft = true;
						}else{
							hurtLeft = false;
						}
								
						ret = true;
						break;
					}
				}
			}
		}

		return ret;
	}
}
