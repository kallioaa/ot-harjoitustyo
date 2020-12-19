/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.database;

import java.util.List;

/**
 *
 * @author aatukallio
 */
public interface DaoChats {

    /**
     * Function adds a message to message table for given image
     * @param id image id
     * @param username Session's user
     * @param text Message from the user
     */
    void addMessage(Integer id, String username, String text);

    //Runction returns the messages for given image_id
    /**
     * Method returns the messages for given image_id
     * @param id image id
     * @return Message information: username,timestamp and the message
     */
    List<List<String>> getMessages(Integer id);
    
}
