package com.mygame.mxd.game;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygame.mxd.game.actor.XiaoMing;
import com.mygame.mxd.game.gameutils.GamePad;
import com.mygame.mxd.game.options.ActionBar;
import com.mygame.mxd.game.options.CharacterPack;
import com.mygame.mxd.game.options.Equipment;
import com.mygame.mxd.game.options.EquipmentHandle;
import com.mygame.mxd.game.options.PackSource;
import com.mygame.mxd.game.gameutils.StatusBar;
public class GameUtilStage extends Stage {

	public GamePad mGamePad;
	public Button buttJump;
	public Button buttAttack;
	private Texture texture;

	private Button btn_pack;
	private CharacterPack characterPack;
	public static PackSource packSource;
	public StatusBar statusBar;
	public XiaoMing xiaoming;
	public GameUtilStage(float width, float height, boolean keepAspectRatio) {
		super(width, height, keepAspectRatio);
		Gdx.input.setInputProcessor(this);
		addButtons();
	}

	public void update(){
		statusBar.update(xiaoming);
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		update();
		super.draw();
	}

	private void addButtons() {
		texture = AssetManagerSingleton.manager.get("data/controls.png");
		buttJump = new Button(new TextureRegionDrawable(new TextureRegion(texture, 0, 0, 64, 64)));
		buttAttack = new Button(new TextureRegionDrawable(new TextureRegion(texture, 64, 0, 64, 64)));
		buttJump.setSize(64, 64);
		buttAttack.setSize(64, 64);
		buttJump.setPosition(400, 50);
		buttAttack.setPosition(600, 50);

		mGamePad = new GamePad();
		addActor(mGamePad);
		addActor(buttJump);
		addActor(buttAttack);

		statusBar = new StatusBar(this, 0, DataSet.ScreenHeight - 31);
		// 测试背包代码

		Texture mtexture2 = AssetManagerSingleton.manager.get("data/items/options/ic_launcher.png");
		Button button = new Button(new TextureRegionDrawable(new TextureRegion(mtexture2)));
		btn_pack = new Button(new TextureRegionDrawable(new TextureRegion(mtexture2)), new TextureRegionDrawable(
				new TextureRegion(mtexture2)));
		Button button3 = new Button(new TextureRegionDrawable(new TextureRegion(mtexture2)));
		Button[] buttons = { button, btn_pack, button3 };
		ActionBar actionBar = new ActionBar(this, buttons);
		actionBar.addListener(listener);

		// EquipmentHandle mEquipmentHandle = new
		// EquipmentHandle("data/items/itemsMe.xml");
		characterPack = new CharacterPack(this, actionBar);
		packSource = new PackSource(new EquipmentHandle("data/items/itemsMe.xml"), characterPack);

		characterPack.setSource(packSource.getEquipments());
		characterPack.show();
		characterPack.setVisible(false);

	}

	private ClickListener listener = new ClickListener() {
		public void clicked(InputEvent event, float x, float y) {
			Gdx.app.debug("xue", "listener:" + event.getListenerActor());
			if (event.getListenerActor() == btn_pack) {

				if (characterPack.isVisible()) {
					characterPack.setVisible(false);
					Gdx.app.debug("xue", "disable");
					return;
				}
				characterPack.setVisible(true);
				Gdx.app.debug("xue", "visible");
				return;
			}
		}
	};

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		texture.dispose();
		mGamePad.dispose();
		super.dispose();
	}
}
