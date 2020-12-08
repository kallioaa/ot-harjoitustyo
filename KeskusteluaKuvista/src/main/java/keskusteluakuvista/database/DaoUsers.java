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
    
    private DaoUsers(Connection conn) {
        this.conn = conn;
    }
    
    /**
     * Singleton constructor for DaoUsers. Used by test classes.
     * @param conn Connection object
     * @return 
     */
    public static DaoUsers getInstance(Connection conn) {
        if (daoUsers_instance == null) {
            daoUsers_instance = new DaoUsers(conn);
        }
        return daoUsers_instance;
    }
    
    /**
     * Singleton constructor for DaoUsers.
     * @return 
     */
    public static DaoUsers getInstance() {
        if (daoUsers_instance == null) {
            daoUsers_instance = new DaoUsers();
        }
        return daoUsers_instance;
    }
        
    /**
     * Check's if the account exists. 
     * @param username Session's user
     * @param password user's password
     * @return 
     */
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
    /**
     * Tries to create a new user to the database. 
     * @param username 
     * @param password
     * @return Returns True if new user is created. False if there already exists an user with this username.
     */
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
