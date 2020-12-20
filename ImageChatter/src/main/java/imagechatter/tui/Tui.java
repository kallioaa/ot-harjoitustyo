/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagechatter.tui;

import java.util.List;
import java.util.Scanner;
//import keskusteluakuvista.logic.ApplicationLogic;

/**
 *
 * @author aatukallio
 */
public class Tui  {
    /*
    private IO scanner;
    private ApplicationLogic logic;
            
    public Tui(ApplicationLogic logic, IO scanner) {
        this.logic = logic;
        this.scanner = scanner;
    }
    
    //The method for running the text user interface.

    public void start() {
        boolean running = true;
        while (running) {
            this.scanner.print("\nSelect operation with typing the corresponding letter: \nSearch with image (S) \nExit (E)\n");
            String input = this.scanner.nextLine().strip().toLowerCase();
            if (input.equals("s")) {
                Integer id = this.insertImage();
                if (id==-1) {
                    continue;
                }
                this.printComments();
                this.scanner.print("Do you want to comment: y(yes)");
                input = this.scanner.nextLine().toLowerCase();
                if (input.equals("y")) {
                    this.addComment();
                    this.printComments();
                }
            } else if (input.equals("e")) {
                running = false;
            } else {
                this.scanner.print("Wrong input");
            }
        }
    }
    private void addComment() {
        String input = scanner.nextLine();
        this.logic.addMessage(input);
    }
    
    private Integer insertImage() {
        scanner.print("Insert URL");
        String url = scanner.nextLine();
        return logic.getImageID();
    }
    /*
    private void printComments() {
        List<String> chat = logic.showChat();
        this.scanner.print("\nKommentit:");
        chat.forEach(s -> scanner.print(s));
        System.out.println("");
    }
*/
}
