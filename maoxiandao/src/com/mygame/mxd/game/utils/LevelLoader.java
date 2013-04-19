package com.mygame.mxd.game.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.mygame.mxd.game.AssetManagerSingleton;
import com.mygame.mxd.game.Block;
import com.mygame.mxd.game.GameLevel;
import com.mygame.mxd.game.GameScene;
import com.mygame.mxd.game.actor.Badboy;
import com.mygame.mxd.screens.GameScreen;

public class LevelLoader {
	
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
					gl.addBlock(new Block(Float.valueOf(splitStrings[1]), Float.valueOf(splitStrings[2]), Float.valueOf(splitStrings[3])));
				}else if(splitStrings[0].equals("enemy")){
					Badboy ghost = new Badboy();
					ghost.setPosition(Float.valueOf(splitStrings[2]), Float.valueOf(splitStrings[3]));
					gl.getGameStage().addActor(ghost);
				}else if(splitStrings[0].equals("startLeft")){
					
				}else if(splitStrings[0].equals("startRight")){
					
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gl;
	}
}
