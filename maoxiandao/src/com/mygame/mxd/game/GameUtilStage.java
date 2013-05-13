package com.mygame.mxd.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygame.mxd.game.gameutils.GamePad;

public class GameUtilStage extends Stage{
	
	public GamePad mGamePad;
	public Button buttJump;
	public Button buttAttack;
	private Texture texture;
	
	public GameUtilStage(float width, float height, boolean keepAspectRatio) {
		super(width, height, keepAspectRatio);
		Gdx.input.setInputProcessor(this);
		addButtons();
	}

	private void addButtons(){
		texture = AssetManagerSingleton.manager.get("data/controls.png");
		buttJump = new Button(new TextureRegionDrawable(new TextureRegion(texture, 0, 0, 64, 64)));
		buttAttack = new Button(new TextureRegionDrawable(new TextureRegion(texture, 64, 0, 64, 64)));
		buttJump.setSize(64, 64);
		buttAttack.setSize(64, 64);
		buttJump.setPosition(400, 50);
		buttAttack.setPosition(600, 50);
		
		mGamePad = new GamePad();
		addActor(mGamePad);
		addActor(buttJump);
		addActor(buttAttack);
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		texture.dispose();
		mGamePad.dispose();
		super.dispose();
	}
	
	
}
