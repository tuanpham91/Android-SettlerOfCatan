package com.example.tuan.myapplication.model;

public class Bank {
	
	public Bank() {
		
	}
	
	public static void addCostToCost(Cost from , Cost to) {
		to.brick += from.brick;
		to.grain += from.grain;
		to.lumber += from.lumber;
		to.ore += from.ore;
		to.wool += from.wool;
	}
}	
