package com.oggtechnologies.creogi.gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.oggtechnologies.creogi.MainActivity;
import com.oggtechnologies.creogi.Textures;
import com.oggtechnologies.creogi.gui.*;

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




}
