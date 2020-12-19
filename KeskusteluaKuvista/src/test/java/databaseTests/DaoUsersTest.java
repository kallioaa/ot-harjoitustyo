/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseTests;

import java.io.File;
import keskusteluakuvista.database.Dao;
import keskusteluakuvista.database.DaoUsers;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author aatukallio
 */


public class DaoUsersTest {
    private static Dao dao;
    private static DaoUsers usersdb;
        
    @BeforeClass
    public static void setUpClass() {
        dao = new Dao("testDatabase.db");
        usersdb = new DaoUsers(dao);
    }
    
    @Test
    public void addUsernameFirstTime() {
        assertTrue(usersdb.addAccount("testiukkeli", "salasana"));        
    }
    
    @Test
    public void addSameUsernameTwoTimesWontWork() {
        usersdb.addAccount("testiukkeli", "salasana2");
        assertFalse(usersdb.addAccount("testiukkeli", "salasana"));        
    }
    
    @Test
    public void addDifferentUsernameWorks() {
        usersdb.addAccount("testiukkeli1", "salasana2");
        usersdb.addAccount("testiukkeli2", "salasana2");
        usersdb.addAccount("testiukkeli3", "salasana2");
        usersdb.addAccount("testiukkeli4", "salasana2");
        assertTrue(usersdb.addAccount("testiukkeli5", "salasana"));        
    }
    
    @Test
    public void loginWorksCorrectCredentials() {
        usersdb.addAccount("testiukkeli1", "salasana1");
        usersdb.addAccount("testiukkeli2", "salasana2");
        usersdb.addAccount("testiukkeli3", "salasana3");
        usersdb.addAccount("testiukkeli4", "salasana4");
        assertEquals("testiukkeli2",usersdb.logIn("testiukkeli2", "salasana2").getUsername());
    }
    
    @Test
    public void loginWorksWrongCredentials() {
        usersdb.addAccount("testiukkeli1", "salasana1");
        usersdb.addAccount("testiukkeli2", "salasana2");
        usersdb.addAccount("testiukkeli3", "salasana3");
        usersdb.addAccount("testiukkeli4", "salasana4");
        assertEquals(null, usersdb.logIn("testiukkeli2", "salasana1"));
    }
    
    @Test
    public void loginWorksWrongCredentials2() {
        usersdb.addAccount("testiukkeli1", "salasana1");
        usersdb.addAccount("testiukkeli2", "salasana2");
        usersdb.addAccount("testiukkeli3", "salasana3");
        usersdb.addAccount("testiukkeli4", "salasana4");
        assertEquals(null, usersdb.logIn("testiukkeli2", ""));
    }
 
    @Test
    public void loginWorksWrongCredentials3() {
        usersdb.addAccount("testiukkeli1", "salasana1");
        usersdb.addAccount("testiukkeli2", "salasana2");
        usersdb.addAccount("testiukkeli3", "salasana3");
        usersdb.addAccount("testiukkeli4", "salasana4");
        assertEquals(null, usersdb.logIn("testiukkeli2", "          sajd kasj kasj kldajsk jkas jkasj asjd lkasjd ska jdksa jka "));
    }
    
    
    @After
    public void clean() {
        File myObj = new File("testDatabase.db");
        myObj.delete();
        dao.initializeDb();
    }
    
    
}

