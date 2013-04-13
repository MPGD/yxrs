package com.mygame.mxd.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.mygame.mxd.game.GameStage;
import com.mygame.mxd.game.utils.CollisionDetect;
import com.mygame.mxd.screens.GameScreen;

public class GameAccidentDetect {
	private GameStage mGameStage;
	private XiaoMing xiaoming;
	/* 如果行为被意外探测器锁定，不再处理按键控制 */
	private boolean needControl = true;

	public GameAccidentDetect(GameScreen gameScreen) {
		mGameStage = gameScreen.getGameStage();
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

		if (detectCollision()) {
			needControl = false;
			xiaoming.addAction(new HurtAction());
		}
	}

	public boolean detectCollision() {
		boolean ret = false;

		Array<Actor> children = mGameStage.getRoot().getChildren();
		for (int i = 0; i < children.size; i++) {
			GameActor actor = (GameActor) children.get(i);
			if (actor.getName().equals("guaiwu")) {
				if (CollisionDetect.detect(new Rectangle(actor.getX()
						+ actor.paddingLeft,
						actor.getY() + actor.paddingBottom, (actor.getWidth()
								- actor.paddingLeft - actor.paddingRight)
								* actor.getScaleX(), (actor.getHeight()
								- actor.paddingTop - actor.paddingBottom)
								* actor.getScaleY()),
								new Rectangle(xiaoming.getX()
										+ xiaoming.paddingLeft,
										xiaoming.getY() + xiaoming.paddingBottom, (xiaoming.getWidth()
												- xiaoming.paddingLeft - xiaoming.paddingRight)
												* xiaoming.getScaleX(), (xiaoming.getHeight()
												- xiaoming.paddingTop - xiaoming.paddingBottom)
												* xiaoming.getScaleY()))) {
					Gdx.app.debug("xujihao", "collision!!!");
					ret = true;
					break;
				}
			}
		}

		return ret;
	}
}
