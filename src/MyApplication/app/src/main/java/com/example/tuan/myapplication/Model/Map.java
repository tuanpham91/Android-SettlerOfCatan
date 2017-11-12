package com.example.tuan.myapplication.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Map {

	public ArrayList<Tile> tiles = new ArrayList<>();
	public ArrayList<Tile> seaTiles = new ArrayList<>();
	public ArrayList<Integer> numbers = new ArrayList<>();
	public ArrayList<Road> roads;
	public ArrayList<House> houses ;
	public ArrayList<Habour> habours;
	public ArrayList<Position> inlandPos;
	public Robber robber;
	public ArrayList<Position> seaPost;
	public Map () {
		houses = new ArrayList<>();
		roads = new ArrayList<>();
		seaTiles = new ArrayList<>();
		habours = new ArrayList<>();
		inlandPos = new ArrayList<Position>() {{
			add(new Position (-2,2));
			add(new Position (-1,2));
			add(new Position (0,2));
			add(new Position (-2,1));
			add(new Position (-1,1));
			add(new Position (0,1));
			add(new Position (1,1));
			add(new Position (-2,0));
			add(new Position (-1,0));
			add(new Position (0,0));
			add(new Position (1,0));
			add(new Position (2,0));
			add(new Position (-1,-1));
			add(new Position (0,-1));
			add(new Position (1,-1));
			add(new Position (2,-1));
			add(new Position (0,-2));
			add(new Position (1,-2));
			add(new Position (2,-2));
		}};
		seaPost = new ArrayList<Position>() { {
			add(new Position (-3,3));
			add(new Position (-2,3));
			add(new Position (-1,3));
			add(new Position (0,3));
			add(new Position (-3,2));
			add(new Position (1,2));
			add(new Position (-3,1));
			add(new Position (2,1));
			add(new Position (-3,0));
			add(new Position (3,0));
			add(new Position (-2,-1));
			add(new Position (3,-1));
			add(new Position (-1,-2));
			add(new Position (3,-2));
			add(new Position (0,-3));
			add(new Position (1,-3));
			add(new Position (2,-3));
			add(new Position (3,-3));
		}};

		int[] nums = new int[]{2,3,3,4,4,5,5,6,6,8,8,9,9,10,10,11,11,12};



		for (int i = 0; i< nums.length ; i++ ){
			this.numbers.add(new Integer(nums[i]));
		}



		Collections.shuffle(numbers);

		int n = 3;
		for (int i = 0 ; i< n; i++) {
			tiles.add(new Tile(TileType.LUMBER));
			tiles.add(new Tile(TileType.WOOL));
			tiles.add(new Tile(TileType.BRICK));
			tiles.add(new Tile(TileType.GRAIN));
			tiles.add(new Tile(TileType.ORE));

		}
		tiles.add(new Tile(TileType.WOOL));
		tiles.add(new Tile(TileType.LUMBER));
		tiles.add(new Tile(TileType.GRAIN));
		Collections.shuffle(tiles);
		Collections.shuffle(inlandPos);
		int count = 0;


		for (Tile tile : tiles) {
			tile.setNumber(numbers.get(count));
			tile.xPos = inlandPos.get(count).column;
			tile.yPos = inlandPos.get(count).row;
			count++;

		}
		count = 0;
		for  (int i = 0 ; i< 18; i++ ) {
			seaTiles.add(new Tile(TileType.SEA));

		}
		for (Tile tile : seaTiles) {
			tile.xPos = inlandPos.get(count).column;
			tile.yPos = inlandPos.get(count).row;
			count++;
		}

		Tile desert = new Tile();
		desert.setType(TileType.DESERT);
		robber = new Robber(desert);
		desert.setNumber(7);
		tiles.add(desert);
		Collections.shuffle(tiles);

		habours.add(new Habour(Cost.ResourceType.BRICK, tiles.get(0),5));


	}

	public Map (ArrayList<Tile> sTiles, ArrayList<Tile> nTiles , Robber r , ArrayList<Road> roadList, ArrayList<House> houseList, ArrayList<Habour> haboursList ) {
		this.tiles = nTiles;
		this.seaTiles = sTiles;
		this.robber = r;
		this.roads = roadList;
		this.houses = houseList;
		this.habours = haboursList;
	}

	public void addRoad (Road road) {
		this.roads.add(road);
	}

	public Tile getTileByCoor(int x , int y) {
		for (Tile tile : tiles) {
			if (tile.xPos == x && tile.yPos == y) {
				return  tile;
			}
		}
		return  null;
	}
}
