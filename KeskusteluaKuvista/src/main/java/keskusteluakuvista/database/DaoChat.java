/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aatukallio
 */

public class DaoChat {
    
    private static DaoChat daoChat_instance;
    private final Connection conn;
    
    
    private DaoChat() {
        this.conn = Dao.getInstance().conn;
    }
    
    public static DaoChat getInstance() {
        if (daoChat_instance == null) {
            daoChat_instance = new DaoChat();
        }
        return daoChat_instance;
    }
   
    public void addMessage(Integer id,String username, String text) {
        try {
            PreparedStatement p  = conn.prepareStatement("INSERT INTO Messages (id_image, message, username, created) VALUES (?, ?, ?, CURRENT_TIMESTAMP)");
            p.setInt(1, id);
            p.setString(2, text);
            p.setString(3, username);
            p.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    public List<List<String>> getMessages(Integer id) {
        List<List<String>> palautus = new ArrayList<>();
        List<String> t;
        try {
            PreparedStatement p  = conn.prepareStatement("SELECT username, created, message FROM Messages WHERE id_image =?");
            p.setString(1, Integer.toString(id));
            ResultSet r = p.executeQuery();
            while (r.next()) {
                t = Arrays.asList(new String[]{r.getString("username"),r.getString("created"),r.getString("message")});
                palautus.add(t);
            }
            return palautus;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }    
}