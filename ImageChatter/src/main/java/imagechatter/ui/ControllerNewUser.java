/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagechatter.ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import imagechatter.logic.UserLogic;

public class ControllerNewUser {
    
    private UserLogic userLogic;
    private Controller controller;

    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private PasswordField repeatPassword;
    
    @FXML
    private Label passwordMsg;
    
    //Changes the view back to log in.
    @FXML
    void backToLogin(ActionEvent event) throws IOException {
        controller.changeToLogin(event);
    }
    
    //Functionality for pressing the new user button. Displays the error message if the new user information are in a wrong format.
    @FXML
    void createNewUser(ActionEvent event) throws IOException {
        String retString = userLogic.createUser(username.getText(), password.getText(), repeatPassword.getText());

        username.clear();
        password.clear();
        repeatPassword.clear();

        if (retString.equals("Success")) {
            controller.changeToLogin(event);   
        }
        passwordMsg.setText(retString);
    }
    
    void setUp(UserLogic userLogic, Controller controller) {
        this.userLogic = userLogic;
        this.controller = controller;
    }
}

