package com.mygame.mxd.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.mxd.screens.BaseScreen;

public class MainMenu extends BaseScreen {

	private Texture texture;
	private SpriteBatch batch;
	private Sprite sprite;

	public MainMenu(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		sprite = new Sprite(texture);

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}

}
