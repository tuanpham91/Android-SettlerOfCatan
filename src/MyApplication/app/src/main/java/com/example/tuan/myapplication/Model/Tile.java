package com.example.tuan.myapplication.model;

import android.graphics.Bitmap;

public class Tile {
	public int number;
	public TileType type;
	public ExchangeRate exchangeRate;
	public double xCoor = 0;
	public double yCoor= 0;
	public int xPos;
	public int yPos;
	public boolean selected;
	public int radius;
	public Hex hex;
	public Bitmap tileBitmap;


	public Tile() {
		selected = false;
	}

	public Tile(int x, int y, TileType type, int num) {
		this.xPos = x;
		this.yPos = y;
		this.type = type;
		this.number = num;
	}
	public void setCoor(double x, double y) {
		this.xCoor = x;
		this.yCoor = y;
	}
	public Tile(TileType a) {
		this.type = a;
	}

	public void setType (TileType a) {
		this.type = a;
	}
	public void setNumber(Integer a) {
		this.number = a;
	}

	public Cost yield(int factor) {
		if (this.type == TileType.BRICK) {
			return new Cost(1,0,0,0,0);
		}
		if (this.type == TileType.LUMBER) {
			return new Cost(0,1,0,0,0);
		}
		if (this.type == TileType.WOOL) {
			return new Cost(0,0,1,0,0);
		}
		if (this.type == TileType.GRAIN) {
			return new Cost(0,0,0,1,0);
		}

		if (this.type == TileType.ORE) {
			return new Cost(0,0,0,0,1);
		}
		else {
			return new Cost(0,0,0,0,0);
		}
	} ;

	
}


