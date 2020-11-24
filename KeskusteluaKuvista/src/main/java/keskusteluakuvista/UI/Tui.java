/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.UI;

import java.util.Scanner;
import keskusteluakuvista.ApplicationLogic;

/**
 *
 * @author aatukallio
 */
public class Tui implements UI{
    private IO scanner;
    private ApplicationLogic logic;
            
    public Tui(ApplicationLogic logic,IO scanner) {
        this.logic = logic;
        this.scanner = scanner;
    }
    
    //The method for running the text user interface.
    public void run() {
        boolean running = true;
        while (running) {
            Scanner myObj = new Scanner(System.in); 
            System.out.println("Select operation with typing the corresponding letter: \nSearch with image (S) \nExit (E)\n");
            String input = scanner.nextLine().strip().toLowerCase();
            if (input.equals("s")) {
                System.out.println("The URL of the picture");
                input = scanner.nextLine();
                System.out.println(this.logic.searchImageFromInternet(input));
            } else if (input.equals("e")) {
                running = false;
            } else {
                System.out.println("Wrong input");
            }
        }
    }
    
   
   
    
    
}
