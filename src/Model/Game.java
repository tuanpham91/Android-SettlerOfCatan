package com.example.tuan.myapplication.Model;

import android.graphics.Color;

import java.util.List;
import java.util.Random;

public class Game {
	private static Player player;
	private int playerCount; 
	private static int maxPlayers = 4;
	private static int pointsToWin = 10;
	public static Map map;
	public static String version = "AndroidClient 1.0 (sepgroup01)";
	public static final String protocol = "1.0";

	public static int radius = 110;
	public Game() {
		
	}
	
	public Dice rollDice (Player player) {
		Random ran = new Random();
		int dice1 = ran.nextInt(6)+1;
		int dice2 = ran.nextInt(6)+1;
		return new Dice(dice1,dice2);		
	}
	
	public static boolean setPlayerId (int id) {
		if (player == null) {
			player = new Player(id);
			return true;
		}
		return false;
	}

	public static void setColor(String color) {
		switch (color) {
			case "Rot" :
				player.setColor("#DC143C");
				break;
			case "Orange" :
				player.setColor("#ff7700");
				break;
			case "Blau" :
				player.setColor("#0000d6");
				break;
			case "Weiss" :
				player.setColor("#ffffff");
				break;
			case "Grün" :
				player.setColor("#1dc407");
				break;
			case "Braun" :
				player.setColor("#84532a");
				break;
			default:
				player.setColor("#DC143C");
				break;
		}
	}

	public static Player getPlayer() {
		return player;
	}
}
