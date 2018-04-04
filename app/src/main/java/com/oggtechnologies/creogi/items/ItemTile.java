package com.oggtechnologies.creogi.items;

import com.oggtechnologies.creogi.tiles.Tile;

public class ItemTile extends Item{
    Tile tile;

    /**
     * An ItemTile is an item that when used on the screen places the tile it contains
     * @param tile
     */
    public ItemTile(Tile tile) {
        super(tile.getTextureName());
        this.tile = tile;
    }
}
