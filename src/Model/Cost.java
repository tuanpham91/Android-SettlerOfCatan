package com.example.tuan.myapplication.Model;

public class Cost {
	public int brick ;
	public int lumber;
	public int wool;
	public int grain;
	public int ore ;

	public Cost (int a, int b, int c, int d, int e ) {
		this.brick = a;
		this.lumber = b;
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
}
