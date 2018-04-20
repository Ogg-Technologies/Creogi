package com.oggtechnologies.creogi.tiles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.oggtechnologies.creogi.GlobalGameData;
import com.oggtechnologies.creogi.Textures;
import com.oggtechnologies.creogi.TileMap;
import com.oggtechnologies.creogi.imageHandler.Image;

public abstract class Tile {
    private int x;
    private int y;
    private Image image;
    private boolean collsision;
    private int lightEmission = 0;
    private int lightLevel = 0;

    /**
     * A tile is a stationary square on the map.
     * The coordiantes is the bottom left position of the map.
     * When standing on the ground, a block going from your feet to your waist has the same Y coordinate as you
     * @param image   The Image that should be drawn at the tile.
     */
    public Tile(int x, int y, Image image, boolean collision) {
        this(x, y, image, collision, 0);
    }

    public Tile(int x, int y, Image image, boolean collision, int lightEmission) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.collsision = collision;
        this.lightEmission = lightEmission;
    }

    public int getLightEmission() {
        return lightEmission;
    }

    public int getLightLevel() {
        return lightLevel;
    }

    public void calculateLight(){
        int highestLightLevel = lightEmission;

        highestLightLevel = getHighestLight(x+1, y, highestLightLevel);
        highestLightLevel = getHighestLight(x-1, y, highestLightLevel);
        highestLightLevel = getHighestLight(x, y+1, highestLightLevel);
        highestLightLevel = getHighestLight(x, y-1, highestLightLevel);

        boolean lightLevelChanged = highestLightLevel != lightLevel;
        lightLevel = highestLightLevel;

        if (lightLevelChanged){
            System.out.println("light level changed");
            TileMap tm = GlobalGameData.getTileMap();
            tm.getTile(x+1, y).calculateLight();
            tm.getTile(x-1, y).calculateLight();
            tm.getTile(x, y+1).calculateLight();
            tm.getTile(x, y-1).calculateLight();
        }
    }

    private int getHighestLight(int x, int y, int currentHighestLight){
        Tile adjacentTile = GlobalGameData.getTileMap().getTile(x, y);
        int thisTileLevel;
        if (isCollsision()){
            thisTileLevel = adjacentTile.getLightLevel()-2;
        } else {
            thisTileLevel = adjacentTile.getLightLevel()-1;
        }
        if (thisTileLevel>currentHighestLight){
            return thisTileLevel;
        }
        return currentHighestLight;
    }

    public void onPlace(){
        calculateLight();
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
