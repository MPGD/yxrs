package com.mygame.mxd.game.options;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygame.mxd.game.AssetManagerSingleton;
import com.mygame.mxd.game.DataSet;

public class CharacterPack {

	private Image background;
	private ActionBar actionBar;

	private Stage stage;

	private List<Equipment> mEquipments;

	private List<Cell> mCells = new ArrayList<Cell>();

	private float equipmentX;
	private float equipmentY;

	public CharacterPack(Stage stage, ActionBar actionBar) {
		this.stage = stage;
		this.actionBar = actionBar;
		background = new Image((Texture) AssetManagerSingleton.manager.get("data/items/options/Item.backgrnd.png"));
		setPosition();
		this.stage.addActor(background);

	}

	public void notifySourceRefresh() {
		for (Cell cell : mCells) {
			cell.remove();
		}
		refresh();
		for (Cell cell : mCells) {
			stage.addActor(cell);
		}
	}

	public void setSource(List<Equipment> mEquipments) {
		this.mEquipments = mEquipments;
	}

	private void refresh() {
		mCells.clear();
		float offsetX = 35;
		float offsetY = 35;
		equipmentX = background.getX() + 12;
		equipmentY = background.getY() + 15;

		for (int i = 0; i < mEquipments.size(); i++) {
			System.out.println("当前装备：：" + mEquipments.size());
			Equipment equipment = mEquipments.get(i);
			Texture texture = (Texture) AssetManagerSingleton.manager.get("data/items/" + equipment.type + "/"
					+ equipment.path);
			Cell cell = new Cell(new TextureRegionDrawable(new TextureRegion(texture)));
			cell.setEquipment(equipment);
			if (i % 24 < 4) {
				cell.setPosition(equipmentX + offsetX * (i % 4), equipmentY + offsetY * 6);
			}
			if (i % 24 > 3 && i % 24 < 8) {
				cell.setPosition(equipmentX + offsetX * (i % 4), equipmentY + offsetY * 5);
			}
			if (i % 24 > 7 && i % 24 < 12) {
				cell.setPosition(equipmentX + offsetX * (i % 4), equipmentY + offsetY * 4);
			}
			if (i % 24 > 11 && i % 24 < 16) {
				cell.setPosition(equipmentX + offsetX * (i % 4), equipmentY + offsetY * 3);
			}
			if (i % 24 > 15 && i % 24 < 20) {
				cell.setPosition(equipmentX + offsetX * (i % 4), equipmentY + offsetY * 2);
			}
			if (i % 24 > 19 && i % 24 < 24) {
				cell.setPosition(equipmentX + offsetX * (i % 4), equipmentY + offsetY);
			}

			mCells.add(cell);

		}
	}

	public void show() {
		refresh();
		for (Cell cell : mCells) {
			stage.addActor(cell);
		}
	}

	/* if you want to set the coordinate with manual */
	public void setPosition(float x, float y) {
		background.setPosition(x, y);
	}

	private void setPosition() {
		background.setPosition(DataSet.ScreenWidth - background.getWidth(),
				DataSet.ScreenHeight - background.getHeight() - actionBar.getHeight());
	}

	public boolean isVisible() {
		return background.isVisible();
	}

	public void setVisible(boolean b) {
		background.setVisible(b);
		for (Cell cell : mCells) {
			cell.setVisible(b);
		}
	}

	private ClickListener listener = new ClickListener() {
		public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {

		}
	};

}
