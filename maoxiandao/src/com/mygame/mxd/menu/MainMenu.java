package com.mygame.mxd.menu;

import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.XmlReader;
import com.mygame.mxd.game.AssetManagerSingleton;
import com.mygame.mxd.game.DataSet;
import com.mygame.mxd.game.options.Pack;
import com.mygame.mxd.game.options.PackGroup;
import com.mygame.mxd.screens.BaseScreen;

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

		try {
			FileReader reader = new FileReader("src/data/menu/conf.xml");
			XmlReader xr = new XmlReader();
			XmlReader.Element mElement = xr.parse(reader);
			DataSet.MUSIC_VOLUME = mElement.getFloat("music");
			DataSet.SOUND_VOLUME = mElement.getFloat("sound");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		batch = new SpriteBatch();
		stage = new MenuStage(DataSet.ScreenWidth, DataSet.ScreenHeight, true);

		stage.setBG((Texture) AssetManagerSingleton.manager.get("data/menu/menu_bg2.png"));

		TextureRegion tr = new TextureRegion(new Texture("data/menu/block.png"));
		ninePatch = new NinePatch(tr, tr, tr, tr, tr, tr, tr, tr, tr);

		mMenuController = new MenuController(this, game, stage);

//		Pack pack = new Pack((Texture) AssetManagerSingleton.manager.get("data/menu/square.png"));
//		PackGroup mPackGroup = new PackGroup((Texture) AssetManagerSingleton.manager.get("data/menu/pack_title1.png"),
//				pack);
//		mPackGroup.setPosition(DataSet.SCREEN_WIDGHT - mPackGroup.getTitleWidth(),
//				DataSet.SCREEN_HEIGHT - mPackGroup.getTitleHeight());
//		Gdx.app.debug("xue", mPackGroup.getTitleWidth()+"::"+mPackGroup.getTitleHeight());
//		stage.addActor(mPackGroup);

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.draw();
		mMenuController.update();

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
