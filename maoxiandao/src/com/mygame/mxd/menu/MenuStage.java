package com.mygame.mxd.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygame.mxd.game.DataSet;

public class MenuStage extends Stage implements InputProcessor {

	private Texture bg;
	private SpriteBatch batch;

	public MenuStage(float width, float height, boolean keepAspectRatio) {
		super(width, height, keepAspectRatio);
		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		batch.begin();
		batch.draw(bg, 0, 0, DataSet.ScreenWidth, DataSet.ScreenHeight);
		batch.end();
		super.draw();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

	public void setBG(Texture bg) {
		this.bg = bg;
	}

}
