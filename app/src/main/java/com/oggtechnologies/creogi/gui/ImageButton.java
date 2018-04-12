package com.oggtechnologies.creogi.gui;


import android.graphics.Canvas;
import android.graphics.Paint;

import com.oggtechnologies.creogi.Textures;

public class ImageButton extends Button {
    String texture;
    public ImageButton(String action, float left, float top, float width, float height, String texture) {
        super(action, left, top, width, height);
        this.texture = texture;
    }

    @Override
    void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(Textures.getTexture(texture, getPixelWidth()), getLeftPixel(), getTopPixel(), paint);
    }
}
