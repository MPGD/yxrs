package com.mygame.mxd.game.options;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;

public class XmlDataBase {

	private FileHandle mFilehandle;
	private XmlReader mXmlReader;

	private File mWritePath;
	private XmlWriter mXmlWriter;

	public XmlDataBase(String file) {
		this.mFilehandle = Gdx.files.internal(file);
		mWritePath = Gdx.files.external(file.substring(file.lastIndexOf("/"))).file();
	}

	public void writeXml(Equipment equipment) {
		try {
			System.out.println("写入文件："+mWritePath);
			mXmlWriter = new XmlWriter(new FileWriter(mWritePath));
			//mXmlWriter = new XmlWriter(new FileWriter("src/data/items/hello.xml"));
			mXmlWriter.element("item-description");
				mXmlWriter.element(Equipment.LEVEL).attribute(Equipment.VALUE, equipment.level);
					mXmlWriter.element(Equipment.ITEM).attribute(Equipment.NAME, equipment.name)
						.element(Equipment.TYPE).attribute(Equipment.NAME, equipment.type).pop()
						.element(Equipment.PATH).attribute(Equipment.NAME, equipment.path).pop()
						.element(Equipment.DESCRIPTION).attribute(Equipment.NAME, equipment.description).pop()
						.element(Equipment.EFFECT).attribute(Equipment.DEFENSE, equipment.defense)
												  .attribute(Equipment.LUCKY, equipment.lucky).pop()
						.element(Equipment.PROBABILITY).attribute(Equipment.VALUE, equipment.probability).pop()
					.pop()
				.pop()
			.pop();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void close(){
		try {
			mXmlWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		XmlDataBase x= new XmlDataBase(null);
		Equipment mEquipment = new Equipment();
		mEquipment.level=100;
		mEquipment.name="hello";
		x.writeXml(mEquipment);
		System.out.println("finished");
		
	}

	public XmlReader.Element readXml() {
		XmlReader.Element mElement = null;
		try {

			mXmlReader = new XmlReader();
			mElement = mXmlReader.parse(mFilehandle);
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
