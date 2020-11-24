/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista;
import keskusteluakuvista.database.DatabaseImages;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
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

    public ApplicationLogic(DaoImages daoImages, DaoChat daoChat) {
        this.daoImages = daoImages;
        this.daoChat = daoChat;
    } 
    //The method fetches the picture from the given URL and inserts it into the "database". 
    public Integer searchImageID(String url) {
        try {
            BufferedImage img = null;
            img = ImageIO.read(new URL(url));
            return daoImages.addImage(new Image(img));
        } catch (IOException e) {
            return -1;
        }
    }
    
    public List<String> showChat(Integer id) {
        return daoChat.getMessages(id);
    }
    
    public void addMessage(Integer id, String text) {
        daoChat.addMessage(id, text);
    }
    

}


    
