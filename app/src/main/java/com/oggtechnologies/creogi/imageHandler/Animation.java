package com.oggtechnologies.creogi.imageHandler;

import com.oggtechnologies.creogi.GlobalGameData;

import java.util.ArrayList;

public class Animation extends Image {
    ArrayList<String> textureNames;
    float fps;
    int updateStarted;

    /**
     * Animation contains a list of images that play in sequence.
     * @param textureNames  A list containing the textureNames that should be displayed
     * @param fps           Frames Per Second. The number of images that should be displayed per second
     */
    public Animation(ArrayList<String> textureNames, float fps) {
        this.textureNames = textureNames;
        this.fps = fps;
        this.updateStarted = GlobalGameData.getUpdatesSinceStart();
    }

    @Override
    public String getTextureName() {
        int updatesSinceStarted = GlobalGameData.getUpdatesSinceStart()-updateStarted;
        double secondsSinceStarted = (double) updatesSinceStarted/60;
        int totalFramesDisplayed = (int) Math.floor(fps*secondsSinceStarted);
        int frameToDisplay = totalFramesDisplayed%textureNames.size();
        return textureNames.get(frameToDisplay);
    }
}
