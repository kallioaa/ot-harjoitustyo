/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.logic;

import java.util.ArrayList;
import java.util.List;
import keskusteluakuvista.database.DaoChat;

/**
 *
 * @author aatukallio
 */
public class ChatLogic {
    
    private final DaoChat daoChat;
    private final UniversalLogic uniLogic;
    
    public ChatLogic(DaoChat daoChat,UniversalLogic uniLogic) {
        this.daoChat = daoChat;
        this.uniLogic = uniLogic;
    }
    
    public List<List<String>> showChat() {
        if (uniLogic.getImageId() != null) {
            return daoChat.getMessages(uniLogic.getImageId());
        }
        return new ArrayList<>();
    }
    
    public void addMessage(String text) {
        if (uniLogic.getImageId() != null && !text.isEmpty()) {
            this.daoChat.addMessage(uniLogic.getImageId(), uniLogic.getUser().getUsername(), text.strip());
        }
    }  
}
