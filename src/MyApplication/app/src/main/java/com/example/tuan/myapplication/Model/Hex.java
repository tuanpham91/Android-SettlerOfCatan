package com.example.tuan.myapplication.model;

import android.graphics.Path;

import java.util.ArrayList;

/**
 * Created by Tuan on 26.05.2017.
 */

public class Hex {
    public int radius;
    public float pointX;
    public float pointY;
    public ArrayList<Line> lines;
    public Hex (int rad, float x , float y) {
        this.radius = rad;
        this.pointX = x;
        this.pointY = y;
        this.lines = new ArrayList<Line>();
    }
    public Path getPath() {
        float triangleHeight = (float) (Math.sqrt(3) * radius / 2);
        float centerX = pointX;
        float centerY = pointY;
        Path hexagonBorderPath = new Path();
        hexagonBorderPath.moveTo(centerX , centerY -radius);
        hexagonBorderPath.lineTo(centerX + triangleHeight, centerY - radius/2);
        this.lines.add(new Line(centerX,centerY-radius,centerX + triangleHeight, centerY - radius/2 ));
        hexagonBorderPath.lineTo(centerX + triangleHeight , centerY + radius/2);
        this.lines.add(new Line(centerX + triangleHeight, centerY - radius/2,centerX + triangleHeight , centerY + radius/2 ));
        hexagonBorderPath.lineTo(centerX, centerY + radius);
        this.lines.add(new Line(centerX + triangleHeight , centerY + radius/2,centerX, centerY + radius ));
        hexagonBorderPath.lineTo(centerX - triangleHeight, centerY + radius/2);
        this.lines.add(new Line(centerX, centerY + radius,centerX - triangleHeight, centerY + radius/2 ));
        hexagonBorderPath.lineTo(centerX - triangleHeight, centerY - radius/2);
        this.lines.add(new Line(centerX - triangleHeight, centerY + radius/2 ,centerX - triangleHeight, centerY - radius/2));
        hexagonBorderPath.close();
        this.lines.add(new Line(centerX - triangleHeight, centerY - radius/2,centerX , centerY -radius));
        return hexagonBorderPath;
    }


}
