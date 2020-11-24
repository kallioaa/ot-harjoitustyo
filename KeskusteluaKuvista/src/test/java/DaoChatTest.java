
import java.util.Random;
import keskusteluakuvista.database.Dao;
import keskusteluakuvista.database.DaoChat;
import keskusteluakuvista.database.DaoImages;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
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

    private DaoChat chatdb;
    
    @Before
    public void setUp() {
        Integer uusi = new Random().nextInt(1000);
        Dao dao = new Dao("resourcesDb/testDb" + uusi + ".db");
        this.chatdb = new DaoChat(dao.getConnection());
    }
    
    @Test
    public void addChat() {
        chatdb.addMessage(1, "Moikka miten menee");
        chatdb.addMessage(1, "Erittain huonosti");
        assertTrue(2==chatdb.getMessages(1).size());
        
    }
    
    
    
}
