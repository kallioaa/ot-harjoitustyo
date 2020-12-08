/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Objects;

/**
 * ImageToHash takes a BufferedImage in the constructor and creates an object with an "uniue" hash ot of it.
 */
public class ImageToHash {
    private int[] pixels;
    private Integer height;
    private Integer width;
    
    public ImageToHash(BufferedImage image) {
        this.pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
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
        if (!Objects.equals(this.height, img.height)) {
            return false;
        }
        if (!Objects.equals(this.width, img.width)) {
            return false;
        }
        return img.pixels.equals(this.pixels); 
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(pixels);
    }
}
