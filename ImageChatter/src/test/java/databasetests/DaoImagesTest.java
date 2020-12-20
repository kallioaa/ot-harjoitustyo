package databasetests;


import imagechatter.database.Dao;
import imagechatter.database.FileDaoImages;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import imagechatter.entity.ImageToHash;
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

public class DaoImagesTest {
    private static Dao dao;
    private static FileDaoImages imgdao;
    private static BufferedImage img1;
    private static BufferedImage img2;
    private static BufferedImage img3;
    
    
    @BeforeClass
    public static void setUpClass() throws IOException {
        dao = new Dao("testDatabase.db");
        img1 = ImageIO.read(new File("testImages","koira1.jpg"));
        img2 = ImageIO.read(new File("testImages","koira2.jpg"));
        img3 = ImageIO.read(new File("testImages","koira3.jpg"));
        imgdao = new FileDaoImages(dao);
    }    
 
    @Test
    public void firstDogOneTime() throws Exception {
        assertEquals(1,(int) imgdao.addImage(new ImageToHash(img1),"Kalle"));
    }
    
    @Test
    public void firstDogMultipleTimes() throws Exception {
        imgdao.addImage(new ImageToHash(img1),"Kalle");
        imgdao.addImage(new ImageToHash(img1),"Jussi");
        imgdao.addImage(new ImageToHash(img1),"Hermanni");
        imgdao.addImage(new ImageToHash(img1),"Tortilla");               
        assertEquals(1,(int) imgdao.addImage(new ImageToHash(img1),"Kalle"));
    }
    
    
    @Test
    public void dogThreeIdIsThree() throws Exception {
        imgdao.addImage(new ImageToHash(img1),"Kalle");
        imgdao.addImage(new ImageToHash(img2),"Jussi");
        imgdao.addImage(new ImageToHash(img3),"Hermanni");
        assertEquals(3,(int) imgdao.addImage(new ImageToHash(img3),"Kalle"));
    }
    
    @Test
    public void dogTwohistoryCorrectValues() throws Exception {
        imgdao.addImage(new ImageToHash(img1),"Pekka");
        imgdao.addImage(new ImageToHash(img2),"Helenius");
        imgdao.addImage(new ImageToHash(img2),"Justus");
        List<String> history = imgdao.getHistrory(2);
        assertArrayEquals(new String[]{"2","Helenius"},new String[]{history.get(0),history.get(1)});   
    }    
    
    @After
    public void clean() {
        File myObj = new File("testDatabase.db");
        myObj.delete();
        dao.initializeDb();
    }
}
