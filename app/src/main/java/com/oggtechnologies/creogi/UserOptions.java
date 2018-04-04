package com.oggtechnologies.creogi;



public class UserOptions {
    private static float zoomLevel = 1;
    private static float tileScale = 1;

    public static float getTileScale() {
        return tileScale;
    }

    public static void setZoomLevel(float zoomLevel) {
        UserOptions.zoomLevel = zoomLevel;
        tileScale = (float) Math.pow(1.1, zoomLevel);
    }

    public static float getZoomLevel() {
        return zoomLevel;
    }
}
