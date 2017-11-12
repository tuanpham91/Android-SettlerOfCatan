package com.example.tuan.myapplication.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Cost {
	public int brick ;
	public int lumber;
	public int wool;
	public int grain;
	public int ore ;

	public Cost (int a, int b, int c, int d, int e ) {

		this.lumber = a;
		this.brick = b;
		this.wool = c;
		this.grain = d;
		this.ore = e;
	}
	
	public Cost () {
		this.brick = 0;
		this.lumber = 0;
		this.wool = 0;
		this.grain = 0;
		this.ore = 0;
	}
	
	public static Cost getCostOfHouse() {
		return new Cost(1,1,1,1,0);
	}
	public static Cost getCostOfUpgrade() {
		return new Cost(0,0,0,2,3);		
	}
	public static Cost getCostOfStreet() {
		return new Cost(1,1,0,0,0);
	}
	public static Cost getCostOfDevelopment(){
		return new Cost(0,0,1,1,1);
	}	
	public enum ResourceType {
		WOOD, WOOL, BRICK , ORE , GRAIN, ANYTHING;
	}

	public JSONObject toJSONObject() throws JSONException {
		JSONObject cObj = new JSONObject();
		cObj.put("Holz", lumber);
		cObj.put("Lehm", brick);
		cObj.put("Wolle",wool);
		cObj.put("Getreide",grain);
		cObj.put("Erz", ore);
		return  cObj;
	}
	public int getAllResources() {
		return brick + lumber + wool + grain + ore;
	}

	public static Cost getCostFromResourceType(ResourceType type) {
		Cost cost ;
		switch (type) {
			case WOOD:
				cost = new Cost(1,0,0,0,0);
				break;
			case BRICK:
				cost = new Cost(0,1,0,0,0);
				break;
			case WOOL:
				cost = new Cost(0,0,1,0,0);
				break;
			case GRAIN:
				cost = new Cost(0,0,0,1,0);
				break;
			default:
				cost = new Cost (0,0,0,0,1);
				break;
		}
		return cost;


	}
	public void removeResource(String type,Cost destination) {
		switch (type) {
			case "Holz":
				int res = this.lumber;
				this.lumber = 0;
				destination.lumber+=res;
				break;
			case "Lehm":
				int res1 = this.brick;
				this.brick = 0;
				destination.brick+= res1;
				break;
			case "Wolle":
				int res2 = this.wool;
				this.wool = 0;
				destination.wool+=res2;
				break;
			case "Getreide":
				int res3  = this.grain;
				this.grain = 0;
				destination.grain+=res3;
				break;
			case "Erz" :
				int res4 = this.ore;
				this.ore = 0;
				destination.ore += res4;
				break;
		}
	}
}
