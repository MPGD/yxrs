package com.mygame.mxd.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygame.mxd.game.AssetManagerSingleton;
import com.mygame.mxd.game.DataSet;
import com.mygame.mxd.screens.BaseScreen;
import com.mygame.mxd.screens.GameScreen;

public class MainMenu extends BaseScreen {

	private MenuStage stage;

	private SpriteBatch batch;

	private NinePatch ninePatch;
	private MenuController mMenuController;

	public MainMenu(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		stage = new MenuStage(DataSet.ScreenWidth, DataSet.ScreenHeight, true);

		stage.setBG((Texture) AssetManagerSingleton.manager.get("data/menu/menu_bg2.png"));

		TextureRegion tr = new TextureRegion(new Texture("data/menu/block.png"));
		ninePatch = new NinePatch(tr, tr, tr, tr, tr, tr, tr, tr, tr);

		mMenuController = new MenuController(this, game, stage);

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.draw();
		mMenuController.update();

		// batch.begin();
		// ninePatch.draw(batch, 100, 100, 159, 159);
		// batch.end();

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		// Gdx.app.debug("xue", "mainmenu resource begin dispose");
		batch.dispose();

		AssetManagerSingleton.manager.dispose();
		AssetManagerSingleton.manager = null;
		Gdx.app.debug("xue", "mainmenu resource disposed");
	}
}
