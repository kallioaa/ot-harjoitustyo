/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagechatter.ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import imagechatter.logic.ChatLogic;
import imagechatter.logic.ImageLogic;
import imagechatter.logic.UserLogic;

/**
 * Common methods for different Controllers. These provide the functionality for changing the view.
 * @author aatukallio
 */
public class Controller {
    
    UserLogic userLogic;
    ImageLogic imageLogic;
    ChatLogic chatLogic;
    
    
    public Controller(UserLogic userLogic, ImageLogic imageLogic, ChatLogic chatLogic)  {
        this.userLogic = userLogic;
        this.imageLogic = imageLogic;
        this.chatLogic = chatLogic;
    }
    
    /**
     * Changes to main view.
     * @param event
     * @throws IOException 
     */
    protected void changeToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view.fxml"));
        Parent root = loader.load();

        ControllerMain controller = loader.getController();
        controller.setUp(userLogic, imageLogic, chatLogic, this);
        
        Scene tableViewScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
    /**
     * Changes to new user view.
     * @param event
     * @throws IOException 
     */
    protected void changeToNewUser(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("newUserView.fxml"));
        Parent root = loader.load();

        ControllerNewUser controller = loader.getController();
        controller.setUp(userLogic, this);
        
        Scene tableViewScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
    /**
     * Changes to log in view.
     * @param event
     * @throws IOException 
     */
    protected void changeToLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("loginView.fxml"));
        Parent root = loader.load();

        ControllerLogin controller = loader.getController();
        controller.setUp(userLogic, this);
        
        Scene tableViewScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
