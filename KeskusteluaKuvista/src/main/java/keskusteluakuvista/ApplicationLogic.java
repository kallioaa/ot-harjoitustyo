/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import keskusteluakuvista.database.DaoChat;
import keskusteluakuvista.database.DaoImages;
/**
 *
 * @author aatukallio
 */
public class ApplicationLogic {
    private DaoImages daoImages;
    private DaoChat daoChat;
    private Integer ImageId;

    public ApplicationLogic(DaoImages daoImages, DaoChat daoChat) {
        this.daoImages = daoImages;
        this.daoChat = daoChat;
    } 
    //The method fetches the picture from the given URL and inserts it into the "database". 
    public void searchImageID(String url) {
        try {
            BufferedImage img = null;
            img = ImageIO.read(new URL(url));
            this.ImageId = daoImages.addImage(new ImageToHash(img));
        } catch (IOException e) {
            this.ImageId = null;
        }
    }
    
    public Integer getImageID() {
        return this.ImageId;
    }
    
    public List<String> showChat() {
        if (this.ImageId != null) {
            return daoChat.getMessages(this.ImageId);
        }
        return new ArrayList<>();
    }
    
    public void addMessage(String text) {
        if (!text.isEmpty()) {
            this.daoChat.addMessage(this.getImageID(), text.strip());
        }
    }
    

}


    
