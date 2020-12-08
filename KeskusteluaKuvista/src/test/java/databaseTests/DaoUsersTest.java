/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseTests;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import keskusteluakuvista.ImageToHash;
import keskusteluakuvista.database.DaoImages;
import keskusteluakuvista.database.DaoUsers;
import org.junit.BeforeClass;

/**
 *
 * @author aatukallio
 */
public class DaoUsersTest {
    
    private static DaoUsers imgdao;
    
    @BeforeClass
    public static void setUp() throws IOException {
        imgdao = DaoUsers.getInstance();
      
    }
}
