package com.oggtechnologies.creogi.items;

import com.oggtechnologies.creogi.GlobalGameData;
import com.oggtechnologies.creogi.entities.Player;
import com.oggtechnologies.creogi.imageHandler.Image;
import com.oggtechnologies.creogi.imageHandler.StaticImage;
import com.oggtechnologies.creogi.tiles.Air;

/**
 * Created by Oskar on 2018-04-19.
 */

public class Miner extends Item {
    public Miner() {
        super(new StaticImage("tile_dirt"));
    }

    @Override
    public void mapPressed(Player player, float x, float y) {
        GlobalGameData.getTileMap().addTile(new Air((int) Math.floor(x), (int) Math.floor(y)));
    }
}
