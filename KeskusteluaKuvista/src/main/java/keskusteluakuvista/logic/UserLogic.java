/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.logic;

import entities.User;
import keskusteluakuvista.database.DaoUsers;


/**
 *
 * @author aatukallio
 */
public class UserLogic {
    
    private final DaoUsers daoUsers;
    private final UniversalLogic uniLogic;
    
    public UserLogic(DaoUsers daoUsers, UniversalLogic uniLogic) {
        this.daoUsers = daoUsers;
        this.uniLogic = uniLogic;
    }
    
    
    public boolean logIn(String username, String password) {
        User sessionUser = daoUsers.logIn(username, password);
        if (sessionUser != null) {
            this.uniLogic.setUser(sessionUser);
            return true;
        }
        return false;
    }
    
    public void logOut() {
        daoUsers.logOut(uniLogic.getUser());
    }
    
    public String createUser(String username, String password, String passwordCheck) {
        if (password.length() >= 8 & !password.contains(" ")) {
            if (password.equals(passwordCheck)) {
                boolean created = daoUsers.addAccount(username, password);
                if (!created) {
                    return "Username is taken";
                }
                return "Success";
            }
            return "Passwords are not the same.";
        }
        return "Password length is under 8 or is has a whitespace.";
    }
}
