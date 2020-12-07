/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author aatukallio
 */
public class MainGui extends Application {
    
    private Stage primarystage;
    
    public static void main(String[] args) {
        Application.launch(args);  
    }
    
    @Override
    public void start(Stage primarystage) throws Exception {
        this.primarystage = primarystage;
        Parent root = FXMLLoader.load(getClass().getResource("loginView.fxml"));
        primarystage.setTitle("ImageChatter");
        primarystage.setScene(new Scene(root,500,800));
        primarystage.show();
    }
    
}
