package com.mygame.mxd.game.options;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;

public class EquipmentHandle extends XmlDataBase {

	// private List<Equipment> mEquipments;

	public EquipmentHandle(String file) {
		super(file);
		// TODO Auto-generated constructor stub
	}

	private Equipment parseData(XmlReader.Element mElement) {
		Equipment mEquipment = new Equipment();

		mEquipment.level = Integer.valueOf(mElement.getParent().getAttribute(Equipment.VALUE));
		mEquipment.name = mElement.getAttribute(Equipment.NAME);
		//mEquipment.name = mElement.getChildByName(Equipment.ITEM).getAttribute(Equipment.NAME);
		mEquipment.type = mElement.getChildByName(Equipment.TYPE).getAttribute(Equipment.NAME);
		mEquipment.path = mElement.getChildByName(Equipment.PATH).getAttribute(Equipment.NAME);
		mEquipment.description = mElement.getChildByName(Equipment.DESCRIPTION).getAttribute(Equipment.NAME);
		mEquipment.probability = Float.valueOf(mElement.getChildByName(Equipment.PROBABILITY).getAttribute(
				Equipment.VALUE));
		mEquipment.defense = Integer.valueOf(mElement.getChildByName(Equipment.EFFECT).getAttribute(Equipment.DEFENSE));
		mEquipment.lucky = Integer.valueOf(mElement.getChildByName(Equipment.EFFECT).getAttribute(Equipment.LUCKY));

		return mEquipment;
	}

	private void formatData(List<Equipment> mEquipments) {

	}

	public List<Equipment> readData() {
		// TODO Auto-generated method stub
		List<Equipment> mEquipments = new ArrayList<Equipment>();
		XmlReader.Element mElement = readXml();
		for (int i = 0; i < mElement.getChildCount(); i++) {
			XmlReader.Element temp = mElement.getChild(i);
			Array<XmlReader.Element> mArray = temp.getChildrenByName(Equipment.ITEM);
			// Gdx.app.debug("xue", "mArray:" + mArray);

			for (XmlReader.Element element : mArray) {
				mEquipments.add(parseData(element));
			}
		}
		return mEquipments;
	}

	public void removeData(List<Equipment> mEquipments) {
		for (Equipment equipment : mEquipments) {
			writeXml(equipment);
			System.out.println("写入对像："+equipment+"::"+equipment.name);
		}

		close();
	}

	public void updateData(List<Equipment> mEquipments) {
		for (Equipment equipment : mEquipments) {
			writeXml(equipment);
		}

		close();
	}

}
