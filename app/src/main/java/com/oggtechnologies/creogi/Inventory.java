package com.oggtechnologies.creogi;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.oggtechnologies.creogi.items.Item;

public class Inventory {

    Item[] items;

    public Inventory(int totalItems){
        items = new Item[totalItems];
    }

    /**
     * Displays this inventory on the screen
     */
    public void draw(Canvas canvas, Paint paint){

    }

    /**
     * Called if this inventory is displayed on the screen and a touch event occurred.
     * Handle changes like move items based on where the screen was pressed.
     */
    public void onTouch(MotionEvent motionEvent){

    }

    public Item[] getItems() {
        return items;
    }

    public boolean tryPickUpItem(Item item){
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null){
                items[i] = item;
                return true;
            }
        }
        return false;
    }
}
