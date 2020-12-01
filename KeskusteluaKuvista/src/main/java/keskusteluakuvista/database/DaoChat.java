/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aatukallio
 */

public class DaoChat {
    private Connection db;
    
    
    public DaoChat(Connection db) {
        this.db = db;
    }
    
    public void addMessage(Integer id, String text) {
        try {
            PreparedStatement p  = db.prepareStatement("INSERT INTO Messages (id,message) VALUES (?,?)");
            p.setInt(1, id);
            p.setString(2, text);
            p.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    public List<String> getMessages(Integer id) {
        List<String> palautus = new ArrayList<>();
        try {
            PreparedStatement p  = db.prepareStatement("SELECT message FROM Messages WHERE id=?");
            p.setString(1, Integer.toString(id));
            ResultSet r = p.executeQuery();
            while (r.next()) {
                palautus.add(r.getString("message"));
            }
            return palautus;
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }    
}