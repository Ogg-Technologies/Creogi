package com.oggtechnologies.creogi;

import com.oggtechnologies.creogi.entities.Player;

public class GlobalGameData {
    private static final float baseTileSize = 50;
    private static Player player;
    private static TileMap tileMap;

    public static TileMap getTileMap() {
        return tileMap;
    }

    public static void setTileMap(TileMap tileMap) {
        GlobalGameData.tileMap = tileMap;
    }

    public static void setPlayer(Player player) {
        GlobalGameData.player = player;
    }

    public static float getTilePixelSize(){
        return baseTileSize*UserOptions.getTileScale();
    }

    public static float mapToScreenX(float mapX){
        float deltaPlayerMapX = mapX-player.getX();
        float deltaPlayerPixelX = deltaPlayerMapX*getTilePixelSize();
        float pixelX = deltaPlayerPixelX+MainActivity.getGameViewWidth()/2;
        return pixelX;
    }

    public static float mapToScreenY(float mapY){
        float deltaPlayerMapY = mapY-player.getY();
        float deltaPlayerPixelY = deltaPlayerMapY*getTilePixelSize();
        float pixelY = MainActivity.getGameViewHeight()/2-deltaPlayerPixelY;
        return pixelY;
    }

    public static float screenToMapX(float pixelX){
        float deltaPlayerPixelX = pixelX-MainActivity.getGameViewWidth()/2;
        System.out.println(deltaPlayerPixelX);
        float deltaPlayerMapX = deltaPlayerPixelX/getTilePixelSize();
        float mapX = deltaPlayerMapX+player.getX();
        return mapX;
    }

    public static float screenToMapY(float pixelY){
        float deltaPlayerPixelY = MainActivity.getGameViewHeight()/2-pixelY;
        float deltaPlayerMapY = deltaPlayerPixelY/getTilePixelSize();
        float mapY = deltaPlayerMapY+player.getY();
        return mapY;
    }
}
