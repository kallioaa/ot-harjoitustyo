/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista;

import keskusteluakuvista.UI.Konsoli;
import keskusteluakuvista.database.*;
import keskusteluakuvista.UI.Tui;

/**
 *
 * @author aatukallio
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    
    //Creates instances of the objects and starts the text user interface. Will be replaced with GUI in later versions. 
    public static void main(String[] args) {
        Dao connection = new Dao("resourcesDb/kkDatabase.db"); 
        DaoImages imagesDb = new DaoImages(connection.getConnection());
        ApplicationLogic logic = new ApplicationLogic(imagesDb);
        Tui tui = new Tui(logic,new Konsoli());
        tui.run();
        imagesDb.printValues();
        //DaoChat chatDb = new DaoChat(connection.getConnection());
    }
}
