/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagechatter.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import imagechatter.entities.ImageToHash;
import java.sql.Date;

/**
 *
 * Communicates with the database tables for image attributes.
 */
public class FileDaoImages implements DaoImages {
    
    private final Dao dao;
    
    public FileDaoImages(Dao dao) {
        this.dao = dao;
    }
    
    /**
    * Function adds an image to the database if it does not exist.
     * @param image ImageToHash object
     * @param username Session's user
     * @return Pictures ID (key in database)
    */
    //Function adds an image to the database if it does not exist.
    @Override
    public Integer addImage(ImageToHash image, String username) {
        String insertString = "INSERT INTO Images (hashcode) VALUES (?)";
        Integer id;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dao.getdbUrl(), dao.getdbUsername(), dao.getdbPassword())) {
            Integer kuvanID = this.key(image, conn);
            if (kuvanID != -1) {
                return kuvanID;
            } else {
                try (PreparedStatement p  = conn.prepareStatement(insertString)) {
                    p.setInt(1, image.hashCode());
                    p.execute();
                }
                id = this.numberOfRows(conn);
                setHistory(id, username, conn);                 
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }  
        return id; 
    }
    
    /**
     * Function set the history data for given image.
     * @param id Pictures key in Images table
     * @param username User of the session
     */
    
    private void setHistory(Integer id, String username, Connection conn) {
        String insertString = "INSERT INTO ImageHistory (id_image, username, created) VALUES (?, ?, CURRENT_TIMESTAMP)";
        try (PreparedStatement p  = conn.prepareStatement(insertString)) {
            p.setInt(1, id);
            p.setString(2, username);
            p.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Function returns the history data of the image.
     * @param id Pictures key in Images table
     * @return Image's history
     */
    @Override
    public List<String> getHistrory(Integer id) {
        String selectString = "SELECT id_image, username, datetime(created,'localtime') as created FROM ImageHistory WHERE id_image=?";
        
        List<String> returnList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dao.getdbUrl(), dao.getdbUsername(), dao.getdbPassword());
             PreparedStatement p  = conn.prepareStatement(selectString)) {
            p.setInt(1, id);
            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    returnList.add(String.valueOf(r.getInt("id_image")));
                    returnList.add(String.valueOf(r.getString("username")));
                    returnList.add(r.getString("created"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }   
        return returnList;
    }
    
    

    /**
     * @return Function return the number of images in the database.
     */
    private Integer numberOfRows(Connection conn) {
        String selectString = "SELECT COUNT(*) FROM Images";
        Integer rows;
        try (PreparedStatement p  = conn.prepareStatement(selectString)) {
            rows = p.executeQuery().getInt(1);                    
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }
    
    //Returns the id of a image if it exists in the database. Else, returns -1
    /**
     * Return the id of a certain image in the database. If it does not exists, returns -1.
     * @param image ImageToHash object
     * @return Integer
     */
    private Integer key(ImageToHash image, Connection conn) {
        String selectString = "SELECT id FROM Images WHERE hashcode=?";
        Integer key;
        try (PreparedStatement p  = conn.prepareStatement(selectString)) {
            p.setInt(1, image.hashCode());
            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    key = r.getInt("id");
                } else {
                    key = -1;
                }
            }  
        } catch (SQLException e) {
            throw new RuntimeException(e); 
        }
        return key;
    }    
}
