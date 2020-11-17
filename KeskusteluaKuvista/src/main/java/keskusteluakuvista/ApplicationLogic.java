/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
/**
 *
 * @author aatukallio
 */
public class ApplicationLogic {
    private DatabaseImages dbImages;
    
    public ApplicationLogic(DatabaseImages dbImages){
        this.dbImages = dbImages;
    } 
    
    //The method fetches the picture from the given URL and inserts it into the "database". 
    public Integer searchImageFromInternet(String url) {
        try {
            BufferedImage img = null;
            img = ImageIO.read(new URL(url));
            dbImages.imageID(new Image(img));
            return dbImages.imageID(new Image(img));
       
        } catch (IOException e) {
            return -1;
        }
    }

}


    
