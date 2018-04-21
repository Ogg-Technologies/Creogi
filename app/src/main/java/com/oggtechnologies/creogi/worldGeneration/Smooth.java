package com.oggtechnologies.creogi.worldGeneration;

import com.oggtechnologies.creogi.GlobalGameData;
import com.oggtechnologies.creogi.TileMap;
import com.oggtechnologies.creogi.tiles.Air;

public class Smooth {

    private TileMap tileMap;

    public Smooth() {
    }

    public void surface(int[] outline) {     // Smooths out the surface
        tileMap = GlobalGameData.getTileMap();
        boolean[] change = new boolean[outline.length];

        for (int i = 1; i < outline.length - 1; i++) {   // Loop through the whole surface except the ends
            int neighbours = checkNeighbours(i, outline[i]);

            if (neighbours >= 5) {
                change[i] = true;
            } else {
                change[i] = false;
            }
        }
    }

    private int checkNeighbours(int x, int y) {
        int neighbours = 0;   // Number of blocks around a surface block

        for (int yIndex = -1; yIndex < 2; yIndex++) {   // Check the current tiles' neighbours' state
            for (int xIndex = -1; xIndex < 2; xIndex++) {

                if (tileMap.getTile(x, y) != new Air(0, 0)) {
                    neighbours++;
                }
            }
        }
        return neighbours;
    }
}
