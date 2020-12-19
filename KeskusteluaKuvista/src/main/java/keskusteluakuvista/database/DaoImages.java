/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.database;

import entities.ImageToHash;
import java.util.List;

/**
 *
 * @author aatukallio
 */
public interface DaoImages {

    /**
     * Function adds an image to the database if it does not exist.
     * @param image ImageToHash object
     * @param username session's user
     * @return Pictures ID (key in database)
     */
    //Function adds an image to the database if it does not exist.
    Integer addImage(ImageToHash image, String username);

    /**
     * Function returns the history data of the image.
     * @param id Pictures key in Images table
     * @return
     */
    List<String> getHistrory(Integer id);
    
}
