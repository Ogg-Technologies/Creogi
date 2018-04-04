package com.oggtechnologies.creogi.entities;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.oggtechnologies.creogi.GUI;
import com.oggtechnologies.creogi.GlobalGameData;
import com.oggtechnologies.creogi.Inventory;
import com.oggtechnologies.creogi.MainActivity;
import com.oggtechnologies.creogi.Textures;
import com.oggtechnologies.creogi.TileMap;
import com.oggtechnologies.creogi.items.ItemTile;
import com.oggtechnologies.creogi.tiles.Stone;

import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {

    GUI gui;
    Inventory inventory = new Inventory(15);

    float JUMPVEL = 0.2f;
    float SPEED = 0.1f;
    Map<Integer, String> fingersDown = new HashMap<Integer, String>();

    public Player(float x, float y) {
        super(x, y, (float) 12/16, (float) 28/16);
        gui = new GUI();
        gui.addButton("left", 0.01f, 0.835f, 0.1f, 0.15f);
        gui.addButton("right", 0.12f, 0.835f, 0.1f, 0.15f);
        gui.addButton("jump", 0.23f, 0.835f, 0.65f, 0.15f);
        gui.addButton("use", 0.89f, 0.835f, 0.1f, 0.15f);

        inventory.tryPickUpItem(new ItemTile(new Stone(0, 0)));
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(
                Textures.getTexture("player", width*GlobalGameData.getTilePixelSize()),
                GlobalGameData.mapToScreenX(x-width/2),
                GlobalGameData.mapToScreenY(y+height),
                paint);

    }

    public void drawGUI(Canvas canvas, Paint paint){
        gui.draw(canvas, paint);
    }

    /**
     * Called on touch event if no inventory or menu is opened
     * Handles all types of controlling the player based on where the screen was pressed
     */
    public void onTouch(MotionEvent motionEvent, TileMap tileMap){
        // The id of the finger that had the event
        int fingerIndex = motionEvent.getActionIndex();
        int fingerID = motionEvent.getPointerId(fingerIndex);
        float screenX = motionEvent.getX(fingerIndex);
        float screenY = motionEvent.getY(fingerIndex);
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            // Player has touched the screen
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                //A finger was pressed
                String action = gui.getButtonPressed(screenX, screenY);
                fingersDown.put(fingerID, action);
                switch (action){
                    case "left":
                        xVel = -SPEED;
                        break;
                    case "right":
                        xVel = SPEED;
                        break;
                    case "jump":
                        yVel = JUMPVEL;
                        break;
                    case "use":
                        break;
                    case "":
                        //No button was pressed, the map was
                        float mapX = GlobalGameData.screenToMapX(screenX);
                        float mapY = GlobalGameData.screenToMapY(screenY);
                        System.out.println("mapx: " + mapX);
                        System.out.println("screenx : " + screenX);
                        System.out.println("playerscreenx: " + MainActivity.getGameViewWidth()/2);
                        tileMap.addTile(new Stone((int) Math.floor(mapX), (int) Math.floor(mapY)));
                        break;
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                //A finger was released
                switch (fingersDown.get(fingerID)){
                    case "left":
                    case "right":
                        xVel = 0;
                        break;
                    case "jump":
                        break;
                    case "use":
                        break;
                    case "":
                        //No button was pressed, the map was
                        break;
                }
                break;
        }
    }
}
