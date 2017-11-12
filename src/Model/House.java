package com.example.tuan.myapplication.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class House extends Building {
	private int level;
	public double xCoor;
	public double yCoor;
	public int width = 70;
	public int height = 70;
	public Tile tile1;
	public Tile tile2;
	public Tile tile3;
	public Bitmap bitmap;
    public ArrayList<Tile> surroundingTiles;
	public House (Tile a, Tile b , Tile c) {
        surroundingTiles = new ArrayList<>();
        surroundingTiles.add(a);
        surroundingTiles.add(b);
        surroundingTiles.add(c);
		this.level = 1;
		this.tile1 = a;
		this.tile2 = b;
		this.tile3 = c;
		this.xCoor = (a.xCoor + b.xCoor + c.xCoor)/3;
		this.yCoor = (a.yCoor + b.yCoor + c.yCoor)/3;

	}

	public void updateCoor() {
		this.xCoor = (tile1.xCoor + tile2.xCoor + tile3.xCoor)/3;
		this.yCoor = (tile1.yCoor + tile2.yCoor + tile3.yCoor)/3;
	}

	public boolean levelUp() {
		if (level == 2) {
			return false;
		} else {
			level =2;
			return true;

		}
	}

	public Cost yieldResource() {
		Cost yield = new Cost();
		// Yielding.
		return new Cost(0,0,0,0,0);
	}
	public int getLevel() {
		return this.level;
	}
	
	
}
