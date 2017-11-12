package com.example.tuan.myapplication.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {

	private int points;
	private Cost resources;

	public String status;

	public String playerColor;
	private boolean turn;
	private boolean diceRolled;
	public int id; 
	private String name;
	private boolean banditRight;	
	private int vicPoint ;

	public boolean hasLongestRoad;
	private int countKnight;

	private List<Road> roads;
	private ArrayList<ExchangeRate> rates;

	public boolean largestArmy;
    public int longestRoad;
	public ArrayList<Card> devCards;
	public ArrayList<House> houses;
	private boolean amZug;

	public Player(int idPlayer) {
		this.id = idPlayer;
		this.points = 0;
		this.resources = new Cost(0,0,0,0,0);
		this.countKnight = 0;
		houses = new ArrayList<>();
		roads = new ArrayList<>();
		rates = new ArrayList<>();
		devCards = new ArrayList<>();

	}

	public  void setArmyNumber (int a) {
		this.countKnight = a;

	}
	public void setBiggestArmy(boolean a) {
		this.largestArmy = a;

	}
	public boolean getBiggestArmy() {
		return this.largestArmy;

	}
	public int getPoint() {
		return this.points;
	}

	public boolean checkResource (Cost cost) {
		if (resources.brick < cost.brick) {
			return false;
		}
		if (resources.lumber < cost.lumber) {
			return false;
		}
		if (resources.wool < cost.wool) {
			return false;
		}
		if (resources.grain < cost.grain) {
			return false;
		}
		if (resources.ore < cost.ore) {
			return false;
		}
		return true;
	}
	
	public boolean useResource(Cost cost) {
		if (resources.brick < cost.brick) {
			return false;
		}
		if (resources.lumber < cost.lumber) {
			return false;
		}
		if (resources.wool < cost.wool) {
			return false;
		}
		if (resources.grain < cost.grain) {
			return false;
		}
		if (resources.ore < cost.ore) {
			return false;
		}
		resources.brick -= cost.brick;
		resources.lumber -= cost.lumber;
		resources.wool -= cost.wool;
		resources.grain -= cost.grain;
		resources.ore -= cost.ore;
		return true;
	}
	
	public boolean handleBuildHouseRequest(Tile a, Tile b, Tile c) {

		Cost houseCost = Cost.getCostOfHouse();
		if (!this.checkResource(houseCost)) {
			return false;
		}
		this.useResource(houseCost);
		this.houses.add(new House(a,b,c,id));
		return true;	

	}

	public void addResource(Cost res) {
		resources.brick += res.brick;
		resources.lumber += res.lumber;
		resources.wool += res.wool;
		resources.grain += res.grain;
		resources.ore += res.ore;
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

	public void setColor(String c) {
		this.playerColor = c;
	}

	public void setName (String name) {
		this.name = name ;
	}

	public String getName () {
		return this.name;
	}

	public void setStatus ( String stat) {
		this.status = stat;
	}

	public String getStatus () {
		return this.status;
	}

	public void setVicPoint(int point) {
		this.vicPoint = point;
	}
	public int getVicPoint() {
		return this.vicPoint;
	}

	public void setAmZug(boolean am) {
		this.amZug = am;
	}

	public void addDevCard(Card card) {
		this.devCards.add(card);
	}

	public void addRoad(Road a) {
		this.roads.add(a);
	}

	public void addHouse(House h) {
		this.houses.add(h);
	}

	public Cost getResources() {
		return resources;
	}

	public ArrayList<Card> getDevCards() {
		return this.devCards;
	}

	public boolean removeDevCard(Card card) {

		for (Card cardFromPlayer : devCards ) {
			if (cardFromPlayer.type == card.type) {
				devCards.remove(cardFromPlayer);
				return true;
			}
		}
		return false;
	}

	public int getArmyNumber() {
        return this.countKnight;
    }
	public  String getColor() {
		return playerColor;
	}

	public  void setResource(Cost r) {
		resources = r;
	}

	public int getLongestRoad(){
        return this.longestRoad;
    }

    public void setLongestRoad (boolean b) {
		this.hasLongestRoad = b;
	}

	public boolean hasLongestRoad() {
		return this.hasLongestRoad;
	}

	public Cost.ResourceType getRandomResouceFromPlayer() {
		Random rand = new Random();
		boolean found = false;
		Cost.ResourceType from = Cost.ResourceType.ANYTHING;
		while (!found) {

			from = Cost.ResourceType.values()[rand.nextInt(Cost.ResourceType.values().length)];
			if (checkResource(Cost.getCostFromResourceType(from))) {
				found = true;
			}
		}
		return from;

	}

	public void updateResources(int chipNumber) {
		for (House h : houses) {
			if (h.tile1.number == chipNumber) {
				switch (h.tile1.type) {
					case LUMBER:
						addResource(new Cost(h.getLevel(),0,0,0,0));
						break;
					case BRICK :
						addResource(new Cost (0,h.getLevel(),0,0,0));
						break;
					case WOOL:
						addResource(new Cost (0,0,h.getLevel(),0,0));
						break;
					case GRAIN:
						addResource(new Cost (0,0,0,h.getLevel(),0));
						break;
					case ORE:
						addResource(new Cost (0,0,0,0,h.getLevel()));
						break;
				}
			}
			if (h.tile2.number == chipNumber) {
				switch (h.tile2.type) {
					case LUMBER:
						addResource(new Cost(h.getLevel(),0,0,0,0));
						break;
					case BRICK :
						addResource(new Cost (0,h.getLevel(),0,0,0));
						break;
					case WOOL:
						addResource(new Cost (0,0,h.getLevel(),0,0));
						break;
					case GRAIN:
						addResource(new Cost (0,0,0,h.getLevel(),0));
						break;
					case ORE:
						addResource(new Cost (0,0,0,0,h.getLevel()));
						break;
				}
			}
			if (h.tile3.number == chipNumber) {
				switch (h.tile3.type) {
					case LUMBER:
						addResource(new Cost(h.getLevel(),0,0,0,0));
						break;
					case BRICK :
						addResource(new Cost (0,h.getLevel(),0,0,0));
						break;
					case WOOL:
						addResource(new Cost (0,0,h.getLevel(),0,0));
						break;
					case GRAIN:
						addResource(new Cost (0,0,0,h.getLevel(),0));
						break;
					case ORE:
						addResource(new Cost (0,0,0,0,h.getLevel()));
						break;
				}
			}
		}
	}

	public boolean updateHouseByTiles(Tile tile1 , Tile tile2 , Tile tile3) {
		for (House h  : houses) {
			if (h.surroundingTiles.contains(tile1) && h.surroundingTiles.contains(tile2) && h.surroundingTiles.contains(tile3)) {
				h.levelUp();
				return true;
			}
		}
		return false;
	}
}
