
package com.oggtechnologies.creogi.worldGeneration;

import android.provider.Settings;
import com.oggtechnologies.creogi.GlobalGameData;
import com.oggtechnologies.creogi.TileMap;

import java.util.Random;

public class SurfaceOutline {

    public int[] outline;               // The output. Holds int height values of every x coordinate
    private int outlineLength = 1024;   // The width of the surface (default 1024)
    private float[] gradientVector;     // Seeded randomized vectors between vectorRange
    private float[] distanceVector;     // Vectors that are calculated from the distance to the closest grid points

    private double[] gradientVectorRange = {-Math.sqrt(2), Math.sqrt(2)};   // The range in which gradientVectors are initialized


    public SurfaceOutline() {
        //TileMap tileMapWidth = GlobalGameData.getTileMap();
        //outlineLength = tileMapWidth.getTileGridWidth();
        outline = new int[outlineLength];
        gradientVector = new float[outlineLength];
        distanceVector = new float[outlineLength];

    }

    /**
     * @param gridSize      How many distanceVectors there are, how smooth or erratic the world will become (more == smoother)
     * @param variation     A constant that is multiplied to each output to create the desired height difference
     */
    public void generate(float gridSize, int variation) {
        /*Random rand = GlobalGameData.getSeededRandom();

        for (int i = 0; i < outlineLength; i++) {
            gradientVector[i] = rand.nextFloat();
            System.out.println(gradientVector[i]);
        }*/

        float length = outlineLength / (gridSize - 1);
        for (int i = 0; i < outlineLength; i++) {

            for (int j = 0; j < gridSize; j++) {
                if (i < length * j) {
                    break;
                }
            }

            float x1 = -(i % length / length);
            float x2 = 1 + x1;
            distanceVector[i] = x1 + x2;

            outline[i] = Math.round(variation * distanceVector[i]);
        }
    }
}
