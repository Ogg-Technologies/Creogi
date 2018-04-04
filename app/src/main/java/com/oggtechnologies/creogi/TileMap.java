package com.oggtechnologies.creogi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.oggtechnologies.creogi.entities.Player;
import com.oggtechnologies.creogi.tiles.*;


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
                addTile(new Air(x, y));
            }
        }
        addTile(new Stone(3, 0));
        addTile(new Stone(4, 0));
        addTile(new Stone(5, 0));
        addTile(new Stone(6, 0));
        addTile(new Stone(3, 4));
        addTile(new Stone(3, 3));
        addTile(new Stone(3, 2));
        addTile(new Stone(3, 1));
        addTile(new Stone(5, 5));
        addTile(new Stone(1, 5));
    }

    public void addTile(Tile tile){
        try {
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
        for (int y = tileGrid.length-1; y >= 0; y--){
            for (int x = 0; x < tileGrid[y].length; x++){
                Tile t = tileGrid[y][x];
                float tw = GlobalGameData.getTilePixelSize();
                t.draw(GlobalGameData.mapToScreenX(x), GlobalGameData.mapToScreenY(y), tw, canvas, paint);
            }
        }
    }

}
