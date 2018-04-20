package com.oggtechnologies.creogi.worldGeneration;


public class WorldGenerator {

    private SurfaceOutline surface = new SurfaceOutline();
    private FillWorld fillWorld = new FillWorld();
    public static float[] layerHeights = {0.001f};   // Where the layer begins in the world. {Surface layer}

    public WorldGenerator() {
    }

    public void generateWorld() {
        surface.generate(800, 1f);
        fillWorld.fill(surface.outline);
    }
}
