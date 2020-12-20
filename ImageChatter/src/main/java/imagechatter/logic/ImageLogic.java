/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagechatter.logic;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import imagechatter.entities.ImageToHash;
import imagechatter.database.DaoImages;


/**
 * Handles logic for the image.
 * @author aatukallio
 */
public class ImageLogic {
    
    private final DaoImages daoImages;
    private final UniversalLogic uniLogic;
    private final ImageIOStub imageLoader;
    private BufferedImage bufImage;
    
    
    public ImageLogic(DaoImages daoImages, UniversalLogic uniLogic, ImageIOStub imageLoader) {
        this.imageLoader = imageLoader;
        this.daoImages = daoImages;
        this.uniLogic = uniLogic;
    }
   
    /**
     * Loads the image from a given URL and adds it to the DaoImages. Sets the imageID for Universal logic.
     * @param url to the image
     */
    public void searchImageID(String url) {
        try {
            bufImage = imageLoader.read(new URL(url));
            ImageToHash imageHashable = new ImageToHash(bufImage);
            Integer imageId = daoImages.addImage(imageHashable, uniLogic.getUser().getUsername());
            uniLogic.setImageId(imageId);
        } catch (Exception e) {
            bufImage = null;
            uniLogic.setImageId(null);
        }
    }
    
    /**
     * 
     * @return History of the image
     */
    public List<String> getImageHistory() {
        if (uniLogic.getImageId() != null) {
            return daoImages.getHistrory(uniLogic.getImageId());
        }
        return new ArrayList<>();
    }
    
    /**
     * 
     * @return BufferedImage
     */
    public BufferedImage getBufImage() {
        return bufImage;
    }
    
    
    
}
