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

//This class includes all the functionality for UI's needs. Class holds the inforamtion 
//about the current user session, current image and the access to different daos. 
public class ApplicationLogic {

    private static ApplicationLogic applicationLogic_instance;
    private DaoImages daoImages;
    private DaoChat daoChat;
    private Integer ImageId;
    private Session session;

    private ApplicationLogic(DaoImages daoImages, DaoChat daoChat) {
        this.daoImages = daoImages;
        this.daoChat = daoChat;
        this.session = Session.getInstance();
    } 
    
    /**
     * Singleton constructor for ApplicationLogic
     * @param daoImages
     * @param daoChat
     * @return Application logic instance
     */
    public static ApplicationLogic getInstance(DaoImages daoImages, DaoChat daoChat) {
        if (applicationLogic_instance == null) {
            applicationLogic_instance = new ApplicationLogic(daoImages, daoChat);
        }
        return applicationLogic_instance;
    } 

    /**
     * resets ApplicationLogic and session.
     */
    public void resetSession() {
        applicationLogic_instance = null;
        this.session.resetSession();
    }
    
    /**
     * Creates an hashable image from the url address and gives it to the dao.
     * @param url 
     */
    public void searchImageID(String url) {
        try {
            BufferedImage img = null;
            img = ImageIO.read(new URL(url));
            this.ImageId = daoImages.addImage(new ImageToHash(img),session.gerUsername());
        } catch (IOException e) {
            this.ImageId = null;
        }
    }
    
    

    /**
     * Returns information about the image: ID First added username and time
     * @return 
     */
    public List<String> getImageHistory() {
        if (this.ImageId != null) {
            return this.daoImages.getHistrory(this.ImageId);
        }
        return new ArrayList<>();
    }
    
    //These method is called when the user has pressed the log out button. These resets the session
    
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


    
