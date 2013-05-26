package com.mygame.mxd.menu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.XmlWriter;
import com.mygame.mxd.game.AssetManagerSingleton;

public class MenuController {

	private Screen screen;
	private Game game;
	private Stage stage;
	private Layout layout;
	private Action action;

	private GameSound sound = new GameSound((Sound) AssetManagerSingleton.manager.get("data/audio/BtMouseOver.mp3"));
	private GameMusic music = new GameMusic(
			(Music) AssetManagerSingleton.manager.get("data/audio/FairyTalediffvers.mp3"));

	private boolean isSettings;
	private boolean isMore;
	private boolean config;

	public MenuController(Screen screen, Game game, Stage stage) {
		this.screen = screen;
		this.game = game;
		this.stage = stage;
		init();
	}

	public void init() {
		music.play();

		layout = new Layout();
		stage.addActor(layout.shadow);
		stage.addActor(layout.btn_start);
		stage.addActor(layout.btn_settings);
		stage.addActor(layout.btn_more);

		stage.addActor(layout.musicHandler);
		stage.addActor(layout.soundHandler);
		stage.addActor(layout.btn_back);
		stage.addActor(layout.infomation);

		layout.btn_start.addListener(click);
		layout.btn_settings.addListener(click);
		layout.btn_more.addListener(click);
		layout.btn_back.addListener(click);

		action = new Action();
		

	}

	public void update() {
		if (isSettings && config) {
			config = false;
			layout.musicHandler.addAction(action.m_moveIn);
			layout.soundHandler.addAction(action.s_moveIn);
			layout.btn_back.addAction(action.b_moveIn);
			layout.shadow.addAction(action.fade_in);
			
			layout.btn_start.setVisible(false);
			layout.btn_settings.setVisible(false);
			layout.btn_more.setVisible(false);
		}
		if (isMore && config) {
			config = false;
			layout.infomation.addAction(action.i_moveIn);
			layout.btn_back.addAction(action.b_moveIn);
			layout.shadow.addAction(action.fade_in);

			layout.btn_start.setVisible(false);
			layout.btn_settings.setVisible(false);
			layout.btn_more.setVisible(false);

		}
		

		stage.act();

	}

	private void goGame(){
		AssetManager manager;
		ArrayList<AssetDescriptor> assetList;
		
		manager = AssetManagerSingleton.getManager();
		assetList = new ArrayList<AssetDescriptor>();

		//audio
		assetList.add(new AssetDescriptor("data/audio/swordL.Attack.mp3", Sound.class));
		assetList.add(new AssetDescriptor("data/audio/swordS.Attack.mp3", Sound.class));
		assetList.add(new AssetDescriptor("data/audio/0100120.Damage.mp3", Sound.class));
		assetList.add(new AssetDescriptor("data/audio/0100100.Die.mp3", Sound.class));
		assetList.add(new AssetDescriptor("data/audio/DropItem.mp3", Sound.class));
		assetList.add(new AssetDescriptor("data/audio/Jump.mp3", Sound.class));
		assetList.add(new AssetDescriptor("data/controls.png", Texture.class));
		assetList.add(new AssetDescriptor("data/bg.jpg", Texture.class));
		assetList.add(new AssetDescriptor("data/testbg.png", Texture.class));
		assetList.add(new AssetDescriptor("data/controls.png", Texture.class));
		assetList.add(new AssetDescriptor("data/ghost01.png", Texture.class));
		assetList.add(new AssetDescriptor("data/xiaoming.png", Texture.class));
		assetList.add(new AssetDescriptor("data/items/sword1.png", Texture.class));
		assetList.add(new AssetDescriptor("data/maps/mxd_1_1back.jpg", Texture.class));
		assetList.add(new AssetDescriptor("data/maps/mxd_1_1front.png", Texture.class));
		assetList.add(new AssetDescriptor("data/menu/square.png", Texture.class));
		assetList.add(new AssetDescriptor("data/menu/pack_title1.png", Texture.class));

		
		for(int i = 0; i < 6; i++){
			assetList.add(new AssetDescriptor("data/actor/swordeffect/sword_effect" + (i+1) + ".png", Texture.class));
		}
		for(int i = 0; i < 5; i++){
			assetList.add(new AssetDescriptor("data/items/cap/" + (i+1) + ".png", Texture.class));
		}
		for(int i = 0; i < 5; i++){
			assetList.add(new AssetDescriptor("data/items/cape/" + (i+1) + ".png", Texture.class));
		}
		for(int i = 0; i < 5; i++){
			assetList.add(new AssetDescriptor("data/items/coat/" + (i+1) + ".png", Texture.class));
		}
		for(int i = 0; i < 5; i++){
			assetList.add(new AssetDescriptor("data/items/glove/" + (i+1) + ".png", Texture.class));
		}
		for(int i = 0; i < 5; i++){
			assetList.add(new AssetDescriptor("data/items/sword/" + (i+1) + ".png", Texture.class));
		}
		for(int i = 0; i < 10; i++){
			assetList.add(new AssetDescriptor("data/number/tt" + i + ".png", Texture.class));
		}
		
		game.setScreen(new Progress(game, assetList, "com.mygame.mxd.screens.GameScreen"));
	}
	
	private ClickListener click = new ClickListener() {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			if (event.getListenerActor() == layout.btn_start) {
				sound.play();
				music.dispose();
				sound.dispose();
				screen.dispose();
				stage.dispose();
				goGame();
				return;
			}
			if (event.getListenerActor() == layout.btn_settings) {
				sound.play();
				isSettings = true;
				config = true;
				return;
			}
			if (event.getListenerActor() == layout.btn_more) {
				sound.play();
				config = true;
				isMore = true;
				return;
			}

			if (event.getListenerActor() == layout.btn_back) {
				sound.play();
				if (isSettings) {
					isSettings = false;
					layout.musicHandler.removeAction(action.m_moveIn);
					layout.soundHandler.removeAction(action.s_moveIn);
					layout.btn_back.removeAction(action.b_moveIn);
					layout.shadow.removeAction(action.fade_in);
					layout.musicHandler.addAction(action.m_moveOut);
					layout.soundHandler.addAction(action.s_moveOut);
					layout.btn_back.addAction(action.b_moveOut);
					layout.shadow.addAction(action.fade_out);

					layout.btn_start.setVisible(true);
					layout.btn_settings.setVisible(true);
					layout.btn_more.setVisible(true);

					music.setVolume(layout.getMusicLevel());
					sound.setVolume(layout.getSoundLevel());

					music.rePlay();

					try {
						FileWriter writer = new FileWriter("src/data/menu/conf.xml");
						XmlWriter xw = new XmlWriter(writer);
						xw.element("Volume");
						xw.element("music", music.getVolume());
						xw.element("sound", sound.getVolume());
						xw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					return;
				}
				if (isMore) {
					isMore = false;
					layout.infomation.removeAction(action.i_moveIn);
					layout.btn_back.removeAction(action.b_moveIn);
					layout.shadow.removeAction(action.fade_in);
					layout.infomation.addAction(action.i_moveOut);
					layout.btn_back.addAction(action.b_moveOut);
					layout.shadow.addAction(action.fade_out);

					layout.btn_start.setVisible(true);
					layout.btn_settings.setVisible(true);
					layout.btn_more.setVisible(true);

					return;
				}
			}

		}
	};

}
