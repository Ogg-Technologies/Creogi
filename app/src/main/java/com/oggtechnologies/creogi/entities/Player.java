package com.oggtechnologies.creogi.entities;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.oggtechnologies.creogi.MainActivity;
import com.oggtechnologies.creogi.gui.GUI;
import com.oggtechnologies.creogi.GlobalGameData;
import com.oggtechnologies.creogi.inventory.Inventory;
import com.oggtechnologies.creogi.Textures;
import com.oggtechnologies.creogi.imageHandler.Animation;
import com.oggtechnologies.creogi.items.Item;
import com.oggtechnologies.creogi.items.ItemTile;
import com.oggtechnologies.creogi.items.Miner;
import com.oggtechnologies.creogi.tiles.Grass;
import com.oggtechnologies.creogi.tiles.Stone;
import com.oggtechnologies.creogi.tiles.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player extends Mob {

    GUI gui;
    Inventory inventory = new Inventory(15);
    //The hotbarSlot that is currently selected
    int slotSelected = 0;

    Map<Integer, String> fingersDown = new HashMap<Integer, String>();

    Animation animation;

    public Player(float x, float y) {
        super(x, y, (float) 12/16, (float) 28/16);
        gui = new GUI();
        gui.addButton("left", 0.01f, 0.835f, 0.1f, 0.15f);
        gui.addButton("right", 0.12f, 0.835f, 0.1f, 0.15f);
        gui.addButton("jump", 0.23f, 0.835f, 0.65f, 0.15f);
        gui.addButton("use", 0.89f, 0.835f, 0.1f, 0.15f);
        gui.addImageButton("inv", 0.9f, 0.01f, 0.09f, 0.09f, "tile_grass");
        addHotbar();


        inventory.tryPickUpItem(new Miner());
        inventory.tryPickUpItem(new ItemTile(new Stone(0, 0)));
        inventory.tryPickUpItem(new ItemTile(new Grass(0, 0)));
        inventory.tryPickUpItem(new ItemTile(new Grass(0, 0)));
        inventory.tryPickUpItem(new ItemTile(new Grass(0, 0)));
        inventory.tryPickUpItem(new ItemTile(new Grass(0, 0)));
        inventory.tryPickUpItem(new ItemTile(new Stone(0, 0)));
        inventory.tryPickUpItem(new ItemTile(new Stone(0, 0)));
        inventory.tryPickUpItem(new ItemTile(new Stone(0, 0)));

        ArrayList<String> textures = new ArrayList<>();
        textures.add("player");
        animation = new Animation(textures, 10);
    }


    private void addHotbar(){
        //Adds a hotbar with the 8 first slot of the players inventory
        int numButtons = 8;
        float start = 0.15f;
        float end = 0.85f;
        float margin = 0.003f;
        float numMargins = numButtons-1;
        float buttonWidth = (end-start-numMargins*margin)/numButtons;
        for (int i = 0; i < numButtons; i++) {
            float left = start+i*(buttonWidth+margin);
            gui.addItemSlotButton(String.valueOf(i), left, 0.01f, buttonWidth, 0.0f, inventory.getItemSlots()[i]);

        }
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(
                Textures.getTexture(animation, width*GlobalGameData.getTilePixelSize()),
                GlobalGameData.mapToScreenX(x-width/2),
                GlobalGameData.mapToScreenY(y+height),
                paint);

    }

    public void drawGUI(Canvas canvas, Paint paint){
        gui.draw(canvas, paint, slotSelected);
    }

    /**
     * Called on touch event if no inventory or menu is opened
     * Handles all types of controlling the player based on where the screen was pressed
     */
    public void onTouch(MotionEvent motionEvent){
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
                if (action.matches("\\-?\\d+")){
                    slotSelected = Integer.parseInt(action);
                }
                switch (action){
                    case "left":
                        xVel = -SPEED;
                        break;
                    case "right":
                        xVel = SPEED;
                        break;
                    case "jump":
                        jump();
                        break;
                    case "use":
                        break;
                    case "":
                        //No button was pressed, the map was
                        float mapX = GlobalGameData.screenToMapX(screenX);
                        float mapY = GlobalGameData.screenToMapY(screenY);
//                        GlobalGameData.getTileMap().addTile(new Stone((int) Math.floor(mapX), (int) Math.floor(mapY)));
                        Item itemSelected = inventory.getItemSlots()[slotSelected].getItem();
                        itemSelected.mapPressed(this, mapX, mapY);

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
