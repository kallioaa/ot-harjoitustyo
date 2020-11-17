/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista;
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
        DatabaseImages dbImages = new DatabaseImages();
        ApplicationLogic logic = new ApplicationLogic(dbImages);
        Tui tui = new Tui(logic);
        tui.run();
       
    }
}
