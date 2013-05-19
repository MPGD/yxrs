package com.mygame.mxd.game.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygame.mxd.game.AssetManagerSingleton;

public class DamageNumber extends Actor{
	private char [] c;
	private float gap = 30;
	private float image_width = 62;
	public DamageNumber(int damage, float x, float y) {
		String s = String.valueOf(damage);
		c = s.toCharArray();
		setX(x - c.length * gap / 2);
		setY(y + 60);
		addAction(Actions.sequence(Actions.moveTo(getX(), getY()+ 100, 2), Actions.removeActor()));
	}
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		for(int i = 0; i < c.length; i++){
			Texture texture = AssetManagerSingleton.manager.get("data/number/tt" + (c[i] - '0') + ".png");
			batch.draw(texture, getX() + i * gap, getY() - ((i) % 2) * 10);
		}
	}
	
}

