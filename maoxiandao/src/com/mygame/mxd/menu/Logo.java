package com.mygame.mxd.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.mxd.game.AssetManagerSingleton;
import com.mygame.mxd.game.DataSet;
import com.mygame.mxd.screens.BaseScreen;

public class Logo extends BaseScreen {

	private SpriteBatch batch;
	private Texture texture;
	private Texture texture2;
	private Sprite sprite;
	private Sprite sprite2;
	private int time = 0;

	private float alpha = 1;

	private AssetManager manager;

	public Logo(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

		manager = AssetManagerSingleton.getManager();

		manager.load("data/audio/FairyTalediffvers.mp3", Music.class);
		manager.load("data/audio/BtMouseClick.mp3", Sound.class);
		manager.load("data/audio/BtMouseOver.mp3", Sound.class);
		manager.load("data/audio/CharSelect.mp3", Sound.class);
		manager.load("data/menu/bt_newgame_down.png", Texture.class);
		manager.load("data/menu/bt_newgame_up.png", Texture.class);

		batch = new SpriteBatch();

		sprite = new Sprite(new Texture(
				Gdx.files.internal("data/menu/game_logo.png")));
		sprite.setPosition((DataSet.ScreenWidth - sprite.getWidth()) / 2,
				(DataSet.ScreenHeight - sprite.getHeight()) / 2);
		sprite2 = new Sprite(new Texture(
				Gdx.files.internal("data/menu/menu_bg.jpg")), 420, 380, 800,
				500);

		sprite2.setSize(DataSet.ScreenWidth, DataSet.ScreenHeight);

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();

		sprite2.draw(batch);
		sprite.draw(batch);
		batch.end();
		if (alpha < 200) {
			alpha++;
		}
		sprite2.setColor(1, 1, 1, alpha);
		sprite.setColor(1, 1, 1, 201 - alpha);
		time++;

		Gdx.app.debug("xue", "QueuedAssets:" + manager.getQueuedAssets());
		Gdx.app.debug("xue", "LoadedAssets:" + manager.getLoadedAssets());
		Gdx.app.debug("xue", "Progress:" + manager.getProgress());

		if (manager.update() && time > 400) {
			this.dispose();
			super.game.setScreen(new MainMenu(super.game));
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		texture.dispose();
		texture2.dispose();
	}

}
