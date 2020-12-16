/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import keskusteluakuvista.database.Dao;
import keskusteluakuvista.database.DaoChat;
import keskusteluakuvista.database.DaoImages;
import keskusteluakuvista.database.DaoUsers;
import keskusteluakuvista.logic.ChatLogic;
import keskusteluakuvista.logic.ImageIOStub;
import keskusteluakuvista.logic.ImageLogic;
import keskusteluakuvista.logic.UniversalLogic;
import keskusteluakuvista.logic.UserLogic;

/**
 *
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
        Dao dao = new Dao();
        DaoImages daoImages = new DaoImages(dao);
        DaoChat daoChat = new DaoChat(dao);
        DaoUsers daoUsers = new DaoUsers(dao);
        UniversalLogic uniLogic = new UniversalLogic();
        this.uniLogic = uniLogic;
        
        UserLogic userLogic = new UserLogic(daoUsers,uniLogic);
        this.userLogic = userLogic;
        ImageLogic imageLogic = new ImageLogic(daoImages,uniLogic, new ImageIOStub());
        ChatLogic chatLogic = new ChatLogic(daoChat,uniLogic);
        
        FXMLLoader loaderLogin = new FXMLLoader(getClass().getResource("loginView.fxml"));
        Parent rootLogin = loaderLogin.load();

        Controller controller = new Controller(userLogic, imageLogic, chatLogic);
        
        ControllerLogin controllerLogin = loaderLogin.getController();      
        controllerLogin.setUp(userLogic, controller);
        
        
        primarystage.setTitle("ImageChatter");
        primarystage.setScene(new Scene(rootLogin,500,800));
        primarystage.show();
    }
    
    @Override
    public void stop(){
        if (uniLogic.getUser() != null) {
            userLogic.logOut();
        }
    }
}
