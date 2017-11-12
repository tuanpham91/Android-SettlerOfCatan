package com.example.tuan.myapplication.model;

import android.graphics.Bitmap;

import java.security.Key;
import java.util.ArrayList;

/**
 * Created by Terina on 10.07.17.
 */

public class Robber extends Building {
    private int level;
    public double xCoor;
    public double yCoor;
    public int width = 70;
    public int height = 70;
    public Tile currentsTile;

    public Bitmap bitmap;
    public ArrayList<Tile> surroundingTiles;
    public Robber (Tile desertTile) {
        this.currentsTile = desertTile;
    }

    public void moveRobber(Tile newTile) {
        this.currentsTile = newTile;
        this.xCoor= newTile.xPos;
        this.yCoor = newTile.yPos;

    }
}
