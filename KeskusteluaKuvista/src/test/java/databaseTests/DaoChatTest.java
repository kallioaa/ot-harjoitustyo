package databaseTests;


import java.io.File;
import java.util.List;
import keskusteluakuvista.database.Dao;
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
    
    private static Dao dao;
    private static DaoChat chatdb;
        
    @BeforeClass
    public static void setUpClass() {
        dao = new Dao();
        chatdb = new DaoChat(dao);
    }
    
    @Test
    public void correctNumberOfChatIdOne() {
        chatdb.addMessage(1, "Aatu Kallio","Hieno kuva");
        assertEquals(1,chatdb.getMessages(1).size());
    }
    
    
    @Test
    public void correctNumberOfmessagesIdTwo() {
        chatdb.addMessage(1, "Aatu Kallio","Hieno kuva");
        chatdb.addMessage(2, "Kauhea kuva","Hieno kuva");
        chatdb.addMessage(1, "Aatu Kallio","ei hyvä");
        chatdb.addMessage(2, "Aatu Kallio","Hieno kuva");
        assertEquals(2,chatdb.getMessages(1).size());
    }
    
    @Test
    public void chatContainsMessage() {
        chatdb.addMessage(3, "Aatu Kallio","jeejee");
        chatdb.addMessage(3, "Kauhea kuva","Hieno kuva");
        chatdb.addMessage(3, "Aatu Kallio","ei hyvä");
        List<List<String>> pictureTwoChat = chatdb.getMessages(3);
        assertArrayEquals(new String[]{"Aatu Kallio","jeejee"},new String[]{pictureTwoChat.get(0).get(0),pictureTwoChat.get(0).get(2)});
    } 
    
    
    @After
    public void clean() {
        File myObj = new File("kkDatabase.db");
        myObj.delete();
        dao.initializeDb();
    }
}

