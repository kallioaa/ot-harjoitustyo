/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import keskusteluakuvista.database.DatabaseImages;
import keskusteluakuvista.ImageToHash;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author aatukallio
 */
public class DatabaseImagesTest {
    DatabaseImages database;
    
    public DatabaseImagesTest() {
    }
    /*
    @Before
    public void setUp() {
        this.database = new DatabaseImages();
        
    }
    //The index should be one for the image.
    @Test
    public void addOneDogThreeTimes() throws IOException {
        BufferedImage img = null;
        img = ImageIO.read(new File("testImages","koira1.jpg"));
        database.imageID(new ImageToHash(img));
        database.imageID(new ImageToHash(img));
        assertTrue(1==database.imageID(new ImageToHash(img)));  
    }
    
    @Test
    public void addAllDogAskSecond() throws IOException {
        BufferedImage img1 = null;
        BufferedImage img2 = null;
        BufferedImage img3 = null;
        img1 = ImageIO.read(new File("testImages","koira1.jpg"));
        img2 = ImageIO.read(new File("testImages","koira2.jpg"));
        img3 = ImageIO.read(new File("testImages","koira3.jpg"));
        database.imageID(new ImageToHash(img1));
        database.imageID(new ImageToHash(img2));
        database.imageID(new ImageToHash(img2));
        database.imageID(new ImageToHash(img1));
        database.imageID(new ImageToHash(img3));
        database.imageID(new ImageToHash(img3));
        database.imageID(new ImageToHash(img1));
        database.imageID(new ImageToHash(img2));
        assertTrue(2==database.imageID(new ImageToHash(img2)));  
    }
    */
    
}
