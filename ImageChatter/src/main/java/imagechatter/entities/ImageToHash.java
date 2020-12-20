/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagechatter.entities;

import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * ImageToHash takes a BufferedImage in the constructor and creates an object with a unique hash.
 * 
 */
public class ImageToHash {
    private final int[] pixels;
    
    public ImageToHash(BufferedImage img) throws Exception {
        this.pixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());   
    }
          
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }
        ImageToHash img = (ImageToHash) o;
        return img.pixels.equals(this.pixels); 
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(pixels);
    }
}
