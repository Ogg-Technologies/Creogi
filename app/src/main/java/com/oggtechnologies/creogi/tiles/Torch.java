package com.oggtechnologies.creogi.tiles;

import com.oggtechnologies.creogi.imageHandler.StaticImage;

public class Torch extends Tile{
    public Torch(int x, int y){
        super(x, y, new StaticImage("tile_dirt"), false, 15);
    }

}
