package com.example.tuan.myapplication.model;

public class Road extends Building {
	public Tile tile1;
	public Tile tile2;
	public int ownerId;
	public Road(Tile pos1, Tile pos2, int id) {
		this.ownerId = id;
		this.tile1 = pos1;
		this.tile2 = pos2;
	}
	
}
