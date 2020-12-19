/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicTests;

import entities.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import keskusteluakuvista.database.FileDaoChats;
import keskusteluakuvista.logic.ChatLogic;
import keskusteluakuvista.logic.UniversalLogic;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author aatukallio
 */
public class ChatLogicTest {
    
    private static ChatLogic chatLogic;
    private static FileDaoChats daoChat;
    private static UniversalLogic uniLogic;
    
    
    @Before
    public void setUp() {
        uniLogic = mock(UniversalLogic.class);
        daoChat = mock(FileDaoChats.class);
        chatLogic = new ChatLogic(daoChat,uniLogic);
    }
    
    @Test
    public void callsRightMethods() {
        when(uniLogic.getImageId()).thenReturn(1);
        chatLogic.showChat();
        verify(uniLogic,times(2)).getImageId();
        verify(daoChat,times(1)).getMessages(1);
    }
    
    @Test
    public void noImageEmptyChat() {
        when(uniLogic.getImageId()).thenReturn(null);
        assertEquals(0,chatLogic.showChat().size());
    }
    
    @Test
    public void returnsCorrectChatImageExists() {
        List<List<String>> chat = new ArrayList();
        chat.add(Arrays.asList(new String[]{"Testiukko","4:20","huutista"}));
        when(uniLogic.getImageId()).thenReturn(1);
        when(daoChat.getMessages(1)).thenReturn(chat);
        assertEquals(chat,chatLogic.showChat());
    }
    
    @Test
    public void emptyTextNoCallToDb() {
        chatLogic.addMessage("");
        verify(daoChat,times(0)).addMessage(anyInt(),anyString() , anyString());
    }
    
    @Test
    public void noImageNoCallToDb() {
        when(uniLogic.getImageId()).thenReturn(null);
        chatLogic.addMessage("");
        verify(daoChat,times(0)).addMessage(anyInt(),anyString() , anyString());
    }
    
    @Test
    public void addMessageCallWithRightValues() {
        String text = "hellokaikille";
        User testUser = new User(1,"HelloMani",1);
        when(uniLogic.getUser()).thenReturn(testUser);
        when(uniLogic.getImageId()).thenReturn(1);
        chatLogic.addMessage(text);
        verify(daoChat,times(1)).addMessage(1,"HelloMani" ,text);
    }
    
    
    
    
    

    
    
    
    
    
    
}
