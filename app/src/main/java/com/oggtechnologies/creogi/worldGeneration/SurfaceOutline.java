
package com.oggtechnologies.creogi.worldGeneration;

import com.oggtechnologies.creogi.GlobalGameData;
import com.oggtechnologies.creogi.TileMap;
import com.oggtechnologies.creogi.tiles.Grass;

import java.util.Random;

public class SurfaceOutline {

    public int[] outline;               // The output. Holds int height values of every x coordinate
    private static int outlineLength = 0;          // The width of the surface (default 1024)
    private float[] gradientVector;     // Seeded randomized vectors between vectorRange
    private float[] distanceVector;     // Vectors that are calculated from the distance to the closest grid points
    private TileMap tileMap;

    private double[] gradientVectorRange = {-Math.sqrt(2), Math.sqrt(2)};   // The range in which gradientVectors are initialized


    public SurfaceOutline() {
        tileMap = GlobalGameData.getTileMap();
        outlineLength = tileMap.getTileGridWidth();
        outline = new int[outlineLength];
        gradientVector = new float[outlineLength];
        distanceVector = new float[outlineLength];

    }

    /**
     * @param gridSize      How many distanceVectors there are, how smooth or erratic the world will become (more == smoother)
     * @param variation     A constant that is multiplied to each output to create the desired height difference
     */
    public void generate(float gridSize, float variation) {
        Random rand = GlobalGameData.getSeededRandom();

        for (int i = 0; i < outlineLength; i++) {
            gradientVector[i] = rand.nextFloat() * (float) (gradientVectorRange[1] - gradientVectorRange[0]) + (float) gradientVectorRange[0];
        }

        float length = outlineLength / (gridSize - 1);
        for (int i = 0; i < outlineLength; i++) {

            int jj = 0;
            for (int j = 0; j < gridSize; j++) {   // Finds the grid point closest to the right of the current coordinate
                if (i < length * j) {
                    jj = j;
                    break;
                }
            }

            float x1 = -(i % length / length);   // Sets the right value to the distance vectors
            float x2 = 1 + x1;
            distanceVector[i] = x1 + x2;


            float totalVector = 0;

            if (i >= 2) {
                totalVector += gradientVector[jj - 1] + gradientVector[jj - 2];
            } else if (i == 1) {
                totalVector += gradientVector[jj - 1];
            }
            if (i <= outlineLength - 3) {
                totalVector += gradientVector[jj + 1] + gradientVector[jj + 2];
            } else if (i == outlineLength - 2) {
                totalVector += gradientVector[jj + 1];
            }

            totalVector += gradientVector[jj] + distanceVector[i];
            totalVector *= variation;

            outline[i] = (int) Math.round(fade(totalVector)) + 10;
        }
        placeSurface();
    }

    private double fade(float value) {
        return value;//6 * Math.pow(value, 5) - 15 * Math.pow(value, 4) + 10 * Math.pow(value, 3);
    }


    private void placeSurface() {
        for (int i = 0; i < outlineLength; i++) {
            tileMap.addTile(new Grass(i, outline[i]));
        }
    }
}
