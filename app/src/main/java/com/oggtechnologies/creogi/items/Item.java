package com.oggtechnologies.creogi.items;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.oggtechnologies.creogi.Textures;
import com.oggtechnologies.creogi.entities.Player;

public class Item {
    String textureName;
    /**
     * This is the baseclass for every item. Other items extends this. An item is an object in an inventory
     */
    public Item(String textureName){
        this.textureName = textureName;
    }

    /**
     * Draws how this item looks in an inventory
     */
    public void draw(float right, float bottom, float pixelSize, Canvas canvas, Paint paint){
        if (textureName != null){
            canvas.drawBitmap(Textures.getTexture(textureName, pixelSize), right, bottom-pixelSize, paint);
        } else {
            paint.setColor(Color.argb(255, 255, 0, 0));
            canvas.drawRect(right, bottom-pixelSize, right+pixelSize, bottom, paint);
        }
    }

    /**
     * Called when this item is equipped and the use key was pressed
     */
    public void usePressed(Player player){

    }

    /**
     * Called when this item is equipped and the use key was released
     * Useful for actions such as charging and firing a bow
     */
    public void useReleased(Player player){

    }

    /**
     * Called when this item is equipped and a coordinate on the map was pressed
     */
    public void mapPressed(Player player, float x, float y){

    }

    /**
     * Called when this item is equipped and a finger on the map was released
     */
    public void mapReleased(Player player, float x, float y){

    }

    /**
     * Called when this item is equipped
     */
    public void equipped(){

    }

    /**
     * Called when this item is unequipped
     */
    public void unequipped(){

    }
}
