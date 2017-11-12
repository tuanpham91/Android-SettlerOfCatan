package com.example.tuan.myapplication.model;

import java.util.ArrayList;

/**
 * Created by Tuan on 23.06.2017.
 */

public class RoadPath {
    public Road start;
    public Road end ;
    public ArrayList<Road> roadsInPath;

    public RoadPath (ArrayList<Road> roads, Road start , Road end) {
        // 1 for end, 0 for start , before this please check if Roads already inside arrays.
        this.roadsInPath= roads;
        this.start = start;
        this.end = end;

    }


    public void addRoadToPath(Road nextRoad, int i) {
        if (i == 0) {
            this.start = nextRoad;
        } else if ( i == 1) {
            this.end = nextRoad;
        }
        this.roadsInPath.add(nextRoad);
    }


}
