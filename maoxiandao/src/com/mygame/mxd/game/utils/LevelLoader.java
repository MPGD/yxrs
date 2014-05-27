package com.mygame.mxd.game.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygame.mxd.game.AssetManagerSingleton;
import com.mygame.mxd.game.GameLevel;
import com.mygame.mxd.game.GameScene;
import com.mygame.mxd.game.actor.Badboy;
import com.mygame.mxd.game.map.Ladder;
import com.mygame.mxd.game.map.Land;
import com.mygame.mxd.game.map.Rope;
import com.mygame.mxd.game.map.Wall;
import com.mygame.mxd.screens.GameScreen;

public class LevelLoader {
	private static String LEVEL = "data/level/level.xml";
	
	public static GameLevel load(GameScreen gs, int level){
		GameLevel gl = new GameLevel(gs);
		FileHandle mFileHandle;
		int bgHeight = 0;
		try {
			mFileHandle = Gdx.files.internal(LEVEL);
			XmlReader xmlReader = new XmlReader();
			Element element;
			element = xmlReader.parse(mFileHandle);
			element = element.getChild(level);
			gl.setLevel(Integer.valueOf(element.getAttribute("level")));
			for(int i = 0; i < element.getChildCount(); i++){
				Element child = element.getChild(i);
				if(child.getName().equals("background")){
					gl.setScene(new GameScene(child.getAttribute("front"), child.getAttribute("back")));
					Texture t = AssetManagerSingleton.manager.get(child.getAttribute("front"));
					bgHeight = t.getHeight();
				}else if(child.getName().equals("bgm")){
				}else if(child.getName().equals("land")){
					gl.addLand(new Land(Float.valueOf(child.getAttribute("x1")), Float.valueOf(child.getAttribute("x2")), bgHeight - Float.valueOf(child.getAttribute("y"))));
				}else if(child.getName().equals("rope")){
					gl.addRope(new Rope(Float.valueOf(child.getAttribute("x")), bgHeight - Float.valueOf(child.getAttribute("y1")), bgHeight - Float.valueOf(child.getAttribute("y2"))));
				}else if(child.getName().equals("ladder")){
					gl.addLadder(new Ladder(Float.valueOf(child.getAttribute("x")), bgHeight - Float.valueOf(child.getAttribute("y1")), bgHeight - Float.valueOf(child.getAttribute("y2"))));
				}else if(child.getName().equals("wall")){
					gl.addWall(new Wall(Float.valueOf(child.getAttribute("x")), bgHeight - Float.valueOf(child.getAttribute("y1")), bgHeight - Float.valueOf(child.getAttribute("y2"))));
				}else if(child.getName().equals("enemy")){
					Badboy ghost = new Badboy();
					ghost.setPosition(Float.valueOf(child.getAttribute("x")), Float.valueOf(child.getAttribute("y")));
					gl.getGameStage().addActor(ghost);
					ghost.setGameLevel(gl);
				}else if(child.getName().equals("startleft")){
					gl.setStartLeft(Float.valueOf(child.getAttribute("x")), Float.valueOf(child.getAttribute("y")));
				}else if(child.getName().equals("startright")){
					gl.setStartRight(Float.valueOf(child.getAttribute("x")), Float.valueOf(child.getAttribute("y")));
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gl;
	}
}
