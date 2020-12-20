/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicTests;

import imagechatter.entities.User;
import imagechatter.database.FileDaoUsers;
import imagechatter.logic.UniversalLogic;
import imagechatter.logic.UserLogic;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



/**
 *
 * @author aatukallio
 */
public class UserLogicTest {
    
    private static UserLogic userLogic;
    private static FileDaoUsers daoUsers;
    private static UniversalLogic uniLogic;
    
    
    @Before
    public void setUp() {
        uniLogic = mock(UniversalLogic.class);
        daoUsers = mock(FileDaoUsers.class);
        userLogic = new UserLogic(daoUsers,uniLogic);
    }
    
    @Test
    public void logInCalled() {
        userLogic.logIn("Testiukkeli", "123");
        verify(daoUsers, times(1)).logIn("Testiukkeli", "123");
    }
    
    @Test
    public void trueWhenLogInWorks() {
        User testUser = new User(1,"Timo",1);
        when(daoUsers.logIn("Timo", "12345678")).thenReturn(testUser);
        assertTrue(userLogic.logIn("Timo", "12345678"));
    }
    
    @Test
    public void falseWhenLogInFailed() {
        when(daoUsers.logIn("Timo", "12345678")).thenReturn(null);
        assertFalse(userLogic.logIn("Timo", "12345678"));
    }
    
    @Test
    public void uniLogicSetUserCalled() {
        User testUser = new User(1,"Timo",1);
        when(daoUsers.logIn("Timo", "12345678")).thenReturn(testUser);
        userLogic.logIn("Timo", "12345678");
        verify(uniLogic,times(1)).setUser(testUser);
    }
    
    @Test
    public void logOutCallsRightMethods() {
        User testUser = new User(666,"Jeppa",123);
        when(uniLogic.getUser()).thenReturn(testUser);
        userLogic.logOut();
        verify(daoUsers, times(1)).logOut(testUser);
    }
    
    @Test
    public void createUserCorrectCredentialsTrue() {
        String username = "pekka";
        String password = "12345678";
        
        when(daoUsers.addAccount(username, password)).thenReturn(true);
        assertEquals("Success",userLogic.createUser(username, password, password));
    }
    
    @Test
    public void passwordHasSpaceCorrectMessage() {
        String username = "pekka";
        String password = "123456 7 8";
        assertEquals("Password length is under 8 or is has a whitespace.",userLogic.createUser(username, password, password));
    }
    
    @Test
    public void tooShortCorrectMessage() {
        String username = "pekka";
        String password = "1234567";
        assertEquals("Password length is under 8 or is has a whitespace.",userLogic.createUser(username, password, password));
    }
    
    @Test
    public void validationPasswordIncorrect() {
        String username = "pekka";
        String password = "12345678";
        String passwordValidation = "12345679";
        assertEquals("Passwords are not the same.",userLogic.createUser(username, password, passwordValidation));
    }   
    
    @Test
    public void createUserCorrectCredentialsTrueExistsBefore() {
        String username = "pekka";
        String password = "12345678";
        
        when(daoUsers.addAccount(username, password)).thenReturn(false);
        assertEquals("Username is taken",userLogic.createUser(username, password, password));
    }   
}

