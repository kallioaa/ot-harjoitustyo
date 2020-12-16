/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.logic;

import entities.User;

/**
 *
 * @author aatukallio
 */
public class UniversalLogic {
    
    private User user;
    private Integer imageId;
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }
    
    public Integer getImageId() {
        return this.imageId;
    }
}
