package com.mygame.mxd.game.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class NormalActor extends Actor implements Disposable{
	public TextureRegion textureR;
	public NormalActor(TextureRegion t){
		textureR = t;
	}


	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		batch.draw(textureR, getX(), getY());
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		textureR.getTexture().dispose();
	}
}