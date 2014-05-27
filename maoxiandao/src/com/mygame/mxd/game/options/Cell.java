package com.mygame.mxd.game.options;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Cell extends ImageButton {

	public boolean status;

	public MyDialog dialog;

	private Equipment equipment;

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Cell(Drawable imageUp) {
		super(imageUp);
		// TODO Auto-generated constructor stub
		this.addListener(listener);
	}

	private ClickListener listener = new ClickListener() {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// TODO Auto-generated method stub
			dialog = MyDialog.getInstance();
			dialog.setStage(Cell.this.getStage());
			dialog.setInfo(equipment);
			dialog.show(Cell.this.getX(), Cell.this.getY());
		}
	};
}
