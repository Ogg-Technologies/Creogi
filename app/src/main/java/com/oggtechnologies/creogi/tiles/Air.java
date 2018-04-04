package com.oggtechnologies.creogi.tiles;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Air extends Tile{
    public Air(int x, int y){
        super(x, y, "", false);
    }

    @Override
    public void draw(float right, float bottom, float pixelSize, Canvas canvas, Paint paint) {
        //Drawing does nothing since it is air
    }
}
