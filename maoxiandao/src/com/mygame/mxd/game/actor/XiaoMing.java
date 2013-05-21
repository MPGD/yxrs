package com.mygame.mxd.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.mygame.mxd.game.AssetManagerSingleton;
import com.mygame.mxd.game.DataSet;
import com.mygame.mxd.game.GameStage;
import com.mygame.mxd.game.controller.GameController;
import com.mygame.mxd.game.gameutils.GamePad;
import com.mygame.mxd.game.map.Ladder;
import com.mygame.mxd.game.map.Rope;
import com.mygame.mxd.game.utils.CollisionDetect;
import com.mygame.mxd.menu.GameSound;

public class XiaoMing extends GameActor{
	
	public static String XIAOMING_IMG_SRC = "data/xiaoming.png";
	public static String ATTACK_EFFECT1 = "data/actor/swordeffect/sword_effect1.png";
	public static String ATTACK_EFFECT2 = "data/actor/swordeffect/sword_effect2.png";
	public static String ATTACK_EFFECT3 = "data/actor/swordeffect/sword_effect3.png";
	public static String ATTACK_EFFECT4 = "data/actor/swordeffect/sword_effect4.png";
	public static String ATTACK_EFFECT5 = "data/actor/swordeffect/sword_effect5.png";
	public static String ATTACK_EFFECT6 = "data/actor/swordeffect/sword_effect6.png";
	private GameSound sound1 = new GameSound((Sound) AssetManagerSingleton.manager.get("data/audio/swordL.Attack.mp3"));
	private GameSound sound2 = new GameSound((Sound) AssetManagerSingleton.manager.get("data/audio/swordS.Attack.mp3"));
	private GameSound soundJump = new GameSound((Sound) AssetManagerSingleton.manager.get("data/audio/Jump.mp3"));
	private boolean isJump = false;
	//攻击条件保护，很多地方用到了这个判断，需要注意。
	//在攻击的时候，人物的状态始终处在攻击状态
	private boolean isAttack = false;
	private float attackTime = 0.5f;
	//两次攻击间隔如果在这个时间，就算连击
	private float comboTime = 0.8f;
	private int currAttackForm = 0;
	private float scaleX = 1f;
	private float scaleY = 1f;
	private int size = 81;
	private boolean controllable = true;
	private Animation animationAttackEffect1;
	private Animation animationAttackEffect2;
	private Rope climbingRope = null;
	private Ladder climbingLadder = null;
	//战斗属性
	public float strenth = 100;
	public float defense = 10;
	
	public XiaoMing(){
		Texture temp = AssetManagerSingleton.manager.get(XIAOMING_IMG_SRC);
		
		Texture attack_effect1 = AssetManagerSingleton.manager.get("data/actor/swordeffect/sword_effect1.png");
		Texture attack_effect2 = AssetManagerSingleton.manager.get("data/actor/swordeffect/sword_effect2.png");
		Texture attack_effect3 = AssetManagerSingleton.manager.get("data/actor/swordeffect/sword_effect3.png");
		Texture attack_effect4 = AssetManagerSingleton.manager.get("data/actor/swordeffect/sword_effect4.png");
		Texture attack_effect5 = AssetManagerSingleton.manager.get("data/actor/swordeffect/sword_effect5.png");
		Texture attack_effect6 = AssetManagerSingleton.manager.get("data/actor/swordeffect/sword_effect6.png");
		
		Sprite _attack_effect1 = new Sprite(attack_effect1);
		Sprite _attack_effect2 = new Sprite(attack_effect2);
		Sprite _attack_effect3 = new Sprite(attack_effect3);
		Sprite _attack_effect4 = new Sprite(attack_effect4);
		Sprite _attack_effect5 = new Sprite(attack_effect5);
		Sprite _attack_effect6 = new Sprite(attack_effect6);
		_attack_effect1.setScale(0.8f, 1);
		_attack_effect2.setScale(0.8f, 1);
		_attack_effect3.setScale(0.8f, 1);
		_attack_effect4.setScale(0.8f, 1);
		_attack_effect5.setScale(0.8f, 1);
		_attack_effect6.setScale(0.8f, 1);
		
		animationAttackEffect1 = new Animation(0.04f, _attack_effect3, _attack_effect2, _attack_effect1);
		animationAttackEffect2 = new Animation(0.04f, _attack_effect4, _attack_effect4, _attack_effect5);
		
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
		animationAttack = new Animation(0.14f, _attack[0], _attack[1], _attack[2],
				_attack[3]);
		
		setSize(size, size);
		setScale(scaleX, scaleY);
		setAnimation(animationIdle);
		setName("xiaoming");
		setPadding(17, 9, 17, 20);
	}

	@Override
	public boolean checkPostion() {
		// TODO Auto-generated method stub
		boolean ret = true;
		if(getStatus() == STATUS_CLIMB){
			return false;
		}
		if(!isJump){
			for(int i = 0; i < mGameLevel.lands.size(); i++){
				if(getRealX() + getRealWidth() > mGameLevel.lands.get(i).x1 && getRealX() < mGameLevel.lands.get(i).x2 && getRealY() == mGameLevel.lands.get(i).y){
					ret = false;
					break;
				}
			}
		}
		return ret;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
		if(getAniTime() < 0.15)
		if(getStatus() == STATUS_ATTACK){
			if((currAttackForm == 0 || currAttackForm == 1) && !isJump){
				Sprite attackEffect = (Sprite)animationAttackEffect1.getKeyFrame(getAniTime(), false);
				if(moveLeft){
					attackEffect.setPosition(getRealX() - 70, getRealY() + (getRealHeight() - attackEffect.getTexture().getHeight())/2);
					attackEffect.setScale(Math.abs(attackEffect.getScaleX()), 1);
				}else{
					attackEffect.setPosition(getRealX() + getRealWidth(), getRealY() +  (getRealHeight() - attackEffect.getTexture().getHeight())/2);
					attackEffect.setScale(Math.abs(attackEffect.getScaleX()) * -1, 1);
				}
				attackEffect.draw(batch, parentAlpha);
			}else{
				Sprite attackEffect = (Sprite)animationAttackEffect2.getKeyFrame(getAniTime(), false);
				if(moveLeft){
					attackEffect.setPosition(getRealX() - 70, getY() + (size - attackEffect.getTexture().getHeight())/2 - 10);
					attackEffect.setScale(Math.abs(attackEffect.getScaleX()), 1);
				}else{
					attackEffect.setPosition(getRealX() + getRealWidth(), getY() + (size - attackEffect.getTexture().getHeight())/2 - 10);
					attackEffect.setScale(Math.abs(attackEffect.getScaleX()) * -1, 1);
				}
				attackEffect.draw(batch, parentAlpha);
			}
		}
	}

	@Override
	public void idle() {
		// TODO Auto-generated method stub
		if(isAttack) return;
		if(getStatus() == STATUS_CLIMB) return;
		super.idle();
		controllable = true;
	}
	

	public void move(boolean left){
		if(isAttack && !isJump) return;
		if(getStatus() == STATUS_CLIMB) return;
		float temp = getRealX();
		if(left){
			moveLeft = true;
			setRealX(getRealX() - DataSet.MoveSpeed);
		}else {
			moveLeft = false;
			setRealX(getRealX() + DataSet.MoveSpeed);
		}
		if(left){
			for(int i = 0; i < mGameLevel.walls.size(); i++){
				if(CollisionDetect.detect(this, mGameLevel.walls.get(i))){
					setRealX(mGameLevel.walls.get(i).x);
					break;
				}
			}
		}else{
			for(int i = 0; i < mGameLevel.walls.size(); i++){
				if(CollisionDetect.detect(this, mGameLevel.walls.get(i))){
					setRealX(mGameLevel.walls.get(i).x - getRealWidth());
					break;
					
				}
			}
		}
//		if(getRealX() < 0){
//			if(!mGameLevel.loadPrevLevel()){
//				setRealX(0);
//			}
//		}else if(getRealX() > DataSet.ScreenWidth - getRealWidth()){
//			if(!mGameLevel.loadNextLevel()){
//				setRealX(DataSet.ScreenWidth - getRealWidth());
//			}
//		}
		if(!isAttack)
			setStatus(STATUS_MOVE);
	}
	
	public void jump(){
		if(isAttack || getStatus() == STATUS_CLIMB) return;
		jump(DataSet.JumpV, DataSet.Gravity);
	}
	
	public void jump(float v, float gravity){
		if(isJump) return;
		MoveWithGravityAction jumpAction = new MoveWithGravityAction();
		jumpAction.setV(v);
		jumpAction.setGravity(gravity);
		soundJump.play();
		RunnableAction resetJump = Actions.run(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				isJump = false;
			}
		});
		addAction(Actions.sequence(jumpAction, resetJump));
		if(!isAttack)
			setStatus(STATUS_JUMP);
		isJump = true;
	}
	
	public void stopJump(){
		isJump = false;
		clearActions();
	}
	
	public void attack(){
		if(isAttack) return;
		if(getStatus() == STATUS_CLIMB) return;
		if(getAniTime() < comboTime){
			currAttackForm++;
			if(currAttackForm == 3){
				currAttackForm = 0;
			}
		}else{
			currAttackForm = 0;
		}
		
		if(currAttackForm < 2){
			sound1.play();
		}else{
			sound2.play();
		}
		resetAniTime();
		RunnableAction resetAttack = Actions.run(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				isAttack = false;
			}
		});
		addAction(Actions.sequence(Actions.delay(attackTime), resetAttack));
		setStatus(STATUS_ATTACK);
		isAttack = true;
	}
	
	public void climb(boolean up){
		if(up){
			setRealY(getRealY() + DataSet.MoveSpeed);
			for(int i = 0; i < mGameLevel.lands.size(); i++){
				if(CollisionDetect.detect(this, mGameLevel.lands.get(i))){
					if(getRealY() >= mGameLevel.lands.get(i).y){
						Gdx.app.debug("xujihao", "get here xxxxxx 11111");
						setRealY(mGameLevel.lands.get(i).y);
						setStatus(STATUS_IDLE);
						idle();
						climbingRope = null;
						climbingLadder = null;
						break;
					}
				}
			}
		}else {
			setRealY(getRealY() - DataSet.MoveSpeed);
			if(climbingRope != null){
				if(getRealY() < climbingRope.y1){
					setStatus(STATUS_IDLE);
					idle();
					climbingRope = null;
				}
			}else if(climbingLadder != null){
				if(getRealY() < climbingLadder.y1){
					setStatus(STATUS_IDLE);
					idle();
					climbingLadder = null;
				}
			}
		}
		
		
		if(climbingRope != null){
			if(!CollisionDetect.detect(this, climbingLadder)){
				setStatus(STATUS_IDLE);
				idle();
				climbingRope = null;
			}
		}else if(climbingLadder != null){
			if(!CollisionDetect.detect(this, climbingLadder)){
				setStatus(STATUS_IDLE);
				idle();
				climbingLadder = null;
			}
		}
	}
	
	public boolean climbRope(boolean up){
		Rope rope = null;
		//Gdx.app.debug("xujihao", "climbrope " + mGameLevel.ropes.size());
		if(up){
			for(int i = 0; i < mGameLevel.ropes.size(); i++){
				if(CollisionDetect.detect(this, mGameLevel.ropes.get(i)) && getRealY() + getRealHeight() < mGameLevel.ropes.get(i).y2){
					rope = mGameLevel.ropes.get(i);
					break;
				}
			}
		}else{
			if(!isJump){
				for(int i = 0; i < mGameLevel.ropes.size(); i++){
					if(CollisionDetect.detect(this, mGameLevel.ropes.get(i)) && getRealY() + getRealHeight() > mGameLevel.ropes.get(i).y2){
						rope = mGameLevel.ropes.get(i);
						break;
					}
				}
			}
		}

		
		if(rope != null){
			climbingRope = rope;
			if(isJump) stopJump();
			setStatus(STATUS_CLIMB);
			setRealX(rope.x - getRealWidth() / 2);
			return true;
		}
		return false;
	}
	
	public boolean climbLadder(boolean up){
		Ladder ladder = null;
		if(up){
			for(int i = 0; i < mGameLevel.ladders.size(); i++){
				if(CollisionDetect.detect(this, mGameLevel.ladders.get(i)) && getRealY() + getRealHeight() < mGameLevel.ladders.get(i).y2){
					ladder = mGameLevel.ladders.get(i);
					break;
				}
			}
		}else{
			if(!isJump){
				for(int i = 0; i < mGameLevel.ladders.size(); i++){
					if(CollisionDetect.detect(this, mGameLevel.ladders.get(i))&& getRealY() + getRealHeight() > mGameLevel.ladders.get(i).y2){
						ladder = mGameLevel.ladders.get(i);
						break;
					}
				}
			}
		}
		if(ladder != null){
			climbingLadder = ladder;
			if(isJump) stopJump();
			setStatus(STATUS_CLIMB);
			setRealX(ladder.x - getRealWidth() / 2);
			Gdx.app.debug("xujihao", "climb ladder ");
			return true;
		}
		return false;
	}
	
	public Rectangle getAttackArea(){
		Rectangle ret = new Rectangle();
		if(moveLeft){
			ret.x = getX() - 60;
			ret.y = getY();
			ret.width = 60;
			ret.height = 60;
		}else{
			ret.x = getX() + size * scaleX;
			ret.y = getY();
			ret.width = 60;
			ret.height = 60;
		}
		return ret;
	}
	
	@Override
	public boolean isHurt() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean isJump(){
		return isJump;
	}
	public void setControllable(boolean ctrl){
		controllable = ctrl;
	}
	
	public boolean getControllable(){
		return controllable;
	}

	@Override
	public void checkPostionCallBack() {
		// TODO Auto-generated method stub
		jump(0, DataSet.Gravity);
	}
	
	public float getAttackDamage(){
		float ret = 0;
		ret = strenth * 1.6f; 
		return ret;
	}
	
	public void process(int event, float x, float y){
		if(event == 0){
			idle();
			return;
		}
		if(getStatus() != STATUS_CLIMB && (y > 80 || (event & GameController.UP) != 0)){
			Gdx.app.log("xujihao", "get here xxxxxx 22222s");
			if(!climbRope(true)){
				climbLadder(true);
			}
		}else if(getStatus() != STATUS_CLIMB && ((y < 20 && y > 0)|| (event & GameController.DOWN) != 0)){
			Gdx.app.log("xujihao", "get here xxxxxx 333333s");
			if(!climbRope(false)){
				climbLadder(false);
			}
		}
		//Gdx.app.log("xujihao", "event is " + event);
		if(getStatus() == STATUS_CLIMB){
			if(y > 64 || (event & GameController.UP) != 0){
				climb(true);
			}else if(y > 0 || (event & GameController.DOWN) != 0){
				climb(false);
			}
		}else{
			if((event & GameController.LEFT) != 0){
				move(true);
			}
			if((event & GameController.RIGHT) != 0){
				move(false);
			}
		}


		if((event & GameController.JUMP) != 0){
			jump();
		}
		if((event & GameController.ATTACK) != 0){
			attack();
		}
	}
}
