package com.mygame.mxd.game.gameutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygame.mxd.game.actor.XiaoMing;

public class GamePad extends Actor{
	public static int MOVE_LEFT = 1;
	public static int MOVE_RIGHT = 2;

	private Sprite sPad;
	private int mEvent = 0;
	public GamePad(){
		Texture texture = new Texture(Gdx.files.internal("data/controls.png"));
		TextureRegion[] buttons = TextureRegion.split(texture, 64, 64)[0];
		TextureRegion pad = new TextureRegion(texture, 0, 64, 128, 128);
		sPad = new Sprite(pad);
		sPad.setPosition(0, 0);
		ActorGestureListener gesListener = new ActorGestureListener(){

			@Override
			public void touchDown(InputEvent event, float x, float y,
					int pointer, int button) { 
				// TODO Auto-generated method stub
				if(x < 64) mEvent = MOVE_LEFT;
				else mEvent = MOVE_RIGHT;
				Gdx.app.debug("xujihao", "x is " + x);
			}

			@Override
			public void pan(InputEvent event, float x, float y, float deltaX,
					float deltaY) {
				// TODO Auto-generated method stub
				if(x < 64) mEvent = MOVE_LEFT;
				else mEvent = MOVE_RIGHT;
				Gdx.app.debug("xujihao", "x is " + x);
			}

			@Override
			public boolean longPress(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				if(x < 64) mEvent = MOVE_LEFT;
				else mEvent = MOVE_RIGHT;
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				mEvent = 0;
			}
			
		};
		Gdx.app.debug("xujihao", "ClickListener");

		addCaptureListener(gesListener);
		setSize(128, 128);

	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		sPad.draw(batch, parentAlpha);
	}
	
	public int getGamePadInfo(){
		return mEvent;
	}
	
}
