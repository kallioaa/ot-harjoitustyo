package databaseTests;


import java.io.File;
import java.util.List;
import keskusteluakuvista.database.DaoChat;
import org.junit.After;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aatukallio
 */


public class DaoChatTest {
    
    private static DaoChat chatdb;
    
    
    @BeforeClass
    public static void setUp() {
        chatdb = DaoChat.getInstance();
        chatdb.addMessage(1, "Aatu Kallio","Hieno kuva");
        chatdb.addMessage(2, "George","Hieno juttu");
        chatdb.addMessage(2, "Spiderman and elsa","Hieno tuttu");
        chatdb.addMessage(3, "Jeejee","Hieno muttu");
        chatdb.addMessage(3, "Jeejee","Hieno muttu");
        chatdb.addMessage(5, "Spiderman","Hieno kuva");
        chatdb.addMessage(2, "Spiderman and elsa","Cool");
        chatdb.addMessage(5, "Aatu Kallio","Hieno kuva");
        
    }
    
    @Test
    public void correctNumberOfChatIdOne() {
        assertEquals(1,chatdb.getMessages(1).size());
    }
    
    @Test
    public void correctNumberOfChatIdTwo() {
        assertEquals(3,chatdb.getMessages(2).size());
    }
    
    @Test
    public void chatContainsMessage() {
        List<List<String>> pictureTwoChat = chatdb.getMessages(3);
        assertArrayEquals(new String[]{"Jeejee","Hieno muttu"},new String[]{pictureTwoChat.get(0).get(0),pictureTwoChat.get(0).get(2)});
    } 
}
