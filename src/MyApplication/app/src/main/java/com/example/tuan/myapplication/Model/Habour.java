package com.example.tuan.myapplication.model;

import android.graphics.Bitmap;

public class Habour {
	public ExchangeRate exchangeRate;

	public Cost.ResourceType resource;

	public double xCoor;

	public double yCoor;

	public Tile tile1;
	public Tile tile2;


	public Bitmap bitmap;


	public int orientation ;

	public Habour(Cost.ResourceType type, Tile tileMap, Tile tile2) {
		if (type == Cost.ResourceType.ANYTHING) {
			this.exchangeRate = new ExchangeRate(Cost.ResourceType.ANYTHING, 3);
		} else {
			this.exchangeRate = new ExchangeRate(type, 2);
		}


		this.tile1 = tileMap;
		this.tile2 = tile2;

	}



	}
	public boolean setCoor() {
		switch (orientation) {
			case 0:
				this.xCoor = tile.xCoor + tile.radius / 2;
				this.yCoor = tile.yCoor - tile.radius;
				break;
			case 1:
				this.xCoor = tile.xCoor + tile.radius;
				this.yCoor = tile.yCoor;
				break;
			case 2:
				this.xCoor = tile.xCoor + tile.radius / 2;
				this.yCoor = tile.yCoor + tile.radius;
				break;
			case 3:
				this.xCoor = tile.xCoor - tile.radius / 2;
				this.yCoor = tile.yCoor + tile.radius;
				break;
			case 4:
				this.xCoor = tile.xCoor - tile.radius;
				this.yCoor = tile.yCoor;
				break;
			case 5:
				this.xCoor = tile.xCoor - tile.radius / 2;
				this.yCoor = tile.yCoor - tile.radius;
				break;
			default:
				return false;
		}
		return true;
	}

}
