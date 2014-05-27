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
import com.badlogic.gdx.utils.Disposable;
import com.mygame.mxd.game.AssetManagerSingleton;
import com.mygame.mxd.game.actor.XiaoMing;

public class GamePad extends Actor implements Disposable{
	public static int MOVE_LEFT = 1;
	public static int MOVE_RIGHT = 2;
	public static int MOVE_UP = 4;
	public static int MOVE_DOWN = 8;
	private Sprite sPad;
	private int mEvent = 0;
	private Texture textureControls = null;
	private float px;
	private float py;
	public GamePad(){
		textureControls = AssetManagerSingleton.manager.get("data/controls.png");
		TextureRegion[] buttons = TextureRegion.split(textureControls, 64, 64)[0];
		TextureRegion pad = new TextureRegion(textureControls, 0, 64, 128, 128);
		sPad = new Sprite(pad);
		sPad.setPosition(0, 0);
		ActorGestureListener gesListener = new ActorGestureListener(){

			@Override
			public void touchDown(InputEvent event, float x, float y,
					int pointer, int button) { 
				// TODO Auto-generated method stub
				if(x < 64) mEvent = MOVE_LEFT;
				else mEvent = MOVE_RIGHT;

				px = x;
				py = y;
				Gdx.app.log("xujihao", "mEvent is " + mEvent + " x = " + x + "pad x = " + GamePad.this.getX());
			}

			@Override
			public void pan(InputEvent event, float x, float y, float deltaX,
					float deltaY) {
				// TODO Auto-generated method stub

				if(x < 64) mEvent = MOVE_LEFT;
				else mEvent = MOVE_RIGHT;
				px = x;
				py = y;
				Gdx.app.log("xujihao", "pan mEvent is " + mEvent + " x = " + x);
			}

			@Override
			public boolean longPress(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				if(x < 64) mEvent = MOVE_LEFT;
				else mEvent = MOVE_RIGHT;
				px = x;
				py = y;
				Gdx.app.log("xujihao", "longPress mEvent is " + mEvent + " x = " + x);
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				px = x;
				py = y;
				mEvent = 0;
				Gdx.app.debug("xujihao", "touchUp mEvent");
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
	
	public int getInfo(){
		//Gdx.app.log("xujihao", "event is " + mEvent);
		return mEvent;
	}

	public float getPadX(){
		return px;
	}
	
	public float getPadY(){
		return py;
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		textureControls.dispose();
	}
	
}
