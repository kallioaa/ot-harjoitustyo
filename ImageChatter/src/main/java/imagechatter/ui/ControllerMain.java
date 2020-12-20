/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagechatter.ui;

import java.awt.image.BufferedImage;
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
import imagechatter.logic.ChatLogic;
import imagechatter.logic.ImageLogic;
import imagechatter.logic.UserLogic;


public class ControllerMain implements Initializable {
    
    private UserLogic userLogic;
    private ImageLogic imageLogic;
    private ChatLogic chatLogic;

    private Controller controller;
    
    
    @FXML
    private Label imageHistory;

    @FXML
    private ImageView image;

    @FXML
    private ListView allMessages;

    @FXML
    private TextField tfMessage;
    
    /**
     * Loads the image from given URL and displays it int the GUI main view
     * @param url 
     */
    private void loadImage() {
        Image currentNetImage = null;
        BufferedImage bImg = null;
        try {
            if (imageLogic.getBufImage() != null) {
                currentNetImage = SwingFXUtils.toFXImage(imageLogic.getBufImage(), null);
            } else {
                bImg = ImageIO.read(getClass().getResourceAsStream("wrongFormat.png"));
                currentNetImage = SwingFXUtils.toFXImage(bImg, null);
            }
            image.setImage(currentNetImage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> setUpChatList(List<List<String>> chat) {
        List<String> returnList = new ArrayList<>();
        chat.forEach(message -> {
            returnList.add("<" + message.get(0) + ", " + message.get(1) + "> " + message.get(2));
        });
        return returnList;
    }
    
    //Method formats the list recieved from ApplicationLogic to desired format
    private String setUpHistory(List<String> history) {
        if (imageLogic.getBufImage() != null) {
            return "<" + history.get(0) + ", " + history.get(1) + ", " + history.get(2) + ">";
        } else {
            return "";
        }
    }
    
    //Sets the chat for the main window
    private void setChat() {   
        List<String> chat = setUpChatList(this.chatLogic.showChat());
        ObservableList<String> messages = FXCollections.observableArrayList(chat);
        this.allMessages.setItems(messages);
    }  
    
    //Functionality when pressing the new image from URL button. 
    @FXML
    void onSearchImage(ActionEvent event) {
        String url = this.tfMessage.getText();
        this.tfMessage.clear();
        this.imageLogic.searchImageID(url);
        this.imageHistory.setText(setUpHistory(this.imageLogic.getImageHistory()));
        this.loadImage();
        this.setChat();
    }
    
    //Functionality when pressing the send button.
    @FXML
    void onSendClick(ActionEvent event) {
        chatLogic.addMessage(this.tfMessage.getText());
        this.setChat();
        this.tfMessage.clear();
    }
   
    /**
     * Functionality when pressing the log out button. Method call from Logic resets current Session.
     */
    @FXML
    void logOutButton(ActionEvent event) throws IOException {
        userLogic.logOut();
        controller.changeToLogin(event);        
    }
    
    void setUp(UserLogic userLogic, ImageLogic imagesLogic, ChatLogic chatLogic, Controller controller) {
        this.userLogic = userLogic;
        this.imageLogic = imagesLogic;
        this.chatLogic = chatLogic;
        this.controller = controller;
    }
   //Function set ups the databases needed.
    /**
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            BufferedImage bImg = ImageIO.read(getClass().getResourceAsStream("startUp_1.png"));
            Image currentNetImage = SwingFXUtils.toFXImage(bImg, null);
            this.image.setImage(currentNetImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
