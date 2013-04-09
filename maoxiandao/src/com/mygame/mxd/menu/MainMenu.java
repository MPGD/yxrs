package com.mygame.mxd.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.mxd.screens.BaseScreen;
import com.mygame.mxd.screens.GameScreen;

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
		texture = new Texture(Gdx.files.internal("data/menu/menu_bg.jpg"));
		sprite = new Sprite(texture, 420, 380, 800, 500);
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//sprite.setColor(1, 1, 1, 200);
		batch.begin();
		sprite.draw(batch);
		batch.end();
		if (Gdx.input.isTouched()){
			super.game.setScreen(new GameScreen(super.game));
		}
	}
}
