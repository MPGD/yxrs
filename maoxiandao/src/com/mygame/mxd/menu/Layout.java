package com.mygame.mxd.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygame.mxd.game.AssetManagerSingleton;
import com.mygame.mxd.game.DataSet;

public class Layout {

	public Button btn_start;
	public Button btn_settings;
	public Button btn_more;
	public Button btn_back;

	public Slider musicHandler;
	public Slider soundHandler;

	public Image shadow;

	public Image infomation;

	public Label label_music;
	public Label label_sound;

	public Layout() {
		initMainMenu();
		initSettings();
		initMore();
	}

	private void initMainMenu() {
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
		btn_start.setName("btn_start");
		btn_settings = new Button(settings_up, settings_down);
		btn_settings.setName("btn_settings");
		btn_more = new Button(more_up, more_down);
		btn_more.setName("btn_more");

		btn_start.setPosition((DataSet.ScreenWidth - btn_start.getWidth()) / 2, DataSet.ScreenHeight / 2);
		btn_settings.setPosition((DataSet.ScreenWidth - btn_settings.getWidth()) / 2,
				btn_start.getY() - btn_start.getHeight() - 10);
		btn_more.setPosition((DataSet.ScreenWidth - btn_more.getWidth()) / 2,
				btn_settings.getY() - btn_start.getHeight() - 10);

		shadow = new Image((Texture) AssetManagerSingleton.getManager().get("data/menu/shadow.png"));
		shadow.setBounds(0, 0, DataSet.ScreenWidth, DataSet.ScreenHeight);
		shadow.setColor(1, 1, 1, 0);

	}

	private void initSettings() {
		Drawable seekbar1 = new TextureRegionDrawable(new TextureRegion(
				(Texture) AssetManagerSingleton.manager.get("data/menu/seekbar.png")));
		Drawable snail1 = new TextureRegionDrawable(new TextureRegion(
				(Texture) AssetManagerSingleton.manager.get("data/menu/snail.png")));
		Drawable seekbar2 = new TextureRegionDrawable(new TextureRegion(
				(Texture) AssetManagerSingleton.manager.get("data/menu/seekbar.png")));
		Drawable snail2 = new TextureRegionDrawable(new TextureRegion(
				(Texture) AssetManagerSingleton.manager.get("data/menu/snail.png")));

		Drawable back1 = new TextureRegionDrawable(new TextureRegion(
				(Texture) AssetManagerSingleton.manager.get("data/menu/button1_480.png")));
		Drawable back2 = new TextureRegionDrawable(new TextureRegion(
				(Texture) AssetManagerSingleton.manager.get("data/menu/button2_480.png")));

		musicHandler = new Slider(0, 10, 1, false, new Slider.SliderStyle(seekbar1, snail1));
		musicHandler.setWidth((float) (DataSet.ScreenWidth * 0.6));
		musicHandler.setPosition(Action.X, Action.M_OUT_Y);
		Action.S_OUT_Y = -musicHandler.getHeight();
		soundHandler = new Slider(0, 10, 1, false, new Slider.SliderStyle(seekbar2, snail2));
		soundHandler.setWidth((float) (DataSet.ScreenWidth * 0.6));
		soundHandler.setPosition(Action.X, Action.S_OUT_Y);
		musicHandler.setValue(DataSet.MUSIC_VOLUME * 10);
		soundHandler.setValue(DataSet.SOUND_VOLUME * 10);

		btn_back = new Button(back1, back2);
		Action.B_IN_Y = DataSet.ScreenHeight - btn_back.getHeight();
		btn_back.setPosition(0, Action.B_OUT_Y);

		label_music = new Label("aaaaaaa", new Label.LabelStyle(new BitmapFont(),Color.GREEN));
		//label_music.setPosition(x, y)
		label_sound = new Label("aaaaaaa", new Label.LabelStyle(new BitmapFont(),Color.GREEN));
		
	}

	private void initMore() {
		infomation = new Image((Texture) AssetManagerSingleton.manager.get("data/menu/game_logo.png"));
		Action.INFOMATION_X = (DataSet.ScreenWidth - infomation.getWidth()) / 2;
		Action.INFOMATION_IN_Y = (DataSet.ScreenHeight - infomation.getHeight()) / 2;
		infomation.setPosition(Action.INFOMATION_X, Action.INFOMATION_OUT_Y);
	}

	public float getMusicLevel() {
		return musicHandler.getValue() / 10;
	}

	public float getSoundLevel() {
		return soundHandler.getValue() / 10;

	}
}
