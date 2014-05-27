package com.mygame.mxd.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.mygame.mxd.game.DataSet;

public class ProgressBar extends Actor implements Disposable {

	private Texture platform;
	private Texture bar;
	private float powerx;
	private float powery;
	private float progress;

	private BitmapFont percent;

	public ProgressBar(float x, float y) {
		setPosition(x, y);
		platform = new Texture("data/menu/black.png");
		bar = new Texture("data/menu/green.png");
		powerx = Gdx.graphics.getWidth() / 800f;
		powery = Gdx.graphics.getHeight() / 480f;
		percent = new BitmapFont();
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		batch.draw(platform, (DataSet.ScreenWidth - platform.getWidth() * powerx) / 2 + 1, 0, platform.getWidth()
				* powerx, platform.getHeight() * powery);
		batch.draw(bar, (DataSet.ScreenWidth - bar.getWidth() * powerx) / 2, 0, bar.getWidth() * progress / 100f
				* powerx, bar.getHeight() * powery);
		percent.draw(batch, (int) progress + "%", DataSet.ScreenWidth / 2 - 10, platform.getHeight() * powery + 5);

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		platform.dispose();
		bar.dispose();
		percent.dispose();
	}

}
