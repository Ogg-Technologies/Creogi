package com.oggtechnologies.creogi.items;

import com.oggtechnologies.creogi.GlobalGameData;
import com.oggtechnologies.creogi.entities.Player;
import com.oggtechnologies.creogi.tiles.Tile;

public class ItemTile extends Item{
    Tile tile;

    /**
     * An ItemTile is an item that when used on the screen places the tile it contains
     * @param tile
     */
    public ItemTile(Tile tile) {
        super(tile.getImage());
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }

    @Override
    public void mapPressed(Player player, float x, float y) {

        tile.setPos((int) Math.floor(x), (int) Math.floor(y));
        GlobalGameData.getTileMap().addTile(tile);
    }
}
