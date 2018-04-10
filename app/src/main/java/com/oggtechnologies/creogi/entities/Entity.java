package com.oggtechnologies.creogi.entities;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.oggtechnologies.creogi.GlobalGameData;
import com.oggtechnologies.creogi.TileMap;

public abstract class Entity {
    public float x, y;
    public float xVel, yVel;
    public float width;
    public float height;
    float GRAVITY = 0.005f;

    /**
     * An entity is something that needs to get updated every tick. E.g player, enemy, projectile
     * The coordinates (x, y) are the bottom middle of the entity
     */
    public Entity(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void update(){
        moveYWithCollisionDetection(GlobalGameData.getTileMap());
        moveXWithCollisionDetection(GlobalGameData.getTileMap());

        yVel -= GRAVITY;
        if (yVel>0.4){
            yVel = 0.4f;
        }
    }

    private void moveYWithCollisionDetection(TileMap tileMap) {
        if (yVel>0){
            // The Entity is moving up so collision should be checked at its head
            boolean willCollide = false;
            for (int xCheck = (int) Math.floor(x-width/2); xCheck < (int) Math.ceil(x+width/2); xCheck++){
                if (tileMap.getTile(xCheck, (int) Math.floor(y+yVel+height)).isCollsision()){
                    willCollide = true;
                    break;
                }
            }
            if (!willCollide){
                y = y + yVel;
            } else {
                // Sets the Entity to the coordinate right before it would collide
                y = (float) Math.floor(y+yVel+height)-height;
                yVel = 0;
            }
        } else {
            // The Entity is moving down so collisions should be checked at its feet
            boolean willCollide = false;
            for (int xCheck = (int) Math.floor(x-width/2); xCheck < (int) Math.ceil(x+width/2); xCheck++){
                if (tileMap.getTile(xCheck, (int) Math.floor(y+yVel)).isCollsision()){
                    willCollide = true;
                    break;
                }
            }
            if (!willCollide){
                y = y + yVel;
            } else {
                // Sets the Entity to the coordinate right before it would collide
                y = (float) Math.ceil(y+yVel);
                yVel = 0;
            }
        }
    }

    private void moveXWithCollisionDetection(TileMap tileMap) {
        if (xVel>0){
            // The Entity is moving right so collisions should be checked to the right
            boolean willCollide = false;
            for (int yCheck = (int) Math.floor(y); yCheck < (int) Math.ceil(y+height); yCheck++){
                if (tileMap.getTile((int) Math.floor(x+xVel+width/2), yCheck).isCollsision()){
                    willCollide = true;
                    break;
                }
            }
            if (!willCollide){
                x = x + xVel;
            } else {
                x = (float) Math.floor(x+xVel+width/2)-width/2;
            }
        } else {
            // The Entity is moving down so collisions should be checked at its feet
            boolean willCollide = false;
            for (int yCheck = (int) Math.floor(y); yCheck < (int) Math.ceil(y+height); yCheck++){
                if (tileMap.getTile((int) Math.floor(x+xVel-width/2), yCheck).isCollsision()){
                    willCollide = true;
                    break;
                }
            }
            if (!willCollide){
                x = x + xVel;
            } else {
                x = (float) Math.ceil(x+xVel-width/2)+width/2;
            }
        }
    }

    public void draw(Canvas canvas, Paint paint){

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
