package com.mygame.mxd.game.gameutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.mygame.mxd.game.AssetManagerSingleton;
import com.mygame.mxd.game.actor.NormalActor;
import com.mygame.mxd.game.actor.XiaoMing;

public class StatusBar{
	
	private Stage stage;
	private Texture textureHPBarFull;
	private Texture textureHPBarEmpty;
	private Texture [] numbers = new Texture [10];
	private Texture lBracket;
	private Texture rBracket;
	private Texture percent;
	private Texture slash;
	private TextureRegion hpWord;
	private TextureRegion hpBar;
	private TextureRegion mpBar;
	private TextureRegion expBar;
	private float x;
	private float y;
	
	private final int length = 109;//血条的长度
	private final int expBarLength = 120;//血条的长度
	private final int wordHeight = 13;//血条中字的高度
	private final int barHeight = 18;//血条中字的高度
	private int level;
	private int hp;
	private int mp;
	private int exp;
	private NormalActor aHpBar;
	private NormalActor aMpBar;
	private NormalActor aExpBar;
	
	private Group hpStringsGroup;
	private Group mpStringsGroup;
	private Group expStringsGroup;
	public StatusBar(Stage utility, float x, float y){
		textureHPBarFull = AssetManagerSingleton.manager.get("data/hpbarfull.png");
		textureHPBarEmpty = AssetManagerSingleton.manager.get("data/hpbarempty.png");
		for(int i = 0; i < 10; i++){
			numbers[i] = AssetManagerSingleton.manager.get("data/number/number." + i + ".png");
		}
		lBracket = AssetManagerSingleton.manager.get("data/number/number.Lbracket.png");
		rBracket = AssetManagerSingleton.manager.get("data/number/number.Rbracket.png");
		percent = AssetManagerSingleton.manager.get("data/number/number.percent.png");
		slash = AssetManagerSingleton.manager.get("data/number/number.slash.png");
		
		hpWord = new TextureRegion(textureHPBarFull, 340, wordHeight);
		hpBar = new TextureRegion(textureHPBarFull, 1, wordHeight+2, length-1, barHeight-2);
		mpBar = new TextureRegion(textureHPBarFull, 110, wordHeight+2, length, barHeight-2);
		expBar = new TextureRegion(textureHPBarFull, 220, wordHeight+2,  expBarLength, barHeight-2);
		
		NormalActor hpEmpty = new NormalActor(new TextureRegion(textureHPBarEmpty));
		NormalActor aHpWord = new NormalActor(hpWord);
		aHpBar = new NormalActor(hpBar);
		aMpBar = new NormalActor(mpBar);
		aExpBar = new NormalActor(expBar);
		
		hpEmpty.setPosition(x, y);
		aHpWord.setPosition(x, y + barHeight);
		aHpBar.setPosition(x, y);
		aMpBar.setPosition(109, y);
		aExpBar.setPosition(220, y);
		
		utility.addActor(aHpWord);
		utility.addActor(aHpBar);
		utility.addActor(aMpBar);
		utility.addActor(aExpBar);
		utility.addActor(hpEmpty);
		stage = utility;
		this.x = x;
		this.y = y;
			
		 hpStringsGroup = new Group();
		 mpStringsGroup = new Group();
		 expStringsGroup = new Group();
		
	}
	
	public void update(XiaoMing xiaoming){ 
//		String s = String.valueOf(xiaoming.hp);
//		for(int i = 0; i < s.length(); i++){
//			Gdx.app.log("xujihao", " " + s.charAt(i));
//		}
		if(xiaoming.hp != hp){
			hpStringsGroup.clear();
			
			setHpString(String.valueOf(xiaoming.hp), String.valueOf(xiaoming.totalHP));
			hp = xiaoming.hp;
			int a = (int)((float)xiaoming.hp / xiaoming.totalHP * length);
			aHpBar.textureR.setRegionWidth(a);
		} 
		if(xiaoming.mp != mp){
			mpStringsGroup.clear();
			setMpString(String.valueOf(xiaoming.mp), String.valueOf(xiaoming.totalMP));
			mp = xiaoming.mp;
			int b = (int)((float)xiaoming.mp / xiaoming.totalMP * (length + 1));
			aMpBar.textureR.setRegionWidth(b);
		}
		if(xiaoming.exp != exp){
			expStringsGroup.clear();
			setExpString(String.valueOf(xiaoming.exp), String.valueOf(xiaoming.exp * 100 / xiaoming.expToLevelUp));	
			exp = xiaoming.exp;
			int c = (int)((float)xiaoming.exp / xiaoming.expToLevelUp * expBarLength);  
			aExpBar.textureR.setRegionWidth(c);
		}


		
	}

	public void setHpString(String cur, String total){
		NormalActor [] numbersActor = new NormalActor[cur.length() + total.length() + 3];
		numbersActor[0] = new NormalActor(new TextureRegion(lBracket));
		for(int i = 0; i < cur.length(); i++){
			numbersActor[i+1] = new NormalActor(new TextureRegion(numbers[cur.charAt(i) - '0']));
		}
		numbersActor[cur.length()+1] = new NormalActor(new TextureRegion(slash));
		for(int i = 0; i < total.length(); i++){
			numbersActor[cur.length()+2+i] = new NormalActor(new TextureRegion(numbers[total.charAt(i) - '0']));
		}
		numbersActor[cur.length() + total.length() + 2] = new NormalActor(new TextureRegion(rBracket));
		for(int i = 0; i < cur.length() + total.length() + 3; i++){
			numbersActor[i].setPosition(x + 20 + i * 7, y + 20);
			hpStringsGroup.addActor(numbersActor[i]);
			stage.addActor(hpStringsGroup);
		}
	}
	
	public void setMpString(String cur, String total){
		NormalActor [] numbersActor = new NormalActor[cur.length() + total.length() + 3];
		numbersActor[0] = new NormalActor(new TextureRegion(lBracket));
		for(int i = 0; i < cur.length(); i++){
			numbersActor[i+1] = new NormalActor(new TextureRegion(numbers[cur.charAt(i) - '0']));
		}
		numbersActor[cur.length()+1] = new NormalActor(new TextureRegion(slash));
		for(int i = 0; i < total.length(); i++){
			numbersActor[cur.length()+2+i] = new NormalActor(new TextureRegion(numbers[total.charAt(i) - '0']));
		}
		numbersActor[cur.length() + total.length() + 2] = new NormalActor(new TextureRegion(rBracket));
		for(int i = 0; i < cur.length() + total.length() + 3; i++){
			numbersActor[i].setPosition(x + 20 + i * 7 + 109, y + 20);
			mpStringsGroup.addActor(numbersActor[i]);
			stage.addActor(mpStringsGroup);
		}
	}
	
	public void setExpString(String cur, String percent){
		NormalActor [] numbersActor = new NormalActor[cur.length() + percent.length() + 3];
		for(int i = 0; i < cur.length(); i++){
			numbersActor[i] = new NormalActor(new TextureRegion(numbers[cur.charAt(i) - '0']));
		}
		numbersActor[cur.length()] = new NormalActor(new TextureRegion(lBracket));
		for(int i = 0; i < percent.length(); i++){
			numbersActor[cur.length()+1+i] = new NormalActor(new TextureRegion(numbers[percent.charAt(i) - '0']));
		}
		numbersActor[cur.length()+1+percent.length()] = new NormalActor(new TextureRegion(this.percent));
		numbersActor[cur.length()+2+percent.length()] = new NormalActor(new TextureRegion(rBracket));
		for(int i = 0; i < cur.length() + percent.length() + 3; i++){
			numbersActor[i].setPosition(x + 20 + i * 7 + 220 + 20, y + 20);
			expStringsGroup.addActor(numbersActor[i]);
			stage.addActor(expStringsGroup);
		}
	}

}
