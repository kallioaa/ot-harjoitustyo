/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import keskusteluakuvista.logic.ApplicationLogic;
import keskusteluakuvista.database.DaoChat;
import keskusteluakuvista.database.DaoImages;

public class ControllerMain extends Controller implements Initializable {
    
    private ApplicationLogic logic;
    
    @FXML
    private Label imageHistory;

    @FXML
    private ImageView image;

    @FXML
    private ListView all_messages;

    @FXML
    private TextField tf_message;
    
    /**
     * Loads the image from given URL and displays it int the GUI main view
     * @param url 
     */
    private void loadImage(String url) {
        try {
            BufferedImage bImg = null;
            bImg = ImageIO.read(new URL(url));
            Image image = SwingFXUtils.toFXImage(bImg, null);
            this.image.setImage(image);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private List<String> setUpChatList(List<List<String>> chat) {
        List<String> returnList = new ArrayList<>();
        for (List<String> message: chat) {
            returnList.add("<" + message.get(0) + ", " + message.get(1) + "> " + message.get(2));
        }
        return returnList;
    }
    
    //Method formats the list recieved from ApplicationLogic to desired format
    private String setUpHistory(List<String> history) {
        return "<" + history.get(0) + ", " + history.get(1) + ", " + history.get(2) + ">";
    }
    
    //Sets the chat for the main window
    void setChat() {   
        List<String> chat = setUpChatList(this.logic.showChat());
        ObservableList<String> messages = FXCollections.observableArrayList(chat);
        this.all_messages.setItems(messages);
    }  
    
    //Functionality when pressing the new image from URL button. 
    @FXML
    void onSearchImage(ActionEvent event) {
        String url = this.tf_message.getText();
        this.tf_message.clear();
        this.logic.searchImageID(url);
        if (this.logic.getImageID()!= null) {
            this.imageHistory.setText(setUpHistory(this.logic.getImageHistory()));
            this.loadImage(url);
        }
        this.setChat();
    }
    
    //Functionality when pressing the send button.
    @FXML
    void onSendClick(ActionEvent event) {
        logic.addMessage(this.tf_message.getText());
        this.setChat();
        this.tf_message.clear();
    }
   
    /**
     * Functionality when pressing the log out button. Method call from Logic resets current Session.
     */
    @FXML
    void logOutButton(ActionEvent event) throws IOException {
        logic.resetSession();
        super.changeToLogin(event);        
    }
    
   //Function set ups the databases needed.
    /**
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DaoImages imagesDao = DaoImages.getInstance();
        DaoChat chatsDao = DaoChat.getInstance();
        this.logic = ApplicationLogic.getInstance(imagesDao, chatsDao);
        try {
            //BufferedImage bImg = ImageIO.read(new File("testImages","startUp.png"));
            //Image image = SwingFXUtils.toFXImage(bImg, null);
            //this.image.setImage(image);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
