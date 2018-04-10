package com.oggtechnologies.creogi.entities;

import com.oggtechnologies.creogi.TileMap;

public class Mob extends Entity {
    /**
     * A Mob is an entity that thinks. NPC's like enemies and the player are mobs
     */

    private float JUMPVEL = 0.2f;
    float SPEED = 0.1f;

    private int maxJumps = 1;   // Increase for multi jump
    private int currentJumps = maxJumps;
    private int updatesWhereStationaryY;   // Before currentJumps is reset yVel has to be 0 for a certain number of updates

    public Mob(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {
        super.update();

        if (yVel <= 0 && yVel >= -GRAVITY) {   // Checks if yVel is (basically) 0 two updates in a row. If it is, the mob is on the ground
            if (updatesWhereStationaryY == 3) {   // Reset currentJumps to make the mob able to jump again
                currentJumps = maxJumps;
                updatesWhereStationaryY = 0;
            } else {
                updatesWhereStationaryY++;
            }
        } else {
            updatesWhereStationaryY = 0;
        }
    }

    void jump() {   // When called it will try to make the mob jump
        if ((currentJumps == maxJumps && yVel != -GRAVITY) | currentJumps > 0) {   // CHANGE so you cannot jump while going down if maxJumps>1
            yVel = JUMPVEL;
            currentJumps--;
        }
    }
}
