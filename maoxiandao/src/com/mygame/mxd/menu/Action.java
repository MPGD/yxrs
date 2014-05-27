package com.mygame.mxd.menu;

import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.mygame.mxd.game.DataSet;

public class Action {

	public Action() {
		initAction();
	}

	public static final float DURATION = 0.4f;
	public static final float X = (float) ((DataSet.ScreenWidth - DataSet.ScreenWidth * 0.6) / 2);
	public static final float M_IN_Y = (float) (DataSet.ScreenHeight * 0.6);
	public static final float M_OUT_Y = DataSet.ScreenHeight;

	public static final float S_IN_Y = (float) (DataSet.ScreenHeight * 0.3);
	public static float S_OUT_Y;

	public static final float B_OUT_Y = M_OUT_Y;
	public static float B_IN_Y;

	public static float INFOMATION_X;
	public static final float INFOMATION_OUT_Y = M_OUT_Y;
	public static float INFOMATION_IN_Y;

	public static final float LABEL_MUSIC_X = DataSet.ScreenWidth / 2 - 10;
	public static final float LABEL_MUSIC_OUT_Y = M_OUT_Y + 20;
	public static final float LABEL_SOUND_OUT_Y = S_OUT_Y - 20;
	public static final float LABEL_MUSIC_IN_Y = M_IN_Y + 20;
	public static final float LABEL_SOUND_IN_Y = S_IN_Y - 20;

	public MoveToAction m_moveIn = new MoveToAction();
	public MoveToAction m_moveOut = new MoveToAction();

	public MoveToAction s_moveIn = new MoveToAction();
	public MoveToAction s_moveOut = new MoveToAction();

	public MoveToAction b_moveIn = new MoveToAction();
	public MoveToAction b_moveOut = new MoveToAction();

	public MoveToAction i_moveIn = new MoveToAction();
	public MoveToAction i_moveOut = new MoveToAction();

	public AlphaAction fade_in = new AlphaAction();
	public AlphaAction fade_out = new AlphaAction();

	private void initAction() {

		m_moveIn.setDuration(DURATION);
		m_moveIn.setPosition(X, M_IN_Y);

		m_moveOut.setDuration(DURATION);
		m_moveOut.setPosition(X, M_OUT_Y);

		s_moveIn.setDuration(DURATION);
		s_moveIn.setPosition(X, S_IN_Y);

		s_moveOut.setDuration(DURATION);
		s_moveOut.setPosition(X, S_OUT_Y);

		b_moveIn.setDuration(DURATION);
		b_moveIn.setY(B_IN_Y);

		b_moveOut.setDuration(DURATION);
		b_moveOut.setY(B_OUT_Y);

		i_moveIn.setDuration(DURATION);
		i_moveIn.setPosition(Action.INFOMATION_X, Action.INFOMATION_IN_Y);

		i_moveOut.setDuration(DURATION);
		i_moveOut.setPosition(Action.INFOMATION_X, Action.INFOMATION_OUT_Y);

		fade_in.setDuration(DURATION);
		fade_in.setAlpha(0.8f);

		fade_out.setDuration(DURATION);
		fade_out.setAlpha(0);
	}

}
