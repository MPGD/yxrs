package com.mygame.mxd.game.options;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygame.mxd.game.AssetManagerSingleton;
import com.mygame.mxd.game.GameUtilStage;

public class MyDialog {

	private static MyDialog dialog = null;

	private Stage stage;
	private static Button button;

	private Equipment equipment;

	private MyDialog() {
	}

	public static MyDialog getInstance() {
		if (dialog == null) {
			dialog = new MyDialog();
			Texture texture = (Texture) AssetManagerSingleton.manager.get("data/items/options/ic_launcher.png");
			Drawable d = new TextureRegionDrawable(new TextureRegion(texture));
			button = new Button(d);
		}
		return dialog;
	}

	public void setStage(Stage stage) {
		this.stage = stage;

	}

	public void setInfo(Equipment equipment) {
		this.equipment = equipment;
	}

	public void show(float x, float y) {
		button.setPosition(x, y);
		stage.addActor(button);
		setListener();
	}

	public boolean isVisable() {
		return button.isVisible();

	}

	public void remove() {
		button.remove();
	}

	private void setListener() {
		button.addListener(listener);
	}

	private ClickListener listener = new ClickListener() {
		public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
			GameUtilStage.packSource.removeEquipment(equipment);
		}
	};

}
