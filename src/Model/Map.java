package com.example.tuan.myapplication.Model;

import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Map {

	public ArrayList<Tile> tiles = new ArrayList<>();
	public ArrayList<Tile> seaTiles = new ArrayList<>();
	public ArrayList<Integer> numbers = new ArrayList<>();
	public ArrayList<Road> roads;
	public ArrayList<House> houses ;
	public ArrayList<Habour> habours;

	public Map () {
		houses = new ArrayList<>();
		roads = new ArrayList<>();
		seaTiles = new ArrayList<>();
		habours = new ArrayList<>();

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
		int count = 0;
		for (Tile tile : tiles) {
			tile.setNumber(numbers.get(count));
			count++;

		}

		for  (int i = 0 ; i< 18; i++ ) {
			seaTiles.add(new Tile(TileType.SEA));
		}

		Tile desert = new Tile();
		desert.setType(TileType.DESERT);
		desert.setNumber(7);
		tiles.add(desert);
		Collections.shuffle(tiles);

		habours.add(new Habour(Cost.ResourceType.BRICK, tiles.get(0),5));


	}

	public void addRoad (Road road) {
		this.roads.add(road);
	}
}
