package com.mygame.mxd.game.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.mygame.mxd.game.DataSet;
import com.mygame.mxd.game.GameStage;
import com.mygame.mxd.game.utils.CollisionDetect;

public class XiaoMing extends GameActor{
	
	public static String XIAOMING_IMG_SRC = "data/xiaoming.png";
	public static String ATTACK_EFFECT1 = "data/actor/swordeffect/sword_effect1.png";
	public static String ATTACK_EFFECT2 = "data/actor/swordeffect/sword_effect2.png";
	public static String ATTACK_EFFECT3 = "data/actor/swordeffect/sword_effect3.png";
	public static String ATTACK_EFFECT4 = "data/actor/swordeffect/sword_effect4.png";
	public static String ATTACK_EFFECT5 = "data/actor/swordeffect/sword_effect5.png";
	public static String ATTACK_EFFECT6 = "data/actor/swordeffect/sword_effect6.png";
	
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
	
	//战斗属性
	public float strenth = 10;
	public float defense = 10;
	
	public XiaoMing(){
		Texture temp = new Texture(XIAOMING_IMG_SRC);
		
		Texture attack_effect1 = new Texture("data/actor/swordeffect/sword_effect1.png");
		Texture attack_effect2 = new Texture("data/actor/swordeffect/sword_effect2.png");
		Texture attack_effect3 = new Texture("data/actor/swordeffect/sword_effect3.png");
		Texture attack_effect4 = new Texture("data/actor/swordeffect/sword_effect4.png");
		Texture attack_effect5 = new Texture("data/actor/swordeffect/sword_effect5.png");
		Texture attack_effect6 = new Texture("data/actor/swordeffect/sword_effect6.png");
		
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
		if(!isJump){
			for(int i = 0; i < mGameLevel.gameBlocks.size(); i++){
				if(getRealX() + getRealWidth() > mGameLevel.gameBlocks.get(i).x1 && getRealX() < mGameLevel.gameBlocks.get(i).x2 && getRealY() == mGameLevel.gameBlocks.get(i).y){
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
		
		super.idle();
		controllable = true;
	}

	public void move(boolean left){
		if(isAttack && !isJump) return;
		
		if(left){
			moveLeft = true;
			setRealX(getRealX() - DataSet.MoveSpeed);
		}else {
			moveLeft = false;
			setRealX(getRealX() + DataSet.MoveSpeed);
		}
		if(getRealX() < 0){
			if(!mGameLevel.loadPrevLevel()){
				setRealX(0);
			}
		}else if(getRealX() > DataSet.ScreenWidth - getRealWidth()){
			if(!mGameLevel.loadNextLevel()){
				setRealX(DataSet.ScreenWidth - getRealWidth());
			}
		}
		if(!isAttack)
			setStatus(STATUS_MOVE);
	}
	
	public void jump(){
		if(isAttack) return;
		jump(DataSet.JumpV, DataSet.Gravity);
	}
	
	public void jump(float v, float gravity){
		if(isJump) return;
		MoveWithGravityAction jumpAction = new MoveWithGravityAction();
		jumpAction.setV(v);
		jumpAction.setGravity(gravity);
		
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
	
	public void attack(){
		if(isAttack) return;
		if(getAniTime() < comboTime){
			currAttackForm++;
			if(currAttackForm == 3){
				currAttackForm = 0;
			}
		}else{
			currAttackForm = 0;
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
}
