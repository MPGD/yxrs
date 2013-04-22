package com.mygame.mxd.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygame.mxd.game.AssetManagerSingleton;
import com.mygame.mxd.game.DataSet;
import com.mygame.mxd.screens.BaseScreen;
import com.mygame.mxd.screens.GameScreen;

public class MainMenu extends BaseScreen {

	private Button btn_start;
	private Button btn_settings;
	private Button btn_more;

	private MenuStage stage;
	private SpriteBatch batch;

	private NinePatch ninePatch;

	private Sound sound;

	public MainMenu(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		GameMusic.setBackgroundMusic((Music) AssetManagerSingleton.manager.get("data/audio/FairyTalediffvers.mp3"));
		GameMusic.play();
		GameSound.setSound((Sound) AssetManagerSingleton.manager.get("data/audio/BtMouseOver.mp3"));
		stage = new MenuStage(DataSet.ScreenWidth, DataSet.ScreenHeight, true);

		Drawable start_up = new TextureRegionDrawable(new TextureRegion(
				(Texture) AssetManagerSingleton.manager.get("data/menu/btn_start_up.png")));
		Drawable start_down = new TextureRegionDrawable(new TextureRegion(
				(Texture) AssetManagerSingleton.manager.get("data/menu/btn_start_down.png")));
		Drawable settings_up = new TextureRegionDrawable(new TextureRegion(
				(Texture) AssetManagerSingleton.manager.get("data/menu/btn_settings_up.png")));
		Drawable settings_down = new TextureRegionDrawable(new TextureRegion(
				(Texture) AssetManagerSingleton.manager.get("data/menu/btn_settings_down.png")));
		Drawable more_up = new TextureRegionDrawable(new TextureRegion(
				(Texture) AssetManagerSingleton.manager.get("data/menu/btn_more_up.png")));
		Drawable more_down = new TextureRegionDrawable(new TextureRegion(
				(Texture) AssetManagerSingleton.manager.get("data/menu/btn_more_down.png")));

		btn_start = new Button(start_up, start_down);
		btn_settings = new Button(settings_up, settings_down);
		btn_more = new Button(more_up, more_down);

		btn_start.addListener(click);
		btn_settings.addListener(click);
		btn_more.addListener(click);

		btn_start.setPosition((DataSet.ScreenWidth - btn_start.getWidth()) / 2, DataSet.ScreenHeight / 2);
		btn_settings.setPosition((DataSet.ScreenWidth - btn_settings.getWidth()) / 2,
				btn_start.getY() - btn_start.getHeight() - 10);
		btn_more.setPosition((DataSet.ScreenWidth - btn_more.getWidth()) / 2,
				btn_settings.getY() - btn_start.getHeight() - 10);

		stage.addActor(btn_start);
		stage.addActor(btn_settings);
		stage.addActor(btn_more);

		TextureRegion tr = new TextureRegion(new Texture("data/menu/block.png"));
		ninePatch = new NinePatch(tr, tr, tr, tr, tr, tr, tr, tr, tr);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.draw();
		// batch.begin();
		// ninePatch.draw(batch, 100, 100, 159, 159);
		// batch.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Gdx.app.debug("xue", "mainmenu resource begin dispose");
		batch.dispose();
		GameMusic.dispose();
		AssetManagerSingleton.manager.clear();
		Gdx.app.debug("xue", "mainmenu resource disposed");
	}

	private ClickListener click = new ClickListener() {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			// super.clicked(event, x, y);
			if (event.getListenerActor() == btn_start) {
				GameSound.play(GameSound.PRESS);
				game.dispose();
				
				game.setScreen(new GameScreen(game));
				return;
			}
			if (event.getListenerActor() == btn_settings) {
				GameSound.play(GameSound.PRESS);
				return;
			}
			if (event.getListenerActor() == btn_more) {
				GameSound.play(GameSound.PRESS);
				return;
			}
		}
	};
}
