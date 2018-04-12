package com.oggtechnologies.creogi.gui;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.oggtechnologies.creogi.MainActivity;

public class Button{
    String action;
    float left;
    float top;
    float width;
    float height;

    /**
     * Creates a button
     * All button coordinates are fractions of the full screen
     * This means that coordinates are always numbers between 0 and 1
     * @param action    A string that gets returned when the button gets pressed
     * @param left      The x coordinate of the left side of the button as a fraction of screen width
     * @param top       The y coordinate of the top side of the button as a fraction of screen height
     * @param width     The width of the button as a fraction of screen width
     * @param height    The height of the button as a fraction of screen height
     */
    public Button(String action, float left, float top, float width, float height) {
        this.action = action;
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    /**
     * Checks if a pixel is within this button
     * @param xPixel    The x coordinate of a pixel
     * @param yPixel    The y coordiante of a pixel
     * @return          True if the given pixel is within this button, else false
     */
    public Boolean wasPressed(float xPixel, float yPixel){
        float xScreenFrac = xPixel/ MainActivity.getGameViewWidth();
        float yScreenFrac = yPixel/MainActivity.getGameViewHeight();
        if (xScreenFrac>left && xScreenFrac<left+width){
            //X coordinate is within the button
            if (yScreenFrac>top && yScreenFrac<top+height){
                //Y coordinate is within the button
                return true;
            }
        }
        return false;
    }

    void draw(Canvas canvas, Paint paint){
        float leftPixel = getLeftPixel();
        float topPixel = getTopPixel();
        float rightPixel = leftPixel+getPixelWidth();
        float bottomPixel = topPixel*getPixelHeight();
        canvas.drawRect(leftPixel, topPixel, rightPixel, bottomPixel, paint);
    }

    float getTopPixel() {
        return top* MainActivity.getGameViewHeight();
    }

    float getLeftPixel() {
        return left* MainActivity.getGameViewWidth();
    }

    float getPixelWidth() {
        return width* MainActivity.getGameViewWidth();
    }

    float getPixelHeight() {
        return height* MainActivity.getGameViewHeight();
    }

}