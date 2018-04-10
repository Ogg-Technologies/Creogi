package com.oggtechnologies.creogi.entities;

import com.oggtechnologies.creogi.TileMap;

public class Mob extends Entity {
    /**
     * A Mob is a character that thinks. NPC's and the player
     */

    private boolean onGround;
    private int jumps = 1;   //

    public Mob(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {
        super.update();

    }
}
