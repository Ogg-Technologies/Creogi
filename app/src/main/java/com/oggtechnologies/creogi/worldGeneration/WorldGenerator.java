package com.oggtechnologies.creogi.worldGeneration;


public class WorldGenerator {

    private SurfaceOutline surface = new SurfaceOutline();
    private FillWorld fillWorld = new FillWorld();
    private Smooth smooth = new Smooth();


    static float[] layerHeights = {0.7f, 0.69f};   // Where the layer begins in the world. {surface/cavern layer, surface outline}

    public WorldGenerator() {
    }

    public void generateWorld() {
        surface.generate(1000, 0.8f);

        int min = 1024;
        int max = 0;
        for (int i = 0; i < surface.outline.length; i++) {   // Finds the tallest mountain and lowest valley
            if (surface.outline[i] < min) {
                min = surface.outline[i];
            } else if (surface.outline[i] > max) {
                max = surface.outline[i];
            }
        }
        System.out.println("----- Min: " + min + " Max: " + max);

        fillWorld.fill(surface.outline);
        smooth.surface(surface.outline, min, max, 5);

        surface.replaceToGrass(max);
    }
}
