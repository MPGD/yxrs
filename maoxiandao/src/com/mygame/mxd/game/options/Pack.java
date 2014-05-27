package com.mygame.mxd.game.options;

import java.util.List;

public interface Pack {

	public List<Equipment> readInfo();
	
	public void addRes(Equipment res);

	public void removeRes(Equipment res);

	public void addCoin(int coin);

	public void spendCoin(int coin);

}
