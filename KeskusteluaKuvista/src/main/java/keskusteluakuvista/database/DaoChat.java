/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.database;

import java.sql.Connection;

/**
 *
 * @author aatukallio
 */
public class DaoChat {
    private Connection db;
    
    public DaoChat(Connection db) {
        this.db = db;
    }
    
}
