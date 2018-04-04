package com.oggtechnologies.creogi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Textures {
    //Keeps track of textures and caches them for quick access
    private static long RECYCLE_CACHE_MEMORY_THRESHOLD = 90000000;
    private static Map<String, Bitmap> baseTextures = new HashMap<String, Bitmap>();
    private static Map<String, Bitmap> scaledTextures = new LinkedHashMap<String, Bitmap>();
    static Context context;

    public static void setContext(Context c){
        context = c;
    }

    static void loadTextures(){
    }

    private static boolean addBaseTexture(String drawableName){
        int resId = context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
        if (resId == 0){
            System.out.println("No drawable with name: " + drawableName);
            return false;
        }
        Bitmap newBitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        baseTextures.put(drawableName, newBitmap);
        return true;
    }

    public static Bitmap getTexture(String textureName, float pixelSize){
        if (pixelSize==0){
            return null;
        }
        //If the image exist in the correct scale return it.
        //The Id of the scaled image is the textureName + the pixelSize
        String scaledId = textureName+pixelSize;
        if (scaledTextures.containsKey(scaledId)){
            return scaledTextures.get(scaledId);
        }
        //If the image can't be found scaled check if it exist as a baseTexture, else return null
        Bitmap baseTexture = baseTextures.get(textureName);
        if (baseTexture == null) {
            if (addBaseTexture(textureName)){
                baseTexture = baseTextures.get(textureName);
            } else {
                System.out.println("No texture with name: " + textureName);
                return null;
            }
        }
        if (baseTexture == null){
            System.out.println("No texture with name: " + textureName);
            return null;
        }
        //If the baseTexture is the correct scale return it
        if (baseTexture.getWidth() == pixelSize){
            return baseTexture;
        }
        //Else scale the baseTexture to the correct scale and add it to scaledTexture
        //Then return the scaled image
        //Check if memory is low before creating a new image
        recycleCacheIfLowMemory();
        Bitmap scaledTexture = getResizedBitmap(baseTexture, pixelSize);
        scaledTextures.put(scaledId, scaledTexture);

        return scaledTexture;
    }

    /**
     * Gets the current available memory
     * Checks if it is less than RECYCLE_CACHE_MEMORY_THRESHOLD
     * If it is, first/oldest image from scaledTextures
     */
    private static void recycleCacheIfLowMemory() {
        long memoryAvailable = getMemoryAvailable();
        while (memoryAvailable<RECYCLE_CACHE_MEMORY_THRESHOLD){
            System.out.println("Low on memory, removing the oldest scaled texture");
            String firstKey = scaledTextures.keySet().iterator().next();
            scaledTextures.remove(firstKey);
            memoryAvailable = getMemoryAvailable();
            System.out.println("Number of cached scaled textures: " + scaledTextures.size());
        }
    }

    private static long getMemoryAvailable() {
        Runtime.getRuntime().gc();
        return (Runtime.getRuntime().maxMemory() -
                Runtime.getRuntime().totalMemory() +
                Runtime.getRuntime().freeMemory());
    }

    private static Bitmap getResizedBitmap(Bitmap bm, float newWidth) {
        System.out.println("resizing image");
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleWidth);

        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

}
