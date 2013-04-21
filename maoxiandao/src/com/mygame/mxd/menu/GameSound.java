package com.mygame.mxd.menu;

import com.badlogic.gdx.audio.Sound;
import com.mygame.mxd.game.DataSet;

public class GameSound {

	public static final int PRESS = 0;
	public static final int SELECT = 1;
	public static final int CHANGE = 2;

	private static Sound press;
	private static Sound select;
	private static Sound change;

	private static float volume = DataSet.AUDIO_VOLUME;

	public static void setSound(Sound... sounds) {
		GameSound.press = sounds[0];
		GameSound.select = sounds[1];
		GameSound.change = sounds[2];
	}

	public static void setVolume(float volume) {
		GameSound.volume = volume;
	}

	public static void play(int soundType) {
		switch (soundType) {
		case PRESS:
			press.play(volume);
			return;
		case SELECT:
			select.play(volume);
			return;
		case CHANGE:
			change.play(volume);
			return;
		}
	}
}
