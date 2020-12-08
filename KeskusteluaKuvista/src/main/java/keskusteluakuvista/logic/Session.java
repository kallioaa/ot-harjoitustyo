/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.logic;

import keskusteluakuvista.database.DaoUsers;

/**
 *
 * @author aatukallio
 */
//This class contains the information about the 
public class Session {
    
    private static Session session_instance;
    private Integer userId;
    private String username;
    private Integer numberOfComments;
    
    private Session(Integer userId, String username, Integer numberOfComments) {
        this.userId = userId;
        this.username = username;
        this.numberOfComments = numberOfComments;
    }
    
    //Singleton constructor for session method. Never return null.
    public static Session getInstance(Integer userId, String username, Integer numberOfComments) {
        if (session_instance == null) {
            session_instance = new Session(userId,username,numberOfComments);
        }
        return session_instance;
    }
    
    //return the instance without te need to supply constructor parameters. Can eturn null
    public static Session getInstance() {
        return session_instance;
    }
    
    public void resetSession() {
        session_instance = null;
        userId = null;
        username = null;
        numberOfComments = null;
    }
    
    public String gerUsername() {
        return this.username;
    }
    
    /*
    public Integer getNumberOfComments() {
        return this.numberOfComments;
    }
    
    
    public int getUserId() {
        return this.userId;
    }
    */
    
    /**
     * Method is checks if the new user information is in a correct format. 
     * @param username
     * @param password
     * @param passwordCheck
     * @return 
     */
    
    public static String createUser(String username, String password, String passwordCheck) {
        if (password.length() >= 8 && !password.contains("\\S")) {
            if (password.equals(passwordCheck)) {
                boolean created = DaoUsers.getInstance().addAccount(username,password);
                if (!created) {
                    return "Username is taken";
                }
                return "Success";
            }
            return "Passwords are not the same.";
        }
        return "Password length is under 8 or is has a whitespace.";
    }
         
    public static boolean SignIn(String username, String password) {
        return DaoUsers.getInstance().login(username, password);
    }
}
