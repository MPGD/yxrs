package com.mygame.mxd.menu;

import com.badlogic.gdx.audio.Sound;
import com.mygame.mxd.game.DataSet;

public class GameSound {
	private Sound press;

	private float volume = DataSet.SOUND_VOLUME;

	public GameSound(Sound sound) {
		press = sound;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public void play() {
		if (volume == 0) {
			return;
		}
		press.play(volume);
	}

	public void dispose() {
		press.dispose();
	}
}
