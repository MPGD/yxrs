package com.mygame.mxd.menu;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
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

	private Sprite sprite;
	private Sprite sprite2;
	private int time = 0;

	private float alpha = 1;

	private AssetManager manager;

	@SuppressWarnings("rawtypes")
	private ArrayList<AssetDescriptor> assetList;

	public Logo(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void show() {
		// TODO Auto-generated method stub

		manager = AssetManagerSingleton.getManager();
		assetList = new ArrayList<AssetDescriptor>();

		assetList.add(new AssetDescriptor("data/audio/FairyTalediffvers.mp3", Music.class));
		assetList.add(new AssetDescriptor("data/audio/BtMouseClick.mp3", Sound.class));
		assetList.add(new AssetDescriptor("data/audio/BtMouseOver.mp3", Sound.class));
		assetList.add(new AssetDescriptor("data/audio/CharSelect.mp3", Sound.class));
		assetList.add(new AssetDescriptor("data/menu/btn_start_down.png", Texture.class));
		assetList.add(new AssetDescriptor("data/menu/btn_start_up.png", Texture.class));
		assetList.add(new AssetDescriptor("data/menu/btn_settings_up.png", Texture.class));
		assetList.add(new AssetDescriptor("data/menu/btn_settings_down.png", Texture.class));
		assetList.add(new AssetDescriptor("data/menu/btn_more_up.png", Texture.class));
		assetList.add(new AssetDescriptor("data/menu/btn_more_down.png", Texture.class));

		batch = new SpriteBatch();

		sprite = new Sprite(new Texture(Gdx.files.internal("data/menu/game_logo.png")));
		sprite.setPosition((DataSet.ScreenWidth - sprite.getWidth()) / 2,
				(DataSet.ScreenHeight - sprite.getHeight()) / 2);
		sprite2 = new Sprite(new Texture(Gdx.files.internal("data/menu/menu_bg.jpg")), 420, 380, 800, 500);

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

		if (manager.update() && time > 400) {
			this.dispose();
			super.game.setScreen(new Progress(super.game, assetList, "com.mygame.mxd.menu.MainMenu"));
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();

	}
}
