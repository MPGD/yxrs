package com.mygame.mxd.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.mygame.mxd.game.AssetManagerSingleton;
import com.mygame.mxd.game.GameLevel;
import com.mygame.mxd.game.utils.ItemDrop;
import com.mygame.mxd.menu.GameSound;

public class Badboy extends GameActor{
	//attrib
	public static int MOVE_TYPE_FLOAT = 1;
	public static int MOVE_TYPE_WALK = 2;
	public static int MOVE_TYPE_JUMP = 3;
	
	public static String BADBOY_IMG_SRC = "data/ghost01.png";
	private float scaleX = 1f;
	private float scaleY = 1f;
	private float speed = 2;
	private int size = 81;
	private GameSound sound1 = new GameSound((Sound) AssetManagerSingleton.manager.get("data/audio/0100120.Damage.mp3"));
	private GameSound sound2 = new GameSound((Sound) AssetManagerSingleton.manager.get("data/audio/0100100.Die.mp3"));
	private GameSound soundDropItem = new GameSound((Sound) AssetManagerSingleton.manager.get("data/audio/DropItem.mp3"));
	private boolean isIgnore = false;
	//战斗属性
	public float hpTotal = 1000;
	public float hpCurr = 100;
	public float damage = 15;
	public float defense = 5;
	public float accuracy = 80;
	public float dodge = 5;
	
	public float loseHp = 0;
	@Override
	public boolean checkPostion() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Badboy(){
		Texture temp = AssetManagerSingleton.manager.get(BADBOY_IMG_SRC);

		// 加载关键帧动画
		TextureRegion[] move = new TextureRegion(temp).split(size, size)[0];
		TextureRegion[] idle = new TextureRegion(temp).split(size, size)[1];
		TextureRegion[] hurt = new TextureRegion(temp).split(size, size)[3];
		TextureRegion[] attack = new TextureRegion(temp).split(size, size)[4];
		
		Sprite [] _move = new Sprite[4];
		Sprite [] _idle = new Sprite[4];
		Sprite [] _hurt = new Sprite[4];
		Sprite [] _attack = new Sprite[4];
		
		for(int i = 0; i < 4; i++){
			_move[i] = new Sprite(move[i]);
			_idle[i] = new Sprite(idle[i]);
			_hurt[i] = new Sprite(hurt[i]);
			_attack[i] = new Sprite(attack[i]);
			
			_move[i].setScale(scaleX, scaleY);
			_idle[i].setScale(scaleX, scaleY);
			_hurt[i].setScale(scaleX, scaleY);
			_attack[i].setScale(scaleX, scaleY);
		}

		
		animationMove = new Animation(0.1f, _move[0], _move[1], _move[2],
				_move[3]);
		animationIdle = new Animation(0.1f, _idle[0], _idle[1], _idle[2],
				_idle[3]);
		animationHurt = new Animation(0.1f, _hurt[0], _hurt[1], _hurt[2],
				_hurt[3]);
		animationAttack = new Animation(0.15f, _attack[0], _attack[1], _attack[2],
				_attack[3]);
		
		//setY(100);
		//setX(500);
		setSize(size, size);
		setScale(scaleX, scaleY);
		setAnimation(animationIdle);
		moveLeft = true;
		
		GhostMoveAction gMoveAction = new  GhostMoveAction();
		gMoveAction.setSpeed(3);
		
		addAction(gMoveAction);
		setName("guaiwu");
		setPadding(27, 13, 20, 20);
		
		
	}

	public float getSpeed(){
		return speed;
	}

	public boolean isIgnore(){
		return isIgnore;
	}
	
	public void ignore(){
		isIgnore = true;
		addAction(Actions.sequence(Actions.delay(0.5f), Actions.run(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				isIgnore = false;
			}
			
		})));
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		moveLeft = !moveLeft;
		super.draw(batch, parentAlpha);
		moveLeft = !moveLeft;
	}

	@Override
	public boolean isHurt(GameActor gActor) {
		// TODO Auto-generated method stub
		XiaoMing xiaoming = (XiaoMing)gActor;
		loseHp = xiaoming.getAttackDamage();
		if(loseHp > hpTotal * 0.15f) return true;
		return false;
	}
	
	@Override
	public boolean beAttacked() {
		// TODO Auto-generated method stub
		boolean hurtLeft = false;
		hpCurr -= loseHp;
		if(mGameLevel.xiaoming.getRealX() >= getRealX() ){
			hurtLeft = true;
		}else{
			hurtLeft = false;
		}
		Array<Action> arrAction = getActions();
		addAction(new HurtAction(hurtLeft));
		DamageNumber n = new DamageNumber((int)loseHp, getRealX(), getRealY());
		getStage().addActor(n);
		sound1.play();
		return false;
	}
	
	@Override
	public void checkPostionCallBack() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isDied(){
		if(hpCurr < 0) return true;
		return false;
	}
	private Badboy badboy = this;
	public void goDead(){
		sound2.play();
		setStatus(STATUS_DIE);
		clearActions();
		DiedAction diedAction = new DiedAction();
		addAction(Actions.sequence(diedAction, Actions.run(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Item item = ItemDrop.calcu(badboy);
				if(item != null){
					soundDropItem.play();
					getStage().addActor(item);
					item.setGameLevel(mGameLevel);
				}
			}
		}), Actions.removeActor()));
	}
}
