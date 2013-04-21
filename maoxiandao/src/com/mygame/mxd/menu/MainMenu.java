package com.mygame.mxd.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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

	private Texture texture;

	private Button btn_start;
	private Button btn_more;

	private MenuStage stage;
	private SpriteBatch batch;

	private NinePatch ninePatch;

	// private Music backgroundMusic;

	public MainMenu(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		BackgroundMusic.setBackgroundMusic((Music) AssetManagerSingleton.manager
				.get("data/audio/FairyTalediffvers.mp3"));
		BackgroundMusic.play();

		stage = new MenuStage(DataSet.ScreenWidth, DataSet.ScreenHeight, true);

		Drawable newgame_up = new TextureRegionDrawable(new TextureRegion(
				(Texture) AssetManagerSingleton.manager.get("data/menu/bt_newgame_up.png")));

		Drawable newgame_down = new TextureRegionDrawable(new TextureRegion(
				(Texture) AssetManagerSingleton.manager.get("data/menu/bt_newgame_down.png")));

		btn_start = new Button(newgame_up, newgame_down);
		btn_start.setPosition(200, 200);
		btn_start.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				BackgroundMusic.stop();
				game.dispose();
				game.setScreen(new GameScreen(game));
			}
		});
		stage.addActor(btn_start);

		TextureRegion tr = new TextureRegion(new Texture("data/menu/block.png"));
		ninePatch = new NinePatch(tr, tr, tr, tr, tr, tr, tr, tr, tr);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.draw();
		batch.begin();
		ninePatch.draw(batch, 100, 100, 159, 159);
		batch.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Gdx.app.debug("xue", "mainmenu resource begin dispose");
		texture.dispose();
		batch.dispose();
		BackgroundMusic.dispose();
		AssetManagerSingleton.manager.clear();
		Gdx.app.debug("xue", "mainmenu resource disposed");
	}
}
