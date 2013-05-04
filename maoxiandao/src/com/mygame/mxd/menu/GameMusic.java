package com.mygame.mxd.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import com.mygame.mxd.game.DataSet;

public class GameMusic {
	private Music BGMusic;
	private boolean loop = true;
	private float volume = DataSet.SOUND_VOLUME;

	public GameMusic(Music music) {
		this.BGMusic = music;
	}

	public void play() {
		if (volume == 0) {
			return;
		}
		BGMusic.setLooping(true);
		BGMusic.setVolume(volume);
		BGMusic.play();
	}

	public void rePlay() {
		if (BGMusic.isPlaying()) {
			stop();
		}
		play();
	}

	public void stop() {
		this.BGMusic.stop();
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public void dispose() {
		if (BGMusic != null) {
			if (BGMusic.isPlaying()) {
				BGMusic.stop();
			}
			BGMusic.dispose();
		}
	}
}
