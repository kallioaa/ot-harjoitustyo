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
import keskusteluakuvista.logic.Session;

public class ControllerLogin extends Controller {
    
    @FXML
    private Label loginMessage;  
    
    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;  
    

    @FXML
    void logInAttempt(ActionEvent event) throws IOException {
        Boolean success = Session.SignIn(this.username.getText(), this.password.getText());
        password.clear();
        username.clear();
        if (success) {
            super.changeToMain(event);
        } else {
            this.loginMessage.setText("Username or password is incorrect");
        }
    }
    
    
    @FXML
    void newUserButton(ActionEvent event) throws IOException {
        super.changeToNewUser(event);
    }
}          