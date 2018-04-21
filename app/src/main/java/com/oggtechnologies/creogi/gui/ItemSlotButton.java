package com.oggtechnologies.creogi.gui;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.oggtechnologies.creogi.Textures;
import com.oggtechnologies.creogi.inventory.ItemSlot;
import com.oggtechnologies.creogi.items.Item;

public class ItemSlotButton extends Button {
    private ItemSlot itemSlot;
    /**
     * Creates a button that uses the image of the item it contains
     */
    public ItemSlotButton(String action, float left, float top, float width, float height, ItemSlot itemSlot) {
        super(action, left, top, width, height);
        this.itemSlot = itemSlot;
    }


    @Override
    void draw(Canvas canvas, Paint paint) {
        // Starts by drawing the outline of the button and then overlays that with the item
        super.draw(canvas, paint);
        drawImage(canvas, paint);
    }

    void drawEquipped(Canvas canvas, Paint paint) {
        // Draw the outline darker
        super.drawColor(canvas, paint, Color.argb(220, 100, 100, 100));
        drawImage(canvas, paint);
    }

    void drawImage(Canvas canvas, Paint paint){
        Item item = itemSlot.getItem();
        if (item == null){
            return;
        }
        String textureName = item.getTextureName();
        drawImage(canvas, paint, textureName, 0.8f);
    }

}