/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javax.imageio.ImageIO;
import keskusteluakuvista.ApplicationLogic;
import keskusteluakuvista.database.Dao;
import keskusteluakuvista.database.DaoChat;
import keskusteluakuvista.database.DaoImages;

public class Controller implements Initializable {
    
    private ApplicationLogic logic;

    @FXML
    private ImageView image;

    @FXML
    private ListView all_messages;

    @FXML
    private TextField tf_message;
    
    @FXML
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
    
    @FXML
    void setChat() {   
        ObservableList<String> messages = FXCollections.observableArrayList(this.logic.showChat());
        this.all_messages.setItems(messages);
    }

    @FXML
    void onSearchImage(ActionEvent event) {
        String url = this.tf_message.getText();
        this.tf_message.clear();
        this.logic.searchImageID(url);
        if (this.logic.getImageID()!= null) {
            this.loadImage(url);
        }
        this.setChat();
    }
    
    @FXML
    void onSendClick(ActionEvent event) {
        logic.addMessage(this.tf_message.getText());
        this.setChat();
        this.tf_message.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Dao dao = new Dao("resourcesDb/kkDatabase.db",false);
        DaoImages imagesDao = new DaoImages(dao.getConnection());
        DaoChat chatsDao = new DaoChat(dao.getConnection());
        this.logic = new ApplicationLogic(imagesDao,chatsDao);
        try {
            BufferedImage bImg = ImageIO.read(new File("testImages","startUp.png"));
            Image image = SwingFXUtils.toFXImage(bImg, null);
            this.image.setImage(image);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
