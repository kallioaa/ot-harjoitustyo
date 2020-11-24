/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author aatukallio
 */
public class Image {
    private Integer height;
    private Integer width;
    private List<Integer> pixels;
    
    /*
    The constructor creates an image. The paramaeters for height and width are self explanatory.
    The for loop takes 100 rgb values from the picture and stores them into a list. From these parametesrs the hashcode is created for the database which for now is just a hashmap
    */
    public Image(BufferedImage image) {
        this.pixels = new ArrayList<>();
        this.height = image.getHeight();
        this.width = image.getWidth();
        for (int x = 0;x <= this.width - this.width / 10; x = x + this.width / 10) {
            for (int y = 0; y <= this.height - this.height / 10; y = y + this.height / 10) {
                pixels.add(image.getRGB(x, y));
            }
        }
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
        Image img = (Image) o;
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
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.height);
        hash = 73 * hash + Objects.hashCode(this.width);
        hash = 73 * hash + Objects.hashCode(this.pixels);
        return hash;
    }
}
