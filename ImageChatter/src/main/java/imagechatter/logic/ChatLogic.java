/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagechatter.logic;

import java.util.ArrayList;
import java.util.List;
import imagechatter.database.DaoChats;

/**
 * Handles logic for the chat.
 * @author aatukallio
 */
public class ChatLogic {
    
    private final DaoChats daoChat;
    private final UniversalLogic uniLogic;
    
    public ChatLogic(DaoChats daoChat, UniversalLogic uniLogic) {
        this.daoChat = daoChat;
        this.uniLogic = uniLogic;
    }
    
    /**
     * Returns the chat for the ImageID defined in UniversalLogic. Empty chat if no imageID is defined.
     * @return The chat of a picture
     */
    public List<List<String>> showChat() {
        if (uniLogic.getImageId() != null) {
            return daoChat.getMessages(uniLogic.getImageId());
        }
        return new ArrayList<>();
    }
    
    /**
     * Adds a message to an image's chat if the message is not empty and imageID is not null in UniversalLogic.
     * @param text Message
     */
    public void addMessage(String text) {
        if (uniLogic.getImageId() != null && !text.isEmpty()) {
            this.daoChat.addMessage(uniLogic.getImageId(), uniLogic.getUser().getUsername(), text.strip());
        }
    }  
}
