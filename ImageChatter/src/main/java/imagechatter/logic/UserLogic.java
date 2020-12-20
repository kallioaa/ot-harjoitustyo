/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagechatter.logic;

import imagechatter.entities.User;
import imagechatter.database.DaoUsers;


/**
 * Handles logic for the User
 * @author aatukallio
 */
public class UserLogic {
    
    private final DaoUsers daoUsers;
    private final UniversalLogic uniLogic;
    
    public UserLogic(DaoUsers daoUsers, UniversalLogic uniLogic) {
        this.daoUsers = daoUsers;
        this.uniLogic = uniLogic;
    }
    
    /**
     * If login is successful new user is set for the universal logic.
     * @param username Username
     * @param password Password
     * @return True if user is logged in and false if not
     */
    public boolean logIn(String username, String password) {
        User sessionUser = daoUsers.logIn(username, password);
        if (sessionUser != null) {
            this.uniLogic.setUser(sessionUser);
            return true;
        }
        return false;
    }
    
    /**
     * Asks the DaoUsers to logout the current user.
     */
    public void logOut() {
        daoUsers.logOut(uniLogic.getUser());
    }
    
    /**
     * Checks if the credentials are in a correct format. If they are method asks DaoUsers to add a new user.
     * @return String about what went wrong. Null if a new account is created.
    */
    public String createUser(String username, String password, String passwordCheck) {
        if (password.length() >= 8 & !password.contains(" ")) {
            if (password.equals(passwordCheck)) {
                boolean created = daoUsers.addAccount(username, password);
                if (!created) {
                    return "Username is taken";
                }
                return null;
            }
            return "Passwords are not the same.";
        }
        return "Password length is under 8 or is has a whitespace.";
    }
}
