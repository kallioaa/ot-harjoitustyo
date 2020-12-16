/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.gui;

/**
 *
 * @author aatukallio
 */
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import keskusteluakuvista.logic.UserLogic;

public class ControllerLogin {
    
    private UserLogic userLogic;
    private Controller controller;
    
    @FXML
    private Label loginMessage;  
    
    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;  
    
    //Functionality for pressing the log in button
    @FXML
    void logInAttempt(ActionEvent event) throws IOException {
        Boolean success = userLogic.logIn(this.username.getText(), this.password.getText());
        password.clear();
        username.clear();
        if (success) {
            controller.changeToMain(event);
        } else {
            this.loginMessage.setText("Username or password is incorrect or user is already logged in");
        }
    }
    
    
    void setUp(UserLogic userLogic, Controller controller) {
        this.userLogic = userLogic;
        this.controller = controller;
    }
    
    
    //Changes the the view to new user creation.
    @FXML
    void newUserButton(ActionEvent event) throws IOException {
        controller.changeToNewUser(event);
    }
}          