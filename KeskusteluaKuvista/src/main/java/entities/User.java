/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author aatukallio
 */
public class User {
    
    private final Integer userId;
    private final String username;
    private final Integer nOfComments;
    
    public User(Integer userId, String username, Integer nOfComments) {
        this.userId = userId;
        this.username = username;
        this.nOfComments = nOfComments;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public Integer nOfComments() {
        return nOfComments;
    }
}
