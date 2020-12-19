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
import java.sql.SQLException;
import entities.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author aatukallio
 */
public class DaoUsers {
   
    private final Dao dao;
      
    public DaoUsers(Dao dao) {
        this.dao = dao;
    }
     
    /**
     * Check's if the account exists. 
     * @param username UserSession's user
     * @param password user's password
     * @return 
     */
    public User logIn(String username, String password) {
        String selectString = "SELECT id, passHashed, nOfComments, loggedIn from Users where username=?";
        User user = null;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dao.getdbUrl(), dao.getdbUsername(), dao.getdbPassword());
             PreparedStatement p = conn.prepareStatement(selectString)) {   
            p.setString(1, username);
            user = checkPassword(username, password, p);
            if (user != null) {
                changeStatus(user, 1, conn);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    
    private User checkPassword(String username, String password, PreparedStatement p) throws SQLException {
        User newUser = null;
        try (ResultSet r = p.executeQuery()) {
            while (r.next()) {
                if (BCrypt.checkpw(password, r.getString("passHashed")) & r.getInt("loggedIn") == 0) {
                    newUser = new User(r.getInt("id"), username, r.getInt("nOfComments"));      
                }
            }
        }
        return newUser;
    }
    
    public void logOut(User user) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dao.getdbUrl(), dao.getdbUsername(), dao.getdbPassword())) {
            changeStatus(user, 0, conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    
    }
    
    private void changeStatus(User user, Integer status, Connection conn) {
        String updateString = "UPDATE Users SET loggedIn=? where username=?";
        try (PreparedStatement p = conn.prepareStatement(updateString)) { 
            p.setInt(1, status);
            p.setString(2, user.getUsername());
            p.execute();
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
    public boolean addAccount(String username, String password) {
        boolean accountAdded = false;
        String insertString = "INSERT INTO Users (username, passHashed, salt, nOfComments, loggedIn) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dao.getdbUrl(), dao.getdbUsername(), dao.getdbPassword())) { 
            if (!contains(username, conn)) {
                try (PreparedStatement p  = conn.prepareStatement(insertString)) {
                    String salt = BCrypt.gensalt();
                    password = BCrypt.hashpw(password, salt);           
                    p.setString(1, username);
                    p.setString(2, password);
                    p.setString(3, salt);
                    p.setInt(4, 0);
                    p.setInt(5, 0);
                    p.execute();
                    accountAdded = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountAdded;
    }
    
    private boolean contains(String username, Connection conn) {
        String selectString = "SELECT * FROM Users WHERE username=?";
        boolean contains = false;
        
        try (PreparedStatement p  = conn.prepareStatement(selectString)) {
            p.setString(1, username);
            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    contains = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contains;
    }
}
