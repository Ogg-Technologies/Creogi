package com.oggtechnologies.creogi.worldGeneration;

import com.oggtechnologies.creogi.GlobalGameData;
import com.oggtechnologies.creogi.TileMap;
import com.oggtechnologies.creogi.tiles.*;

import static com.oggtechnologies.creogi.worldGeneration.WorldGenerator.layerHeights;

public class FillWorld {

    private TileMap tileMap;

    FillWorld() {
        tileMap = GlobalGameData.getTileMap();
    }

    /**
     * Fills the world with dirt under the surface outline and stone where the surface layer ends
     */
    public void fill(int[] surfaceOutline) {
        // Fills the world with stone
        for (int y = 0; y < (float) tileMap.getTileGridHeight() * layerHeights[1]; y++) {
            for (int x = 0; x < tileMap.getTileGridWidth(); x++) {
                tileMap.addTile(new Stone(x, y));
            }
        }

        // Fills the world with dirt
        for (int x = 0; x < tileMap.getTileGridWidth() - 1; x++) {
            for (float y = tileMap.getTileGridHeight() * layerHeights[1]; y < surfaceOutline[x]; y++) {
                tileMap.addTile((new Dirt(x, (int) Math.floor(y))));
            }
        }
    }
}
