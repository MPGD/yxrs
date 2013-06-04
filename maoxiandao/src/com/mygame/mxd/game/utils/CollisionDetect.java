package com.mygame.mxd.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygame.mxd.game.actor.GameActor;
import com.mygame.mxd.game.actor.Item;
import com.mygame.mxd.game.map.Ladder;
import com.mygame.mxd.game.map.Land;
import com.mygame.mxd.game.map.Rope;
import com.mygame.mxd.game.map.Wall;

public class CollisionDetect {
	public static boolean detect(Rectangle rec1, Rectangle rec2) {
		boolean ret = false;
		if (rec1.overlaps(rec2))
			ret = true;
		return ret;
	}

	public static boolean detect(GameActor actor, Land land) {
		boolean ret = false;
		Rectangle rec1 = new Rectangle(actor.getRealX(), actor.getRealY(),
				actor.getRealWidth(), actor.getRealHeight());
		Rectangle rec2 = new Rectangle(land.x1, land.y, land.x2 - land.x1, 1);
		return detect(rec1, rec2);
	}

	public static boolean detect(Item item, Land land) {
		boolean ret = false;
		Rectangle rec1 = new Rectangle(item.getX(), item.getY(),
				item.getWidth(), item.getHeight());
		Rectangle rec2 = new Rectangle(land.x1, land.y, land.x2 - land.x1, 1);
		return detect(rec1, rec2);
	}

	public static boolean detect(GameActor actor, Rope rope) {
		boolean ret = false;
		Rectangle rec1 = new Rectangle(actor.getRealX(), actor.getRealY(),
				actor.getRealWidth(), actor.getRealHeight());
		Rectangle rec2 = new Rectangle(rope.x, rope.y1, 1, rope.y2 - rope.y1);
		return detect(rec1, rec2);
	}

	public static boolean detect(GameActor actor, Wall wall) {
		boolean ret = false;
		Rectangle rec1 = new Rectangle(actor.getRealX(), actor.getRealY(),
				actor.getRealWidth(), actor.getRealHeight());
		Rectangle rec2 = new Rectangle(wall.x, wall.y1 + 1, 1, wall.y2
				- wall.y1 - 2);
		return detect(rec1, rec2);
	}

	public static boolean detect(GameActor actor, Ladder ladder) {
		boolean ret = false;
		Rectangle rec1 = new Rectangle(actor.getRealX(), actor.getRealY(),
				actor.getRealWidth(), actor.getRealHeight());
		Rectangle rec2 = new Rectangle(ladder.x, ladder.y1 + 1, 1, ladder.y2
				- ladder.y1 + 1);
//		Gdx.app.debug(
//				"xujihao",
//				String.format(
//						"rec1.x = %f rec1.y = %f rec1.w = %f rec1.h = %f rec2.x = %f rec2.y = %f rec2.w = %f rec2.h = %f",
//						rec1.x, rec1.y, rec1.width, rec1.height, rec2.x,
//						rec2.y, rec2.width, rec2.height));
		return detect(rec1, rec2);
	}
}
