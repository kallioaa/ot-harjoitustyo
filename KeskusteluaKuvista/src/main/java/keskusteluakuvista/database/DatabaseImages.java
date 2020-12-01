/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.database;


import java.util.HashMap;
import keskusteluakuvista.ImageToHash;

/*
This class exists only to simulate the operations of database. Will be replaced with sqlite implementation.
 */
public class DatabaseImages {
    
    private HashMap<ImageToHash,Integer> images;
    private Integer n;
    
    public DatabaseImages() {
        this.n = 1;
        this.images = new HashMap<>();
    }
 
    //Find the pictureID from the database if it exists. Otherwise, return -1;
    private void addImage(ImageToHash image) {
        this.images.put(image, n);
        this.n++;
    }
    
    public Integer imageID(ImageToHash image) {
        if (!this.images.containsKey(image)) {
            this.addImage(image);
            return this.n;
        } else {
            return this.images.get(image);
        }
    }
   
    
}
