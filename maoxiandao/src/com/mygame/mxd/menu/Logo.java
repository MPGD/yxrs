package com.mygame.mxd.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.mxd.game.DataSet;
import com.mygame.mxd.screens.BaseScreen;

public class Logo extends BaseScreen {

	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private int time = 0;

	public Logo(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("data/menu/game_logo.png"));
		sprite = new Sprite(texture);

		sprite.setPosition((DataSet.ScreenWidth - sprite.getWidth()) / 2,
				(DataSet.ScreenHeight - sprite.getHeight()) / 2);

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		sprite.draw(batch);
		batch.end();
		time++;
		if (time > 50) {
			Gdx.app.debug("xue", "time:" + time);
			super.game.setScreen(new MainMenu(super.game));
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		texture.dispose();
	}
}
