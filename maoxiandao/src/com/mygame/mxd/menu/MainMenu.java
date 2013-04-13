package com.mygame.mxd.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.mxd.game.AssetManagerSingleton;
import com.mygame.mxd.game.DataSet;
import com.mygame.mxd.screens.BaseScreen;
import com.mygame.mxd.screens.GameScreen;

public class MainMenu extends BaseScreen {

	private Texture texture;

	private SpriteBatch batch;
	private Sprite sprite;
	private Sprite bt_newgame_up;
	private Sprite bt_newgame_down;

	private Music backgroundMusic;

	public MainMenu(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

		backgroundMusic = AssetManagerSingleton.manager
				.get("data/audio/FairyTalediffvers.mp3");
		// bt_newgame_up = AssetManagerSingleton.manager
		// .get("data/menu/bt_newgame_up.png");
		// bt_newgame_down = AssetManagerSingleton.manager
		// .get("data/menu/bt_newgame_down.png");

		backgroundMusic.setVolume(DataSet.AudioVolume);
		backgroundMusic.setLooping(true);
		backgroundMusic.play();

		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("data/menu/menu_bg2.png"));

		// sprite = new Sprite(texture, 420, 380, 800, 500);
		sprite = new Sprite(texture);
		bt_newgame_up = new Sprite(
				(Texture) AssetManagerSingleton.manager
						.get("data/menu/bt_newgame_up.png"));
		bt_newgame_down = new Sprite(
				(Texture) AssetManagerSingleton.manager
						.get("data/menu/bt_newgame_down.png"));
		bt_newgame_up.setPosition(
				(DataSet.ScreenWidth - bt_newgame_up.getWidth()) / 2,
				(DataSet.ScreenHeight - bt_newgame_up.getHeight()) / 2);
		sprite.setSize(DataSet.ScreenWidth, DataSet.ScreenHeight);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// sprite.setColor(1, 1, 1, 200);
		batch.begin();
		sprite.draw(batch);
		bt_newgame_up.draw(batch);
		batch.end();
		if (Gdx.input.isTouched()) {
			backgroundMusic.stop();
			this.dispose();
			super.game.setScreen(new GameScreen(super.game));
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Gdx.app.debug("xue", "mainmenu resource begin dispose");
		texture.dispose();
		batch.dispose();
		backgroundMusic.dispose();
		AssetManagerSingleton.manager.clear();
		Gdx.app.debug("xue", "mainmenu resource disposed");
	}
}
