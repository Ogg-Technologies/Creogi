package com.oggtechnologies.creogi.worldGeneration;


public class WorldGenerator {

    private SurfaceOutline surface = new SurfaceOutline();
    private FillWorld fillWorld = new FillWorld();
    private Smooth smooth = new Smooth();


    public static float[] layerHeights = {0.004f};   // Where the layer begins in the world. {Surface layer}

    public WorldGenerator() {
    }

    public void generateWorld() {
        surface.generate(550, 0.9f);
        fillWorld.fill(surface.outline);
        smooth.surface(surface.outline);
    }
}
