package com.mygame.mxd.game.options;

import java.util.List;

public class PackSource {

	private EquipmentHandle mEquipmentHandle;
	private CharacterPack characterPack;
	private List<Equipment> mEquipments;

	public PackSource(EquipmentHandle mEquipmentHandle, CharacterPack characterPack) {
		this.mEquipmentHandle = mEquipmentHandle;
		this.characterPack = characterPack;
		mEquipments=  mEquipmentHandle.readData();
	}

	public List<Equipment> getEquipments() {
		return mEquipments;
	}

	public void addEquipment(Equipment equipment) {
		mEquipments.add(equipment);
		characterPack.notifySourceRefresh();
	}

	public void removeEquipment(Equipment equipment) {
		System.out.println("kkkkk:"+mEquipments.remove(equipment));
		System.out.println(equipment);
		characterPack.notifySourceRefresh();
		mEquipmentHandle.removeData(mEquipments);
		
	}

}
