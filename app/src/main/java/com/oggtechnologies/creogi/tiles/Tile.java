package com.oggtechnologies.creogi.tiles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.oggtechnologies.creogi.Textures;
import com.oggtechnologies.creogi.imageHandler.Image;

public abstract class Tile {
    int x;
    int y;
    Image image;
    boolean collsision;

    /**
     * A tile is a stationary square on the map.
     * The coordiantes is the bottom left position of the map.
     * When standing on the ground, a block going from your feet to your waist has the same Y coordinate as you
     * @param image   The Image that should be drawn at the tile.
     */
    public Tile(int x, int y, Image image, boolean collision) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.collsision = collision;
    }

    public void onPlace(){

    }

    public void onDestroy(){
    }

    public void draw(float right, float bottom, float pixelSize, Canvas canvas, Paint paint){
        if (image != null){
            canvas.drawBitmap(Textures.getTexture(image, pixelSize), right, bottom-pixelSize, paint);
        } else {
            paint.setColor(Color.argb(255, 255, 0, 0));
            canvas.drawRect(right, bottom-pixelSize, right+pixelSize, bottom, paint);
        }
    }

    public boolean isCollsision() {
        return collsision;
    }

    public String getType(){
        return this.getClass().getSimpleName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
