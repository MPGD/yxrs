package com.mygame.mxd.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class Block extends TextureRegion implements Cloneable, Disposable {

	public Block(Texture texture,int x, int y, int width, int height) {
		super(texture);
	}
	

	public Object Clone() {

		Object object = null;
		try {
			object = (Block) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}
