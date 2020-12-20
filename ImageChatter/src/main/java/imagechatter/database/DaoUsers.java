/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagechatter.database;

import imagechatter.entities.User;

/**
 *
 * @author aatukallio
 */
public interface DaoUsers {

    /**
     * Tries to create a new user to the database.
     * @param username
     * @param password
     * @return Returns True if new user is created. False if there already exists an user with this username.
     */
    boolean addAccount(String username, String password);

    /**
     * Check's if the account exists.
     * @param username UserSession's user
     * @param password user's password
     * @return
     */
    User logIn(String username, String password);

    void logOut(User user);
    
}
