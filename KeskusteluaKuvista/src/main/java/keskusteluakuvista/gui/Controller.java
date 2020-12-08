/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Common methods for different Controllers. These provide the functionality for changing the view.
 * @author aatukallio
 */
public abstract class Controller {

    protected void changeToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("view.fxml"));
        Scene tableViewScene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
    protected void changeToNewUser(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("newUserView.fxml"));
        Scene tableViewScene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
    protected void changeToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("loginView.fxml"));
        Scene tableViewScene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
