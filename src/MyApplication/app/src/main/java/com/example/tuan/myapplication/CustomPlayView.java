package com.example.tuan.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

import com.example.tuan.myapplication.model.Game;
import com.example.tuan.myapplication.model.Habour;
import com.example.tuan.myapplication.model.Hex;
import com.example.tuan.myapplication.model.House;
import com.example.tuan.myapplication.model.Line;
import com.example.tuan.myapplication.model.Road;
import com.example.tuan.myapplication.model.Tile;


import com.example.tuan.myapplication.model.Map;
import com.example.tuan.myapplication.model.TileType;

import java.util.ArrayList;

/**
 * Created by Tuan on 26.05.2017.
 */

public class CustomPlayView extends ImageView {
    public static int radius = 110;
    public double scrollerX;
    public double scrollerY;
    public float cursorX = 0;
    public float cursorY = 0;
    public House selectedHouse;
    public ArrayList<Bitmap> bitmaps = new ArrayList<>();
    public Tile lastTile;
    public Tile beforeLastTile;
    public Tile beforeTwoTile;
    public Map map;
    public boolean test = true;
    private ScaleGestureDetector scaleDetector;

    public CustomPlayView(Context context) {
        super(context);
        lastTile = null;
        beforeLastTile = null;
        scrollerX = 400;
        scrollerY = 80;
        this.map = Game.map;
        setBackgroundResource(R.drawable.ocean_background);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.grain_tile);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.wood_tile);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.ore_tile);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.brick_tile);
        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.grass_tile);
        Bitmap bitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.desert_tile);
        Bitmap bitmap6 = BitmapFactory.decodeResource(getResources(), R.drawable.sea_tile);
        bitmaps.add(bitmap);
        bitmaps.add(bitmap1);
        bitmaps.add(bitmap2);
        bitmaps.add(bitmap3);
        bitmaps.add(bitmap4);
        bitmaps.add(bitmap5);
        bitmaps.add(bitmap6);

        init();

        this.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        cursorX = event.getX();
                        cursorY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        cursorX = event.getX();
                        cursorY = event.getY();
                    case MotionEvent.ACTION_MOVE:
                        scrollerX = scrollerX + (event.getX() - cursorX) / 2;
                        scrollerY = scrollerY + (event.getY() - cursorY) / 2;
                        cursorX = event.getX();
                        cursorY = event.getY();
                        invalidate();
                        break;
                }
                return true;
            }
        });


        invalidate();
    }

    public static Bitmap getCroppedBitmap(int index, Tile tile, Bitmap bitmap) {

        Bitmap finalBitmap = Bitmap.createScaledBitmap(bitmap, (int) (Math.sqrt(3) * tile.radius), tile.radius * 2, false);

        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(),
                finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        final Rect rect = new Rect(0, 0, finalBitmap.getWidth(),
                finalBitmap.getHeight());

        canvas.drawARGB(0, 0, 0, 0);

        canvas.drawPath(tile.hex.getPath(), paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, rect, paint);
        paint.setXfermode(null);


        paint.setStrokeWidth(radius / 20);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#ffa419"));

        if (tile.selected) {
            paint.setColor(Color.RED);
        } else {
            paint.setColor(Color.parseColor("#ffa419"));
            if (tile.type == TileType.SEA) {
                paint.setColor(Color.TRANSPARENT);
            }
        }

        for (Line line : tile.hex.lines) {
            canvas.drawLine(line.startX, line.startY, line.endX, line.endY, paint);
        }
        paint.setColor(Color.DKGRAY);
        paint.setStrokeWidth(radius / 10);
        for (Line line : tile.hex.lines) {
            if (line.road) {
                canvas.drawLine(line.startX, line.startY, line.endX, line.endY, paint);
            }
        }
        if (tile.type != TileType.SEA) {
            paint.setStrokeWidth(radius / 40);
            paint.setColor(Color.parseColor("#182fff"));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(tile.hex.pointX, tile.hex.pointY, (float) (tile.radius / 4.5), paint);
            paint.setColor(Color.parseColor("#ffa419"));
            paint.setTextSize(radius / 3);
            paint.setTypeface(Typeface.create("Arial", Typeface.BOLD));
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(String.valueOf(tile.number), tile.hex.pointX, tile.hex.pointY + radius / 6, paint);
        }
        return output;

    }

    public static int fromTileToBitmap(Tile a) {
        if (a.type == TileType.GRAIN) {
            return 0;
        }
        if (a.type == TileType.LUMBER) {
            return 1;
        }
        if (a.type == TileType.ORE) {
            return 2;
        }
        if (a.type == TileType.BRICK) {
            return 3;
        }
        if (a.type == TileType.WOOL) {
            return 4;
        }
        if (a.type == TileType.DESERT) {
            return 5;
        }
        if (a.type == TileType.SEA) {
            return 6;
        }
        return 0;
    }

    public void setScaleFactor(int scale) {

        radius = (int) (Math.round(110 * (1 + ((float) scale) / 100)));
        this.init();
        invalidate();
    }

    public void init() {
        //Initialize images for Tile
        for (Tile tile : map.tiles) {
            tile.radius = radius;
            //Log.i("RADIUS",String.valueOf(tile.radius));
            tile.hex = new Hex(radius - 3, (float) (radius * Math.sqrt(3) / 2), radius);
            tile.tileBitmap = getCroppedBitmap(1, tile, bitmaps.get(fromTileToBitmap(tile)));
        }

        //Initialize images for SeaTile
        for (Tile tile : map.seaTiles) {
            tile.radius = radius;
            tile.hex = new Hex(tile.radius - 3, (float) (tile.radius * Math.sqrt(3) / 2), tile.radius);
            tile.tileBitmap = getCroppedBitmap(1, tile, bitmaps.get(fromTileToBitmap(tile)));
        }

        //Initialize images for HabourTile
        for (Habour hab : map.habours) {
            Bitmap bitm = BitmapFactory.decodeResource(getResources(), R.drawable.wood_trade_icon);
            hab.bitmap = Bitmap.createScaledBitmap(bitm, radius / 2, radius / 2, false);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();

        int count = 0;
        int countSea = 0;
        for (int i = 0; i < 4; i++) {
            canvas.drawBitmap(map.seaTiles.get(countSea).tileBitmap, (int) (scrollerX + (i + 0.5) * Math.sqrt(3) * (radius)), (int) (scrollerY + (-1) * 1.5 * (radius)), paint);
            countSea++;
        }
        canvas.drawBitmap(map.seaTiles.get(countSea).tileBitmap, (int) (scrollerX + (0) * Math.sqrt(3) * (radius)), (int) (scrollerY + (0) * 1.5 * (radius)), paint);
        countSea++;
        for (int i = 0; i < 3; i++) {
            canvas.drawBitmap(map.tiles.get(count).tileBitmap, (int) (scrollerX + (i + 1) * Math.sqrt(3) * (radius)), (int) (scrollerY + (0) * 1.5 * (radius)), paint);
            map.tiles.get(count).setCoor(scrollerX + (i + 1) * Math.sqrt(3) * (radius) + radius * Math.sqrt(3) / 2, scrollerY + (0) * 1.5 * (radius) + radius);
            count++;
        }
        canvas.drawBitmap(map.seaTiles.get(countSea).tileBitmap, (int) (scrollerX + (4) * Math.sqrt(3) * (radius)), (int) (scrollerY + (0) * 1.5 * (radius)), paint);
        countSea++;

        canvas.drawBitmap(map.seaTiles.get(countSea).tileBitmap, (int) (scrollerX + (-0.5) * Math.sqrt(3) * (radius)), (int) (scrollerY + (1) * 1.5 * (radius)), paint);
        countSea++;
        for (int i = 0; i < 4; i++) {
            canvas.drawBitmap((map.tiles.get(count).tileBitmap), (int) (scrollerX + (i + 0.5) * Math.sqrt(3) * (radius)), (int) (scrollerY + (1) * 1.5 * (radius)), paint);
            map.tiles.get(count).setCoor(scrollerX + (i + 0.5) * Math.sqrt(3) * (radius) + radius * Math.sqrt(3) / 2, scrollerY + (1) * 1.5 * (radius) + radius);
            count++;
        }
        canvas.drawBitmap(map.seaTiles.get(countSea).tileBitmap, (int) (scrollerX + (4.5) * Math.sqrt(3) * (radius)), (int) (scrollerY + (1) * 1.5 * (radius)), paint);
        countSea++;

        canvas.drawBitmap(map.seaTiles.get(countSea).tileBitmap, (int) (scrollerX + (-1) * Math.sqrt(3) * (radius)), (int) (scrollerY + (2) * 1.5 * (radius)), paint);
        countSea++;
        for (int i = 0; i < 5; i++) {
            canvas.drawBitmap(map.tiles.get(count).tileBitmap, (int) (scrollerX + (i) * Math.sqrt(3) * (radius)), (int) (scrollerY + (2) * 1.5 * (radius)), paint);
            map.tiles.get(count).setCoor(scrollerX + (i) * Math.sqrt(3) * (radius) + radius * Math.sqrt(3) / 2, scrollerY + (2) * 1.5 * (radius) + radius);
            count++;
        }
        canvas.drawBitmap(map.seaTiles.get(countSea).tileBitmap, (int) (scrollerX + (5) * Math.sqrt(3) * (radius)), (int) (scrollerY + (2) * 1.5 * (radius)), paint);
        countSea++;

        canvas.drawBitmap(map.seaTiles.get(countSea).tileBitmap, (int) (scrollerX + (-0.5) * Math.sqrt(3) * (radius)), (int) (scrollerY + (3) * 1.5 * (radius)), paint);
        countSea++;
        for (int i = 0; i < 4; i++) {
            canvas.drawBitmap(map.tiles.get(count).tileBitmap, (int) (scrollerX + (i + 0.5) * Math.sqrt(3) * (radius)), (int) (scrollerY + (3) * 1.5 * (radius)), paint);
            map.tiles.get(count).setCoor(scrollerX + (i + 0.5) * Math.sqrt(3) * (radius) + radius * Math.sqrt(3) / 2, scrollerY + (3) * 1.5 * (radius) + radius);
            count++;
        }
        canvas.drawBitmap(map.seaTiles.get(countSea).tileBitmap, (int) (scrollerX + (4.5) * Math.sqrt(3) * (radius)), (int) (scrollerY + (3) * 1.5 * (radius)), paint);
        countSea++;

        canvas.drawBitmap(map.seaTiles.get(countSea).tileBitmap, (int) (scrollerX + (0) * Math.sqrt(3) * (radius)), (int) (scrollerY + (4) * 1.5 * (radius)), paint);
        countSea++;
        for (int i = 0; i < 3; i++) {
            canvas.drawBitmap(map.tiles.get(count).tileBitmap, (int) (scrollerX + (i + 1) * Math.sqrt(3) * (radius)), (int) (scrollerY + (4) * 1.5 * (radius)), paint);
            map.tiles.get(count).setCoor(scrollerX + (i + 1) * Math.sqrt(3) * (radius) + radius * Math.sqrt(3) / 2, scrollerY + (4) * 1.5 * (radius) + radius);
            count++;
        }
        canvas.drawBitmap(map.seaTiles.get(countSea).tileBitmap, (int) (scrollerX + (4) * Math.sqrt(3) * (radius)), (int) (scrollerY + (4) * 1.5 * (radius)), paint);
        countSea++;

        for (int i = 0; i < 4; i++) {
            canvas.drawBitmap(map.seaTiles.get(countSea).tileBitmap, (int) (scrollerX + (i + 0.5) * Math.sqrt(3) * (radius)), (int) (scrollerY + (5) * 1.5 * (radius)), paint);
            countSea++;
        }

        if (map.houses.size() > 0) {
            for (House house : map.houses) {
                house.updateCoor();
                canvas.drawBitmap(house.bitmap, (int) house.xCoor - house.width / 2, (int) house.yCoor - house.height / 2, paint);
            }
        }

        for (Habour hab : map.habours) {
            hab.setCoor();
            canvas.drawBitmap(hab.bitmap, (int) hab.xCoor - radius / 2, (int) hab.yCoor - radius / 3, paint);
        }

    }

    public boolean buildRoadOnSelectedTiles() {
        if (lastTile == beforeLastTile) {
            return false;
        }
        if (isBuildStreetOk(lastTile, beforeLastTile)) {

            Road newRoad = new Road(lastTile, beforeLastTile);
            map.addRoad(newRoad);
            drawRoad(newRoad);
            lastTile.tileBitmap = getCroppedBitmap(2, lastTile, bitmaps.get(fromTileToBitmap(lastTile)));
            beforeLastTile.tileBitmap = getCroppedBitmap(2, beforeLastTile, bitmaps.get(fromTileToBitmap(beforeLastTile)));
            invalidate();
            return true;
        } else {
            invalidate();
            return false;
        }

    }

    public boolean setHouseSelected(double x, double y) {
        for (House house : map.houses) {
            if (Math.sqrt(Math.pow(x - house.xCoor, 2) + Math.pow(y - house.yCoor, 2)) <= house.width * 2) {
                selectedHouse = house;
                return true;
            }
        }
        return false;
    }

    public boolean buildHouseOnSelectedTiles() {
        if (!isBuildHouseOk(beforeLastTile, beforeTwoTile, lastTile)) {
            return false;
        }
        House newHouse = new House(beforeLastTile, lastTile, beforeTwoTile);

        newHouse.bitmap = setHouseBitmap(newHouse, null);


        map.houses.add(newHouse);

        return true;
    }

    public int get2TileSelected(double x, double y) {

        for (Tile tile : map.tiles) {
            if (Math.sqrt(Math.pow(x - tile.xCoor, 2) + Math.pow(y - tile.yCoor, 2)) <= radius) {
                if (beforeLastTile != null) {
                    beforeLastTile.selected = false;
                    beforeLastTile.tileBitmap = getCroppedBitmap(2, beforeLastTile, bitmaps.get(fromTileToBitmap(beforeLastTile)));
                } else {

                }

                beforeLastTile = lastTile;
                if (beforeLastTile != null) {
                    beforeLastTile.tileBitmap = getCroppedBitmap(2, beforeLastTile, bitmaps.get(fromTileToBitmap(beforeLastTile)));
                }
                lastTile = tile;
                lastTile.selected = true;
                lastTile.tileBitmap = getCroppedBitmap(2, lastTile, bitmaps.get(fromTileToBitmap(lastTile)));

                invalidate();

            }
        }
        return -1;
    }

    public boolean upgradeSelectedHouse() {
        this.selectedHouse.bitmap = setHouseBitmap(selectedHouse, null);
        invalidate();
        return true;
    }

    public void drawRoad(Road road) {
        Tile a = road.tile1;
        Tile b = road.tile2;
        double distance = Math.sqrt(Math.pow(a.xCoor - b.xCoor, 2) + Math.pow(a.yCoor - b.yCoor, 2));

        if (a.xCoor < b.xCoor) {
            if ((b.xCoor - a.xCoor) >= 0.9 * distance) {
                a.hex.lines.get(1).road = true;
                b.hex.lines.get(4).road = true;
            } else {
                if (a.yCoor > b.yCoor) {
                    a.hex.lines.get(0).road = true;
                    b.hex.lines.get(3).road = true;
                } else {
                    a.hex.lines.get(2).road = true;
                    b.hex.lines.get(5).road = true;
                }
            }
        } else {
            if ((a.xCoor - b.xCoor) >= 0.9 * distance) {
                b.hex.lines.get(1).road = true;
                a.hex.lines.get(4).road = true;
            } else {
                if (a.yCoor > b.yCoor) {
                    a.hex.lines.get(5).road = true;
                    b.hex.lines.get(2).road = true;
                } else {
                    a.hex.lines.get(3).road = true;
                    b.hex.lines.get(0).road = true;
                }
            }
        }

    }

    public int get3TileSelected(double x, double y) {
        int res = -1;
        for (Tile tile : map.tiles) {
            if (Math.sqrt(Math.pow(x - tile.xCoor, 2) + Math.pow(y - tile.yCoor, 2)) <= radius) {

                if (beforeTwoTile != null) {
                    beforeTwoTile.selected = false;
                    beforeTwoTile.tileBitmap = getCroppedBitmap(2, beforeTwoTile, bitmaps.get(fromTileToBitmap(beforeTwoTile)));
                }
                beforeTwoTile = beforeLastTile;
                beforeLastTile = lastTile;
                if (beforeTwoTile != null) {
                    beforeTwoTile.tileBitmap = getCroppedBitmap(2, beforeTwoTile, bitmaps.get(fromTileToBitmap(beforeTwoTile)));
                }
                lastTile = tile;
                lastTile.selected = true;
                lastTile.tileBitmap = getCroppedBitmap(2, lastTile, bitmaps.get(fromTileToBitmap(lastTile)));

                invalidate();

            }
        }
        return 1;
    }

    public void resetSelected() {
        int count = 0;

        for (Tile tile : map.tiles) {
            tile.selected = false;
            tile.tileBitmap = getCroppedBitmap(count, tile, bitmaps.get(fromTileToBitmap(tile)));
            count++;
        }


        this.lastTile = null;
        this.beforeLastTile = null;
        this.beforeTwoTile = null;

        invalidate();
    }

    public boolean isBuildHouseOk(Tile a, Tile b, Tile c) {
        if (a == b || b == c || c == a) {
            return false;
        }
        double distance1 = Math.sqrt(Math.pow(a.xCoor - b.xCoor, 2) + Math.pow(a.yCoor - b.yCoor, 2));
        double distance2 = Math.sqrt(Math.pow(a.xCoor - c.xCoor, 2) + Math.pow(a.yCoor - c.yCoor, 2));
        double distance3 = Math.sqrt(Math.pow(c.xCoor - b.xCoor, 2) + Math.pow(c.yCoor - b.yCoor, 2));


        if (distance1 > 2 * radius || distance2 > 2 * radius || distance3 > 2 * radius) {
            return false;
        }

        for (House house : map.houses) {
            if (house.surroundingTiles.contains(a) && house.surroundingTiles.contains(b) && house.surroundingTiles.contains(c)) {
                return false;
            }
        }
        invalidate();
        return true;
    }

    public boolean isBuildStreetOk(Tile a, Tile b) {
        double distance = Math.sqrt(Math.pow(a.xCoor - b.xCoor, 2) + Math.pow(a.yCoor - b.yCoor, 2));

        if (distance > 2 * radius) {
            return false;
        }

        return true;
    }

    public Bitmap setHouseBitmap(House house, Color color) {

        Bitmap input;
        if (house.getLevel() == 1) {
            input = BitmapFactory.decodeResource(getResources(), R.drawable.new_settlement_icon);
        } else {
            input = BitmapFactory.decodeResource(getResources(), R.drawable.new_city_icon);
        }
        int w = radius / 2;
        Bitmap finalBitmap = Bitmap.createScaledBitmap(input, w, w, true);

        Bitmap output = Bitmap.createBitmap(house.width,
                house.height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#0050ff"));
        if (color != null) {
            paint.setColor(Color.BLUE);
        }
        canvas.drawCircle(house.width / 2, house.height / 2, house.height / 2, paint);
        canvas.drawBitmap(finalBitmap, (house.height - w) / 2, (house.height - w) / 2, paint);
        return output;
    }

}
