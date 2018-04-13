package com.oggtechnologies.creogi.tiles;

import com.oggtechnologies.creogi.imageHandler.StaticImage;

public class Grass extends Tile{
    public Grass(int x, int y){
        super(x, y, new StaticImage("tile_grass"), true);
    }
}
