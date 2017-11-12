package com.example.tuan.myapplication.Model;

import android.graphics.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private int points;
	private int brick ;
	private int lumber ;
	private int wool;
	private int grain;
	private int ore;
	public Color playerColor;
	private boolean turn;
	private boolean diceRolled;
	public int id; 
	private String name;
	private boolean banditRight;	

	private int countKnight;
	private List<House> houses;
	private List<Road> roads;
	private ArrayList<ExchangeRate> rates;

	public Player(int idPlayer) {
		this.id = idPlayer;
		this.points = 0;
		this.brick = 0;
		this.lumber = 0;
		this.wool = 0;
		this.ore = 0;
		this.grain = 0;
		this.countKnight = 0;	
	}

	public int getPoint() {
		return this.points;
	}

	public boolean checkResource (Cost cost) {
		if (this.brick < cost.brick) {
			return false;
		}
		if (this.lumber < cost.lumber) {
			return false;
		}
		if (this.wool < cost.wool) {
			return false;
		}
		if (this.grain < cost.grain) {
			return false;
		}
		if (this.ore < cost.ore) {
			return false;
		}
		return true;
	}
	
	public boolean useResource(Cost cost) {
		if (this.brick < cost.brick) {
			return false;
		}
		if (this.lumber < cost.lumber) {
			return false;
		}
		if (this.wool < cost.wool) {
			return false;
		}
		if (this.grain < cost.grain) {
			return false;
		}
		if (this.ore < cost.ore) {
			return false;
		}
		this.brick -= cost.brick;
		this.lumber -= cost.lumber;
		this.wool -= cost.wool;
		this.grain -= cost.grain;
		this.ore -= cost.ore;
		return true;
	}
	
	public boolean handleBuildHouseRequest(Tile a, Tile b, Tile c) {

		Cost houseCost = Cost.getCostOfHouse();
		if (!this.checkResource(houseCost)) {
			return false;
		}
		this.useResource(houseCost);
		this.houses.add(new House(a,b,c));
		return true;	

	}

	public void addResource(Cost res) {
		this.brick += res.brick;
		this.lumber += res.lumber;
		this.wool += res.wool;
		this.grain += res.grain;
		this.ore += res.ore;
	}

	public void updateResource(int dices) {
		if (dices == 7) {
			//Gain Robber Right for this player			
		}
		else  {
			for (House house : houses) {
				for (Tile tile : house.surroundingTiles) {
					if (tile.number == dices) {
						this.addResource(tile.yield(house.getLevel()));
					}
				}
			}
		}
	}

	public int lookForExchangeRate(Cost.ResourceType from) {
		int max = 4;
		for (ExchangeRate exRate : rates) {
			if (exRate.fromMaterial == from) {
				if (exRate.rate<max) {
					max = exRate.rate;
				}
			}
		}
		return max;
	}

}
