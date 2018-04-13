package com.oggtechnologies.creogi.imageHandler;

public class StaticImage extends Image{
    String textureName;

    /**
     * An Image is a class containing a single textureName.
     * @param textureName   The name of the texture.
     */
    public StaticImage(String textureName) {
        this.textureName = textureName;
    }

    @Override
    public String getTextureName() {
        return textureName;
    }
}
