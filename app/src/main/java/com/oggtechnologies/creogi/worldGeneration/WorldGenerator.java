package com.oggtechnologies.creogi.worldGeneration;


public class WorldGenerator {

    SurfaceOutline surface = new SurfaceOutline();

    public WorldGenerator() {
    }

    public void generateWorld() {
        surface.generate();

    }
}
