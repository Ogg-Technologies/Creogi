package com.oggtechnologies.creogi.tiles;

import com.oggtechnologies.creogi.imageHandler.StaticImage;


public class Stone extends Tile{
    public Stone(int x, int y){
        super(x, y, new StaticImage("tile_stone"), true);
    }


}
