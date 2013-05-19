package com.mygame.mxd.game;

/**
 * 地图上用来行走的块的设定
 * 目前因为高度统一为1*/
public class Land {
	public float x1;
	public float x2;
	public float y;
	
	public Land(float x1, float x2, float y){
		this.x1 = x1;
		this.x2 = x2;
		this.y = y;
	}
}
