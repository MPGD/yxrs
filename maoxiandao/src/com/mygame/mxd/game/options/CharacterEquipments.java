package com.mygame.mxd.game.options;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.mygame.mxd.game.AssetManagerSingleton;

public class CharacterEquipments extends Actor {

	private ArrayList<Equipment> mEquipments = new ArrayList<Equipment>();
	private ArrayList<Sprite> mSprites = new ArrayList<Sprite>();

	private float equipmentX;
	private float equipmentY;

	public CharacterEquipments() {

	}

	public void equip(Equipment equipment) {

	}

	public void refresh(Pack pack) {
		
		if (!mSprites.isEmpty()) {
			mSprites.clear();
		}
		float offset = pack.getPack().getMiddleWidth();
		equipmentX = pack.getX()-offset*2;
		equipmentY = pack.getY()-offset*2;
		for (int i = 0; i < mEquipments.size(); i++) {
			Equipment equipment = mEquipments.get(i);
			Sprite sprite = new Sprite((Texture) AssetManagerSingleton.manager.get("data/items/" + equipment.name.type
					+ "/" + equipment.name.path));
			if (i % 9 < 3) {
				sprite.setPosition(equipmentX + offset * (i % 3), equipmentY + offset * 2);
			}
			if (i % 9 > 2 && i % 9 < 6) {
				sprite.setPosition(equipmentX + offset * (i % 3), equipmentY + offset);
			}
			if (i % 9 > 5 && i % 9 < 9) {
				sprite.setPosition(equipmentX + offset * (i % 3), equipmentY);
			}
			mSprites.add(sprite);
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		for (Sprite sprite : mSprites) {
			sprite.draw(batch);
		}
	}

	// public void draw(SpriteBatch batch) {
	// for (Sprite sprite : mSprites) {
	// sprite.draw(batch);
	// }
	// }

	public void readEquipments(File file) {
		XmlReader.Element mElement = loadXMl(file);

		for (int i = 0; i < mElement.getChildCount(); i++) {
			XmlReader.Element temp = mElement.getChild(i);
			Array<XmlReader.Element> mArray = temp.getChildrenByName(Equipment.ITEM);
			Gdx.app.debug("xue", "mArray:" + mArray);

			for (XmlReader.Element element : mArray) {
				mEquipments.add(parseElement(element));
			}
		}
	}

	public int getSize() {
		return mEquipments.size();
	}

	private Equipment parseElement(XmlReader.Element element) {
		Equipment mEquipment = new Equipment();
		mEquipment.level.levelValue = Integer.valueOf(element.getParent().getAttribute(Equipment.VALUE));
		mEquipment.name.itemName = element.getChildByName(Equipment.TYPE).getAttribute(Equipment.NAME);
		mEquipment.name.type = element.getChildByName(Equipment.TYPE).getAttribute(Equipment.NAME);
		mEquipment.name.path = element.getChildByName(Equipment.PATH).getAttribute(Equipment.NAME);
		mEquipment.name.description = element.getChildByName(Equipment.DESCRIPTION).getAttribute(Equipment.NAME);
		mEquipment.name.probability = Float.valueOf(element.getChildByName(Equipment.PROBABILITY).getAttribute(
				Equipment.VALUE));
		mEquipment.effect.defense = Integer.valueOf(element.getChildByName(Equipment.EFFECT).getAttribute(
				Equipment.DEFENSE));
		mEquipment.effect.lucky = Integer.valueOf(element.getChildByName(Equipment.EFFECT)
				.getAttribute(Equipment.LUCKY));

		return mEquipment;

	}

	private XmlReader.Element loadXMl(File file) {
		XmlReader.Element mElement = null;
		try {
			FileReader fileReader = new FileReader(file);
			XmlReader mXmlReader = new XmlReader();
			mElement = mXmlReader.parse(fileReader);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mElement;
	}

}
