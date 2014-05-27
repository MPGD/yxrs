package com.mygame.mxd.game.actor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Disposable;
import com.mygame.mxd.game.GameLevel;

public class Item extends Actor implements Disposable{
	private Sprite item = null;
	public GameLevel mGameLevel = null;
	private Action rotateAction;
	public Item(TextureRegion tr, float x, float y){
		item = new Sprite(tr);
		setName("item");
		setPosition(x, y); 
		setSize(30, 30);
		rotateAction = Actions.rotateBy(30);
		addAction(Actions.sequence(new ItemAction(), Actions.run(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				removeAction(rotateAction);
				setRotation(0);
			}
		})));
		addAction(rotateAction);
		
	}
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		item.rotate(getRotation());
		item.setPosition(getX(), getY());
		item.draw(batch, parentAlpha);
	}
	
	public void setGameLevel(GameLevel gameLevel){
		mGameLevel = gameLevel;
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	
}
