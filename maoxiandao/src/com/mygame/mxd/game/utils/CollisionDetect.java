package com.mygame.mxd.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class CollisionDetect {
	public static boolean detect(Rectangle rec1, Rectangle rec2) {
		boolean ret = false;
		if (rec1.overlaps(rec2))
			ret = true;
		if (ret) {
			Gdx.app.debug(
					"xujihao",
					String.format(
							"x1=[%f] y1=[%f] width1=[%f] height1=[%f] x2=[%f] y2=[%f] width2=[%f] height2=[%f]",
							rec1.x, rec1.y, rec1.width, rec1.height, rec2.x,
							rec2.y, rec2.width, rec2.height));
		}
		return ret;
	}
}
