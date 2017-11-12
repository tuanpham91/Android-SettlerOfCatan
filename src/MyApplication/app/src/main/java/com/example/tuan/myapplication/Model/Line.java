package com.example.tuan.myapplication.model;

/**
 * Created by Tuan on 06.06.2017.
 */

public class Line {
    public float startX;
    public float startY;
    public boolean road = false;
    public float endX;
    public float endY;
    public Line (float a, float b, float c, float d) {
        this.startX = a;
        this.startY = b;
        this.endX = c;
        this.endY = d;

    }
    public boolean crossedLine(Hex a) {
        double middleX = (startX + endX)/2;
        double middleY = (startY + endY)/2;
        double distance =  Math.sqrt(Math.pow(a.pointX - middleX,2)+Math.pow(a.pointY-middleY,2));
        if  (distance < 0.95 * a.radius) {
            return true;
        } else {
            return  false;
        }
    }
}
