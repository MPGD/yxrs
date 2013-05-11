package com.mygame.mxd.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygame.mxd.game.GameBlock;
import com.mygame.mxd.game.actor.GameActor;

public class CollisionDetect {
	public static boolean detect(Rectangle rec1, Rectangle rec2) {
		boolean ret = false;
		if (rec1.overlaps(rec2))
			ret = true;
		return ret;
	}

	public static boolean detect(GameActor actor, GameBlock gameBlock) {
		boolean ret = false;
		Rectangle rec1 = new Rectangle(actor.getRealX(),
				actor.getRealY(), actor.getRealWidth(), actor.getRealHeight());
		Rectangle rec2 = new Rectangle(gameBlock.x1, gameBlock.y, gameBlock.x2
				- gameBlock.x1, 1);
//		Debug.d(String
//				.format("rec1[x1 = %f y1 = %f w = %f h = %f] rec2[x1 = %f y1 = %f w = %f h = %f]",
//						rec1.x, rec1.y, rec1.width, rec1.height, rec2.x,
//						rec2.y, rec2.width, rec2.height));
		return detect(rec1, rec2);
	}
}
