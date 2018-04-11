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
    private boolean resetJumpsNextUpdate;   // Before currentJumps is reset yVel has to be -GRAVITY for a 2 updates in a row

    public Mob(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {
        super.update();

        if (yVel == -GRAVITY) {   // Checks if yVel is -GRAVITY two updates in a row. If it is, the mob is on the ground
            if (resetJumpsNextUpdate) {   // Reset currentJumps to make the mob able to jump again
                currentJumps = maxJumps;
                resetJumpsNextUpdate = false;
            } else {
                resetJumpsNextUpdate = true;
            }
        } else {
            resetJumpsNextUpdate = false;
            if (currentJumps == maxJumps) {   // This is for when the mob has walked over an edge and
                currentJumps--;
            }
        }
    }

    void jump() {   // When called it will try to make the mob jump
        if (currentJumps > 0) {
            yVel = JUMPVEL;
            currentJumps--;
        }
    }
}
