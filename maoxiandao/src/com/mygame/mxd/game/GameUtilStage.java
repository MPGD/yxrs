package com.mygame.mxd.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameUtilStage extends Stage{
	public GameUtilStage(float width, float height, boolean keepAspectRatio) {
		super(width, height, keepAspectRatio);
		Gdx.input.setInputProcessor(this);
	}

	public GameUtilStage(float width, float height, boolean keepAspectRatio, SpriteBatch batch) {
		super(width, height, keepAspectRatio, batch);
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
}
