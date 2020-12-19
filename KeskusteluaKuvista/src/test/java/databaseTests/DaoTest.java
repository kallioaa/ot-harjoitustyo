/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseTests;

import java.io.File;
import keskusteluakuvista.database.Dao;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author aatukallio
 */
public class DaoTest {
    
    @Before
    public void removeDatabase() {
        File myObj = new File("kkDatabase.db");
        myObj.delete();
    }
    
    @Test
    public void databaseIsCreated() throws Exception {
        new Dao("testDatabase.db");
        File myObj = new File("testDatabase.db");
        assertTrue(myObj.exists());
    }
}
