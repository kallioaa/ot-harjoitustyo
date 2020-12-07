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
import keskusteluakuvista.logic.Session;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author aatukallio
 */
public class DaoUsers {
    
    private static DaoUsers daoUsers_instance;
    private final Connection conn;
    
    
    private DaoUsers() {
        this.conn = Dao.getInstance().conn;
    }
    
    public static DaoUsers getInstance() {
        if (daoUsers_instance == null) {
            daoUsers_instance = new DaoUsers();
        }
        return daoUsers_instance;
    }
        
    
    public boolean login(String username, String password) {
        try {   
            PreparedStatement p = conn.prepareStatement("SELECT id, passHashed, nOfComments from Users where username=?");
            p.setString(1, username);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                if (BCrypt.checkpw(password, r.getString("passHashed"))){
                    Session.getInstance(r.getInt("id"),username,r.getInt("nOfComments"));
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean addAccount(String username,String password) {
        if (this.contains(username)) {
            return false;
        }
        try {
            String salt = BCrypt.gensalt();
            password = BCrypt.hashpw(password, salt);
            PreparedStatement p = conn.prepareStatement("INSERT INTO Users (username, passHashed, salt, nOfComments) VALUES (?, ?, ?, ?)");
            p.setString(1, username);
            p.setString(2, password);
            p.setString(3, salt);
            p.setInt(4, 0);
            p.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private boolean contains(String username) {
        try {
            PreparedStatement p  = conn.prepareStatement("SELECT * FROM Users WHERE username=?");
            p.setString(1, username);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                return true;
            }
            return false;            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
