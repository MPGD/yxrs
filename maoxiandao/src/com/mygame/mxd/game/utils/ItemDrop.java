package com.mygame.mxd.game.utils;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygame.mxd.game.AssetManagerSingleton;
import com.mygame.mxd.game.actor.Badboy;
import com.mygame.mxd.game.actor.Item;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ItemDrop {
	private static String ITEM_XML = "data/items/items.xml";

	public static Item calcu(Badboy badboy) {
		FileHandle mFileHandle;
		Item item = null;
		try {
			mFileHandle = Gdx.files.internal(ITEM_XML);
			XmlReader xmlReader = new XmlReader();
			Element element;
			element = xmlReader.parse(mFileHandle);
			for (int i = 4; i >= 0; i--) {

				// 先从该等级的物品中随机找出一个参与随机运算
				int a = (int) (Math.random() * 10000);
				Element temp = element.getChild(i);
				int b = element.getChild(i).getChildCount();
				Element ee = element.getChild(i).getChild(0);
				int h = ee.getChildCount();
				int d = element.getChildCount();
				int c = a % b;
				int g = temp.getChildCount();
				Gdx.app.log("xujihao", "a = " + a + " b = " + b + " c = " + c
						+ " d = " + d + " g = " + g + " h = " + ee.getChildCount() + " " + ee.getName());
				Element e = element.getChild(i).getChild(c);
				String s = e.getChildByName("probability")
						.getAttribute("value");
				float f = Float.valueOf(s);
				float random = (float) Math.random();
				if (random < f) {
					String path = "data/items/"
							+ e.getChildByName("type").getAttribute("name")
							+ "/"
							+ e.getChildByName("path").getAttribute("name");
					Gdx.app.debug("xujihao", path);
					Texture t = AssetManagerSingleton.manager.get(path);
					item = new Item(new TextureRegion(t), badboy.getRealX(),
							badboy.getRealY());
					break;
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}
}
