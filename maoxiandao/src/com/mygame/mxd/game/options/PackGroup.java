package com.mygame.mxd.game.options;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygame.mxd.game.DataSet;

public class PackGroup extends Actor {

	private Sprite packTitle;

	private float packHeight;
	private float packWidth;
	private float PackX;
	private float PackY;

	private Pack pack;

	private final int PACK_1 = 0;
	private final int PACK_2 = 1;
	private final int PACK_3 = 2;

	private int current = 0;

	public PackGroup(Texture title, Pack pack) {
		createPackTitle(title);
		this.pack = pack;
	}

	private void drawGroup(SpriteBatch batch) {
		packTitle.draw(batch);
		pack.draw(batch);
	}

	public float getTitleWidth() {
		return packTitle.getWidth();
	}

	public float getTitleHeight() {
		return packTitle.getHeight();
	}

	public void changePackTab() {

	}

	private void createPackTitle(Texture title) {
		packTitle = new Sprite(title);
	}

	public void setPosition(float x, float y) {
		packTitle.setPosition(x, y);
		pack.setPosition(DataSet.SCREEN_WIDGHT - pack.getPack().getMiddleWidth(), y - pack.getPack().getMiddleWidth());
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		drawGroup(batch);
	}
}
