/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.logic;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import keskusteluakuvista.ImageToHash;
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
    private Session session;

    public ApplicationLogic(DaoImages daoImages, DaoChat daoChat) {
        this.daoImages = daoImages;
        this.daoChat = daoChat;
        this.session = Session.getInstance();
    } 
    //The method fetches the picture from the given URL and inserts it into the "database". 
    
    public void searchImageID(String url) {
        try {
            BufferedImage img = null;
            img = ImageIO.read(new URL(url));
            this.ImageId = daoImages.addImage(new ImageToHash(img),session.gerUsername());
        } catch (IOException e) {
            this.ImageId = null;
        }
    }
    
    public List<String> getImageInfo() {
        List<String> t = this.daoImages.getHistrory(ImageId);
        System.out.println(t.toString());
        return t;
    }
    
    public List<String> getImageHistory() {
        if (this.ImageId != null) {
            return this.daoImages.getHistrory(this.ImageId);
        }
        return new ArrayList<>();
    }
    
    public void resetSession() {
        this.session.resetSession();
    }
    
    public Integer getImageID() {
        return this.ImageId;
    }
    
    public List<List<String>> showChat() {
        if (this.ImageId != null) {
            return daoChat.getMessages(this.ImageId);
        }
        return new ArrayList<>();
    }
    
    public void addMessage(String text) {
        if (!text.isEmpty()) {
            this.daoChat.addMessage(this.getImageID(), session.gerUsername(), text.strip());
        }
    }
}


    
