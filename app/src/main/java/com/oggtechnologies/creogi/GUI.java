package com.oggtechnologies.creogi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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

    public void draw(Canvas canvas, Paint paint){
        paint.setColor(Color.argb(150, 50, 50, 50));
        for (Button b : buttons){
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

    private class Button{
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
        private Button(String action, float left, float top, float width, float height) {
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
        private Boolean wasPressed(float xPixel, float yPixel){
            float xScreenFrac = xPixel/MainActivity.getGameViewWidth();
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

        private void draw(Canvas canvas, Paint paint){
            float leftPixel = left*MainActivity.getGameViewWidth();
            float topPixel = top*MainActivity.getGameViewHeight();
            float rightPixel = (left+width)*MainActivity.getGameViewWidth();
            float bottomPixel = (top+height)*MainActivity.getGameViewHeight();
            canvas.drawRect(leftPixel, topPixel, rightPixel, bottomPixel, paint);

        }

    }
}
