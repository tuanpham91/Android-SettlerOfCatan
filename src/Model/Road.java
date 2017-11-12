package com.example.tuan.myapplication.Model;

public class Road extends Building {
	public Tile tile1;
	public Tile tile2;

	public Road(Tile pos1, Tile pos2) {
		this.tile1 = pos1;
		this.tile2 = pos2;
	}
	
}
