/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import keskusteluakuvista.logic.Session;

public class ControllerNewUser extends Controller {

    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private PasswordField repeatPassword;
    
    @FXML
    private Label passwordMsg;
    
    @FXML
    void backToLogin(ActionEvent event) throws IOException {
        super.changeToLogin(event);
    }
            
    @FXML
    void createNewUser(ActionEvent event) throws IOException {
       String retString = Session.createUser(username.getText(), password.getText(), repeatPassword.getText());
      
       username.clear();
       password.clear();
       repeatPassword.clear();
       
       if (retString.equals("Success")) {
           super.changeToLogin(event);   
       }
       passwordMsg.setText(retString);
    }
}

