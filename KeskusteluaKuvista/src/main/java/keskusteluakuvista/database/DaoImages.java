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
import keskusteluakuvista.ImageToHash;

/**
 *
 * @author aatukallio
 */
public class DaoImages {
    
    private Integer image;
    private Connection db;
    
    public DaoImages(Connection db) {
        this.db = db;
    }
    
    public Integer addImage(ImageToHash image) {
        Integer kuvanID = this.key(image);
        if (kuvanID != -1) {
            return kuvanID;
        }
        else {
            try {
                PreparedStatement p  = db.prepareStatement("INSERT INTO Images (hashcode) VALUES (?)");
                p.setInt(1, image.hashCode());
                p.execute();
                return this.numberOfRows();
            } catch (SQLException e) {
                System.out.println(e);
                return null;
            }  
        }
    }
    
    public Integer numberOfRows() {
        try {
            PreparedStatement p  = db.prepareStatement("SELECT COUNT(*) FROM Images");
            return p.executeQuery().getInt(1);
                    
        } catch (SQLException e) {
            return null;
        }
    }
    
    private Integer key(ImageToHash image) {
        try {
            PreparedStatement p  = db.prepareStatement("SELECT id FROM Images WHERE hashcode=?");
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
