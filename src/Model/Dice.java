package com.example.tuan.myapplication.Model;

import java.util.Random;

public class Dice {
	public int dice1;
	public int dice2;
	
	public Dice (int a, int b) {
		this.dice1 = a;
		this.dice2 = b;
	}

	public static Dice randomDice() {
		Random rn = new Random();

		int a = rn.nextInt(Integer.MAX_VALUE) %6+1;
		int b = rn.nextInt(Integer.MAX_VALUE )%6+1;
		return new Dice(a,b);
	}
}
