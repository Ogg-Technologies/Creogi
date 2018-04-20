package com.oggtechnologies.creogi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.oggtechnologies.creogi.entities.Player;
import com.oggtechnologies.creogi.tiles.*;
import com.oggtechnologies.creogi.worldGeneration.WorldGenerator;


public class TileMap {
    private Tile[][] tileGrid;

    /**
     * The coordinates of the tileGrid:
     * Positive X, left to right
     * Positive Y, bottom to top
     * @param width     The width of the map
     * @param height    The height of the map
     */
    public TileMap(int width, int height){
        tileGrid = new Tile[height][width];
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                tileGrid[y][x] = new Air(x, y);
            }
        }
        for (int y = 0; y < 6; y++){
            for (int x = 0; x < width; x++){
                tileGrid[y][x]= new Grass(x, y);
            }
        }

        WorldGenerator worldGenerator = new WorldGenerator();
        worldGenerator.generateWorld();

    }

    public void addTile(Tile tile){
        try {
            Tile lastTile = tileGrid[tile.getY()][tile.getX()];
            if (lastTile != null) {
                tileGrid[tile.getY()][tile.getX()].onDestroy();
            }
            tileGrid[tile.getY()][tile.getX()] = tile;
            tile.onPlace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Tile at coordinates x=" + tile.getY() + " y=" + tile.getX() + " is outside the map");
        }
    }

    public Tile getTile(int x, int y){
        if (x<0 || x>=tileGrid[0].length || y<0 || y>=tileGrid.length) {
            return new Air(0, 0);
        }
        return tileGrid[y][x];
    }

    public void drawTiles(Canvas canvas, Paint paint){
        //Calculates the boundaries of visible tiles
        int leftmostVisibleTile = (int) Math.floor(GlobalGameData.getLeftmostVisibleCoordinate());
        int rightmostVisibleTile = (int) Math.floor(GlobalGameData.getRightmostVisibleCoordinate());
        int topmostVisibleTile = (int) Math.floor(GlobalGameData.getTopmostVisibleCoordinate());
        int bottommostVisibleTile = (int) Math.floor(GlobalGameData.getBottommostVisibleCoordinate());

        //Clamps these to the tileMap
        leftmostVisibleTile = clampToMapX(leftmostVisibleTile);
        rightmostVisibleTile = clampToMapX(rightmostVisibleTile);
        topmostVisibleTile = clampToMapY(topmostVisibleTile);
        bottommostVisibleTile = clampToMapY(bottommostVisibleTile);

        //Loops through just the visible tiles and draws them
        for (int y = topmostVisibleTile; y >= bottommostVisibleTile; y--){
            for (int x = leftmostVisibleTile; x <= rightmostVisibleTile; x++){
                Tile t = tileGrid[y][x];
                float tw = GlobalGameData.getTilePixelSize();
                t.draw(GlobalGameData.mapToScreenX(x), GlobalGameData.mapToScreenY(y), tw, canvas, paint);

                paint.setColor(Color.argb(255, 10, 10, 10));
                canvas.drawText(String.valueOf(t.getLightLevel()), GlobalGameData.mapToScreenX(x), GlobalGameData.mapToScreenY(y), paint);
            }
        }
    }

    public int getTileGridWidth(){
        System.out.println(tileGrid[0]);
        return tileGrid[0].length;
    }
    public int getTileGridHeight(){
        return tileGrid.length;
    }

    private int clampToMapX(int x){
        return Math.max(Math.min(x, tileGrid[0].length-1), 0);
    }
    private int clampToMapY(int y){
        return Math.max(Math.min(y, tileGrid.length-1), 0);
    }

}
