package com.mygame.mxd.game.options;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.mygame.mxd.game.DataSet;

public class Pack {

	private NinePatchDrawable mNinePatchDrawbale;
	private NinePatch pack;

	private float squareSideLenght;

	private float packHeight;
	private float packWidth;
	private float x;
	private float y;

	public Pack(Texture square) {
		pack = createPack(square);
		squareSideLenght = square.getWidth();
		packWidth = squareSideLenght;
		packHeight = squareSideLenght;
	}

	private NinePatch createPack(Texture square) {
		TextureRegion mTextureRegion = new TextureRegion(square);
		return new NinePatch(mTextureRegion, mTextureRegion, mTextureRegion, mTextureRegion, mTextureRegion,
				mTextureRegion, mTextureRegion, mTextureRegion, mTextureRegion);
	}

	public NinePatch getPack() {
		return pack;
	}

	public void draw(SpriteBatch batch) {
		appearAction(10);
		pack.draw(batch, x, y, packWidth, packHeight);
	}

	private void appearAction(int speed) {
		if (x > DataSet.SCREEN_WIDGHT - squareSideLenght * 3) {
			x -= speed;
			y -= speed;
			packHeight += speed;
			packWidth += speed;
			Gdx.app.debug("xue", "patchWidth:" + packWidth);
		}
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

}
