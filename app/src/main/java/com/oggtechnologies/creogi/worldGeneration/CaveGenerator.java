package com.oggtechnologies.creogi.worldGeneration;

import com.oggtechnologies.creogi.GlobalGameData;
import com.oggtechnologies.creogi.TileMap;
import com.oggtechnologies.creogi.tiles.Air;

import java.util.Random;

import static com.oggtechnologies.creogi.worldGeneration.WorldGenerator.layerHeights;


public class CaveGenerator {

    private TileMap tileMap;


    public CaveGenerator() {
        tileMap = GlobalGameData.getTileMap();
    }

    public void generate(int smoothness) {
        Random rand = GlobalGameData.getSeededRandom();
        int topOfLayer = (int) (tileMap.getTileGridHeight() * layerHeights[1]);

        for (int y = 0; y < topOfLayer; y++) {
            for (int x = 0; x < tileMap.getTileGridWidth(); x++) {
                if (rand.nextBoolean()) {
                    tileMap.addTile(new Air(x, y));
                }
            }
        }

        WorldGenerator.smooth.caves(topOfLayer, smoothness);   // Smooths the caves
    }
}
