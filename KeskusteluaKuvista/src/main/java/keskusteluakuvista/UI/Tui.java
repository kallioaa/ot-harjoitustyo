/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.UI;

import java.util.List;
import java.util.Scanner;
import keskusteluakuvista.ApplicationLogic;

/**
 *
 * @author aatukallio
 */
public class Tui implements UI {
    private IO scanner;
    private ApplicationLogic logic;
            
    public Tui(ApplicationLogic logic, IO scanner) {
        this.logic = logic;
        this.scanner = scanner;
    }
    
    //The method for running the text user interface.
    @Override
    public void run() {
        boolean running = true;
        while (running) {
            this.scanner.print("\nSelect operation with typing the corresponding letter: \nSearch with image (S) \nExit (E)\n");
            String input = this.scanner.nextLine().strip().toLowerCase();
            if (input.equals("s")) {
                Integer id = this.insertImage();
                this.printComments(id);
                this.scanner.print("Do you want to comment: y(yes)");
                input = this.scanner.nextLine().toLowerCase();
                if (input.equals("y")) {
                    this.addComment(id);
                    this.printComments(id);
                }
            } else if (input.equals("e")) {
                running = false;
            } else {
                this.scanner.print("Wrong input");
            }
        }
    }
    private void addComment(Integer id) {
        String input = scanner.nextLine();
        this.logic.addMessage(id, input);
    }
    
    private Integer insertImage() {
        scanner.print("Insert URL");
        String url = scanner.nextLine();
        return logic.searchImageID(url);
    }
    
    private void printComments(Integer id) {
        List<String> chat = logic.showChat(id);
        this.scanner.print("\nKommentit:");
        chat.forEach(s -> scanner.print(s));
        System.out.println("");
    }
}
