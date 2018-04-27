package com.oggtechnologies.creogi.worldGeneration;

import com.oggtechnologies.creogi.GlobalGameData;
import com.oggtechnologies.creogi.TileMap;
import com.oggtechnologies.creogi.tiles.Air;
import com.oggtechnologies.creogi.tiles.Dirt;
import com.oggtechnologies.creogi.tiles.Stone;

import java.util.Arrays;

public class Smooth {

    private TileMap tileMap;

    public Smooth() {
    }

    public void surface(int[] outline, int min, int max, int roughness) {   // Smooths out the surface
        tileMap = GlobalGameData.getTileMap();
        for (int y = min; y <= max; y++) {   // Loop through the whole surface except the ends
            for (int x = 1; x < outline.length - 1; x++) {
                if (checkNeighbours(x, y) < roughness) {
                    tileMap.addTile(new Air(x, y));
                } else {
                    tileMap.addTile(new Dirt(x, y));
                }
            }
        }
    }

    /**
     * Smooths out random noise so that it looks like caves
     * @param max           Where the cavern layer begins
     * @param smoothness    The smoothness of the caves
     */
    public void caves(int max, int smoothness) {   // Creates caves by smoothing random noise out
        for (int i = 0; i < smoothness; i++) {
            for (int y = max; y >= 0; y--) {
                for (int x = 0; x < tileMap.getTileGridWidth(); x++) {   // 2 for-loops to go back and forth on the x-axis
                    caveSmoothCheck(x, y);
                }
                for (int x = tileMap.getTileGridWidth(); x <= 0; x--) {
                    caveSmoothCheck(x, y);
                }
            }
        }
    }

    private void caveSmoothCheck(int x, int y) {
        int neighbours = checkNeighbours(x, y);
        if (neighbours > 4 && tileMap.getTile(x, y).getClass().toString().equals("class com.oggtechnologies.creogi.tiles.Air")) {
            tileMap.addTile(new Stone(x, y));
        } else if (neighbours <= 4 && tileMap.getTile(x, y).getClass().toString().equals("class com.oggtechnologies.creogi.tiles.Stone")) {
            tileMap.addTile(new Air(x, y));
        }
    }


    private int checkNeighbours(int x, int y) {
        int neighbours = 0;   // Number of non-air blocks around a given block
        for (int yIndex = -1; yIndex < 2; yIndex++) {   // Check the current tiles' neighbours' state
            for (int xIndex = -1; xIndex < 2; xIndex++) {
                if (!tileMap.getTile(x + xIndex, y + yIndex).getClass().toString().equals("class com.oggtechnologies.creogi.tiles.Air")) {
                    neighbours++;
                }
            }
        }
        return neighbours;   // Returns how many tiles is around another tile
    }
}
