package com.oggtechnologies.creogi.gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.oggtechnologies.creogi.MainActivity;
import com.oggtechnologies.creogi.Textures;
import com.oggtechnologies.creogi.gui.*;
import com.oggtechnologies.creogi.inventory.ItemSlot;
import com.oggtechnologies.creogi.items.Item;

import java.util.ArrayList;

public class GUI {
    ArrayList<Button> buttons = new ArrayList<Button>();
    /**
     * GUI contains a number of buttons and returns which was pressed given a touchEvent.
     */
    public GUI(){
    }

    /**
     * Adds a button to the GUI at a location with an action
     */
    public void addButton(String action, float left, float top, float width, float height){
        buttons.add(new Button(action, left, top, width, height));
    }

    public void addImageButton(String action, float left, float top, float width, float height, String texture){
        buttons.add(new ImageButton(action, left, top, width, height, texture));
    }

    public void addItemSlotButton(String action, float left, float top, float width, float height, ItemSlot itemSlot){
        buttons.add(new ItemSlotButton(action, left, top, width, height, itemSlot));
    }

    public void draw(Canvas canvas, Paint paint, int hotbarSlotSelected){
        for (Button b : buttons){
            try {
                ItemSlotButton itemSlotButton = (ItemSlotButton) b;
                if (itemSlotButton.action == String.valueOf(hotbarSlotSelected)){
                    itemSlotButton.drawEquipped(canvas, paint);
                    continue;
                }
            } catch (ClassCastException e){
            }
            b.draw(canvas, paint);
        }
    }

    public String getButtonPressed(float xPressed, float yPressed){
        for (Button b : buttons){
            if (b.wasPressed(xPressed, yPressed)){
                return b.action;
            }
        }
        return "";
    }




}
