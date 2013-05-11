package com.mygame.mxd.game.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygame.mxd.game.AssetManagerSingleton;
import com.mygame.mxd.game.GameBlock;
import com.mygame.mxd.game.GameLevel;
import com.mygame.mxd.game.GameScene;
import com.mygame.mxd.game.actor.Badboy;
import com.mygame.mxd.screens.GameScreen;

public class LevelLoader {
	private static String LEVEL = "data/level/level.xml";
	public static GameLevel load(GameScreen gs, String file){
		FileHandle mFileHandle;
		GameLevel gl = new GameLevel(gs);
		try {
			mFileHandle = Gdx.files.internal(file);
			InputStream stream = mFileHandle.read();
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));
			String line;
			while ((line = br.readLine()) != null){
				String [] splitStrings = line.split("[ ]+");
				if(splitStrings[0].equals("bg")){
					gl.setScene(new GameScene(splitStrings[1]));
				}else if(splitStrings[0].equals("bgm")){
					if(!splitStrings[1].equals("null"))
						gl.setBGM((Music)AssetManagerSingleton.manager.get(splitStrings[1]));
				}else if(splitStrings[0].equals("block")){
					gl.addBlock(new GameBlock(Float.valueOf(splitStrings[1]), Float.valueOf(splitStrings[2]), Float.valueOf(splitStrings[3])));
				}else if(splitStrings[0].equals("enemy")){
					Badboy ghost = new Badboy();
					ghost.setPosition(Float.valueOf(splitStrings[2]), Float.valueOf(splitStrings[3]));
					gl.getGameStage().addActor(ghost);
					ghost.setGameLevel(gl);
				}else if(splitStrings[0].equals("startleft")){
					gl.setStartLeft(Float.valueOf(splitStrings[1]), Float.valueOf(splitStrings[2]));
				}else if(splitStrings[0].equals("startright")){
					gl.setStartRight(Float.valueOf(splitStrings[1]), Float.valueOf(splitStrings[2]));
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gl;
	}
	
	public static GameLevel load(GameScreen gs, int level){
		GameLevel gl = new GameLevel(gs);
		FileHandle mFileHandle;
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
					gl.setScene(new GameScene(child.getAttribute("name")));
				}else if(child.getName().equals("bgm")){
				}else if(child.getName().equals("block")){
					gl.addBlock(new GameBlock(Float.valueOf(child.getAttribute("x1")), Float.valueOf(child.getAttribute("x2")), Float.valueOf(child.getAttribute("y"))));
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
