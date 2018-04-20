package com.oggtechnologies.creogi.tiles;

import com.oggtechnologies.creogi.imageHandler.StaticImage;

public class Dirt extends Tile{
    public Dirt(int x, int y){
        super(x, y, new StaticImage("tile_dirt"), true);
    }
}
