/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aatukallio
 */

public class DaoChat {
        
    public Dao dao;
    
    public DaoChat(Dao dao) {
        this.dao = dao;
    }
    
    /**
     * Function adds a message to message table for given image
     * @param id image id
     * @param username Session's user
     * @param text Message from the user
     */
    public void addMessage(Integer id, String username, String text) {
        String insertString = "INSERT INTO Messages (id_image, message, username, created) VALUES (?, ?, ?, CURRENT_TIMESTAMP);";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dao.getdbUrl(), dao.getdbUsername(), dao.getdbPassword());
             PreparedStatement p = conn.prepareStatement(insertString)){
            p.setInt(1, id);
            p.setString(2, text);
            p.setString(3, username);
            p.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    //Runction returns the messages for given image_id
    /**
     * Method returns the messages for given image_id
     * @param id image id
     * @return Message information: username,timestamp and the message
     */
    public List<List<String>> getMessages(Integer id) {
        String selectString = "SELECT username, created, message FROM Messages WHERE id_image =?;";
        List<List<String>> palautus = new ArrayList<>();
        List<String> t;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dao.getdbUrl(), dao.getdbUsername(), dao.getdbPassword());
             PreparedStatement p  = conn.prepareStatement(selectString)){    
            p.setString(1, Integer.toString(id));
            ResultSet r = p.executeQuery();
            while (r.next()) {
                t = Arrays.asList(new String[]{r.getString("username"),r.getString("created"),r.getString("message")});
                palautus.add(t);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return palautus;
    }    
}