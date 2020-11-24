/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.UI;

import java.util.Scanner;

/**
 *
 * @author aatukallio
 */
public class Konsoli implements IO {
    private Scanner scanner;
    
    public Konsoli() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String nextLine() {
        return scanner.nextLine(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void print(String m) {
        System.out.println(m);
    }
    

}
