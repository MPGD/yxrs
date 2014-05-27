package com.mygame.mxd.game.options;

import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.mygame.mxd.game.DataSet;

public class ActionBar {

	private Sprite background;
	private Button[] widgets;
	private Stage stage;

	public ActionBar(Stage stage, Button[] widgets) {
		this.widgets = widgets;
		this.stage = stage;
		init();
	}

	private void init() {
		// background.setPosition(DataSet.ScreenWidth - background.getWidth(),
		// DataSet.ScreenHeight - background.getHeight());
		float x = DataSet.ScreenWidth;
		for (Button widget : widgets) {
			widget.setPosition(x = x - widget.getWidth(), DataSet.ScreenHeight - widget.getHeight());
			stage.addActor(widget);
		}
	}

	public boolean addListener(EventListener listener) {
		for (Button widget : widgets) {
			widget.addListener(listener);

		}

		return true;
	}

	public float getWidth() {
		return widgets[0].getWidth();

	}

	public float getHeight() {
		return widgets[0].getHeight();
	}
}
