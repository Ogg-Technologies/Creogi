package com.oggtechnologies.creogi.inventory;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.oggtechnologies.creogi.items.Item;

public class Inventory {

    ItemSlot[] itemSlots;

    public Inventory(int totalItems){
        itemSlots = new ItemSlot[totalItems];
        for (int i = 0; i < itemSlots.length; i++) {
            itemSlots[i] = new ItemSlot();
        }
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

    public ItemSlot[] getItemSlots() {
        return itemSlots;
    }

    public boolean tryPickUpItem(Item item){
        for (int i = 0; i < itemSlots.length; i++) {
            if (itemSlots[i].getItem() == null){
                itemSlots[i].setItem(item);
                return true;
            }
        }
        return false;
    }
}
