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
import java.util.List;
import keskusteluakuvista.ImageToHash;

/**
 *
 * Communicates with the database tables for image attributes.
 */
public class DaoImages {
    
    private static DaoImages daoImages_instance;
    private final Connection conn;
    
    
    private DaoImages() {
        this.conn = Dao.getInstance().conn;
    }
    
    private DaoImages(Connection conn) {
        this.conn = conn;
    }
    
    /**
    * Singleton constructor for DaoImages.
    * @author aatukallio
    */
    public static DaoImages getInstance() {
        if (daoImages_instance == null) {
            daoImages_instance = new DaoImages();
        }
        return daoImages_instance;
    }
    /**
     * Singleton constructor for DaoImages. Used by test classes.
     * @param conn Connection object
     * @return 
     */
    public static DaoImages getInstance(Connection conn) {
        if (daoImages_instance == null) {
            daoImages_instance = new DaoImages(conn);
        }
        return daoImages_instance;
    }
    
    /**
    * Function adds an image to the database if it does not exist.
     * @param image ImageToHash object
     * @param username session's user
     * @return Pictures ID (key in database)
    */
    //Function adds an image to the database if it does not exist.
    public Integer addImage(ImageToHash image,String username) {
        Integer kuvanID = this.key(image);
        if (kuvanID != -1) {
            return kuvanID;
        }
        else {
            try {
                PreparedStatement p  = conn.prepareStatement("INSERT INTO Images (hashcode) VALUES (?)");
                p.setInt(1, image.hashCode());
                p.execute();
                Integer id = this.numberOfRows();
                setHistory(id,username);
                return id;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }  
        }
    }
    
    /**
     * Function set the history data for given image.
     * @param id Pictures key in Images table
     * @param username User of the session
     */
    
    private void setHistory(Integer id,String username) {
        try {
            PreparedStatement p  = conn.prepareStatement("INSERT INTO ImageHistory (id_image, username, created) VALUES (?, ?, CURRENT_TIMESTAMP)");
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
     * @return 
     */
    public List<String> getHistrory(Integer id) {
        List<String> returnList = new ArrayList<>();
        try {
            PreparedStatement p  = conn.prepareStatement("SELECT id_image, username, created FROM ImageHistory WHERE id_image=?");
            p.setInt(1, id);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                returnList.add(String.valueOf(r.getInt("id_image")));
                returnList.add(String.valueOf(r.getString("username")));
                returnList.add(r.getString("created"));
            }
            return returnList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }   
    }
    

    /**
     * @return Function return the number of images in the database.
     */
    private Integer numberOfRows() {
        try {
            PreparedStatement p  = conn.prepareStatement("SELECT COUNT(*) FROM Images");
            return p.executeQuery().getInt(1);
                    
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //Returns the id of a image if it exists in the database. Else, returns -1
    /**
     * Return the id of a certain image in the database. If it does not exists, returns -1.
     * @param image ImageToHash object
     * @return Integer
     */
    private Integer key(ImageToHash image) {
        try {
            PreparedStatement p  = conn.prepareStatement("SELECT id FROM Images WHERE hashcode=?");
            p.setInt(1, image.hashCode());
            ResultSet r = p.executeQuery();
            if (r.next()) {
                return r.getInt("id");
            } else {
                return -1;
            }                
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
