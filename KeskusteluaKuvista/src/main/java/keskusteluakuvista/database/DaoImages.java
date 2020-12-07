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
 * @author aatukallio
 */
public class DaoImages {
    
    private static DaoImages daoImages_instance;
    private final Connection conn;
    
    
    private DaoImages() {
        this.conn = Dao.getInstance().conn;
    }
    
    public static DaoImages getInstance() {
        if (daoImages_instance == null) {
            daoImages_instance = new DaoImages();
        }
        return daoImages_instance;
    }
    
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
    
    
    private Integer numberOfRows() {
        try {
            PreparedStatement p  = conn.prepareStatement("SELECT COUNT(*) FROM Images");
            return p.executeQuery().getInt(1);
                    
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
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
