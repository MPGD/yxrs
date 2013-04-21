package com.mygame.mxd.menu;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.mygame.mxd.game.DataSet;

public class BackgroundMusic {
	private static Music BGMusic;
	private static boolean loop = true;
	private static float volume = DataSet.AUDIO_VOLUME;

	public static void setBackgroundMusic(Music music) {
		if (BGMusic == null) {
			BackgroundMusic.BGMusic = music;
		}
	}

	public static void play() {
		BGMusic.setLooping(true);
		BGMusic.setVolume(DataSet.AUDIO_VOLUME);
		BGMusic.play();
	}

	public static void stop() {
		BGMusic.stop();
	}

	public static void setLoop(boolean loop) {
		BackgroundMusic.loop = loop;
	}

	public static void setVolume(float volume) {
		BackgroundMusic.volume = volume;
	}

	public static void dispose() {
		if (BGMusic != null) {
			if (BGMusic.isPlaying()) {
				BGMusic.stop();
			}
			BGMusic.dispose();
		}
	}
}
