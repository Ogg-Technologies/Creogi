package com.oggtechnologies.creogi.worldGeneration;


public class WorldGenerator {

    private SurfaceOutline surface = new SurfaceOutline();

    public WorldGenerator() {
    }

    public void generateWorld() {
        surface.generate(5, 5);

    }
}
