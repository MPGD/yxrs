package com.mygame.mxd.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import com.mygame.mxd.game.AssetManagerSingleton;
import com.mygame.mxd.game.DataSet;
import com.mygame.mxd.game.GameScene;
import com.mygame.mxd.game.GameStage;
import com.mygame.mxd.screens.BaseScreen;
import com.mygame.mxd.screens.GameScreen;

public class MainMenu extends BaseScreen {

	private Texture texture;

	private Button btn_start;
	private Button btn_more;

	private GameStage stage;
	private SpriteBatch batch;
	private Sprite sprite;

	private Music backgroundMusic;

	public MainMenu(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		stage = new GameStage(DataSet.ScreenWidth, DataSet.ScreenHeight, true);

		GameScene gameScene = new GameScene();
		gameScene.setTexture("data/menu/menu_bg2.png");
		stage.setScene(gameScene);

		backgroundMusic = AssetManagerSingleton.manager
				.get("data/audio/FairyTalediffvers.mp3");

		backgroundMusic.setVolume(DataSet.AudioVolume);
		backgroundMusic.setLooping(true);
		backgroundMusic.play();

		// sprite.setSize(DataSet.ScreenWidth, DataSet.ScreenHeight);

		Drawable newgame_up = new TextureRegionDrawable(new TextureRegion(
				(Texture) AssetManagerSingleton.manager
						.get("data/menu/bt_newgame_up.png")));

		Drawable newgame_down = new TextureRegionDrawable(new TextureRegion(
				(Texture) AssetManagerSingleton.manager
						.get("data/menu/bt_newgame_down.png")));

		btn_start = new Button(newgame_up, newgame_down);
		btn_start.setPosition(200, 200);
		btn_start.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				backgroundMusic.stop();
				game.dispose();
				game.setScreen(new GameScreen(game));
			}
		});
		stage.addActor(btn_start);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);

		stage.update(delta);
		stage.draw();

		// if (Gdx.input.isTouched()) {
		// backgroundMusic.stop();
		// this.dispose();
		// super.game.setScreen(new GameScreen(super.game));
		// }
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
