package com.oggtechnologies.creogi;



public class UserOptions {
    private static float zoomLevel = 0;
    private static float tileScale = 10;

    public static float getTileScale() {
        return tileScale;
    }

    public static void setZoomLevel(float zoomLevel) {   // Less zoomLevel equals more out zoomed
        UserOptions.zoomLevel = zoomLevel;
        tileScale = (float) Math.pow(1.1, zoomLevel);
    }

    public static float getZoomLevel() {
        return zoomLevel;
    }
}
