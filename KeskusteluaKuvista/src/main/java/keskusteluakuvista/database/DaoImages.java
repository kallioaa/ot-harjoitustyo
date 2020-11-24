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
import java.sql.Statement;
import keskusteluakuvista.Image;

/**
 *
 * @author aatukallio
 */
public class DaoImages {
    
    private Integer image;
    private Connection db;
    private Integer n;
    
    public DaoImages(Connection db) {
        this.db = db;
        this.n = 0;
    }
    
    public Integer addImage(Image image) {
        Integer kuvanID = this.contains(image);
        if (kuvanID != -1) {
            return kuvanID;
        }
        else {
            try {
                PreparedStatement p  = db.prepareStatement("INSERT INTO Images (hashcode) VALUES (?)");
                p.setInt(1, image.hashCode());
                p.execute();
                n++;
                return n;
            } catch (SQLException e) {
                System.out.println(e);
                return null;
            }  
        }
    }
    
    public void printValues() {
        try {
            Statement s = this.db.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM Images");
            while (r.next()) {
                System.out.println(r.getInt("id") + " :" + r.getInt("hashcode"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private Integer contains(Image image) {
        try {
            PreparedStatement p  = db.prepareStatement("SELECT id FROM Images WHERE hashcode=?");
            p.setString(1, Integer.toString(image.hashCode()));
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
