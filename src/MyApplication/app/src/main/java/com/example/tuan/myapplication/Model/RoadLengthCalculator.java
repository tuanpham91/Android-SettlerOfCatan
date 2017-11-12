package com.example.tuan.myapplication.model;


import java.util.ArrayList;

/**
 * Created by Tuan on 22.06.2017.
 */

public class RoadLengthCalculator {
    public ArrayList<Road> connectedRoads;
    public static ArrayList<RoadPath> paths;
    public static  int  getMaxRoadLength(ArrayList<Road> roads) {
        paths = new ArrayList<>();
        int max = 1;
        for (int i = 0 ; i<roads.size() ; i ++ ){
            for (int k = 0 ; k < roads.size(); k++) {
                if (i == k){
                    continue;
                }
                if (twoRoadsConnected(roads.get(i),roads.get(k))) {
                    ArrayList<Road> newRoads = new ArrayList<>();
                    newRoads.add(roads.get(i));
                    newRoads.add(roads.get(k));
                    paths.add(new RoadPath(newRoads, roads.get(i), roads.get(k)));
                }
            }
        }


        for (int m = 0 ; m <paths.size() ; m++ ) {

            ArrayList<RoadPath>  toAddPaths = new ArrayList<>();
            for (Road road : roads) {
                RoadPath tempPath = new RoadPath(paths.get(m).roadsInPath,paths.get(m).start,paths.get(m).end);
                if (tempPath.roadsInPath.contains(road)) {
                    continue;
                }
                if (twoRoadsConnected(tempPath.start, road)) {
                    tempPath.addRoadToPath(road, 0);
                    toAddPaths.add(tempPath);
                    continue;
                }
                if (twoRoadsConnected(tempPath.end, road)) {
                    tempPath.addRoadToPath(road, 1);
                    toAddPaths.add(tempPath);
                }
            }
            paths.addAll(toAddPaths);
            paths.remove(paths.get(m));
        }

        for (RoadPath path : paths) {
            if (path.roadsInPath.size()>max) {
                max = path.roadsInPath.size();
            }
        }
        return  max;
    }

    public static boolean twoRoadsConnected(Road a, Road b) {
        if (a.tile1 == b.tile1) {
            if (distanceTiles(a.tile2 , b.tile2)> 2*a.tile1.radius) {
                return false;
            } else {
                return  true;
            }
        } else if (a.tile1 == b.tile2) {
            if (distanceTiles(a.tile2 , b.tile1)> 2*a.tile1.radius) {
                return false;
            } else {
                return  true;
            }
        } else {
            return false;
        }
    }

    public static double distanceTiles(Tile a, Tile b) {
        double distance = Math.sqrt(Math.pow(a.xCoor - b.xCoor,2)+Math.pow(a.yCoor-b.yCoor,2));
        return distance;
    }


}
