/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagechatter.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import imagechatter.database.Dao;
import imagechatter.database.FileDaoChats;
import imagechatter.database.FileDaoImages;
import imagechatter.database.FileDaoUsers;
import imagechatter.logic.ChatLogic;
import imagechatter.logic.ImageIOStub;
import imagechatter.logic.ImageLogic;
import imagechatter.logic.UniversalLogic;
import imagechatter.logic.UserLogic;

/**
 * Sets up the GUI for the application.
 * @author aatukallio
 */

//This class sets up the Gui.
public class MainGui extends Application {
    
    private UserLogic userLogic;
    private UniversalLogic uniLogic;
    
        
    public static void main(String[] args) {
        Application.launch(args);  
    }
    
    @Override
    public void start(Stage primarystage) throws Exception {
        Dao dao = new Dao("imagechatter.db");
        FileDaoImages daoImages = new FileDaoImages(dao);
        FileDaoChats daoChat = new FileDaoChats(dao);
        FileDaoUsers daoUsers = new FileDaoUsers(dao);
        UniversalLogic uniLogic = new UniversalLogic();
        this.uniLogic = uniLogic;
        
        UserLogic userLogic = new UserLogic(daoUsers, uniLogic);
        this.userLogic = userLogic;
        ImageLogic imageLogic = new ImageLogic(daoImages, uniLogic, new ImageIOStub());
        ChatLogic chatLogic = new ChatLogic(daoChat, uniLogic);
        
        FXMLLoader loaderLogin = new FXMLLoader(getClass().getResource("loginView.fxml"));
        Parent rootLogin = loaderLogin.load();

        Controller controller = new Controller(userLogic, imageLogic, chatLogic);
        
        ControllerLogin controllerLogin = loaderLogin.getController();      
        controllerLogin.setUp(userLogic, controller);
        
        
        primarystage.setTitle("ImageChatter");
        primarystage.setScene(new Scene(rootLogin, 500, 800));
        primarystage.show();
    }
    
    @Override
    public void stop() {
        if (uniLogic.getUser() != null) {
            userLogic.logOut();
        }
    }
}
