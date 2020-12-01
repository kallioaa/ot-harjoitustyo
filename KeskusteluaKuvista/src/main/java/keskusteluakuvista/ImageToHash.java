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
 *
 * @author aatukallio
 */
public class ImageToHash {
    private int[] pixels;
    private Integer height;
    private Integer width;
    
    /*
    The constructor creates an image. The paramaeters for height and width are self explanatory.
    The for loop takes 100 rgb values from the picture and stores them into a list. From these parametesrs the hashcode is created for the database which for now is just a hashmap
    */
    public ImageToHash(BufferedImage image) {
        this.pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
        this.height = image.getHeight();
        this.width = image.getWidth();
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
