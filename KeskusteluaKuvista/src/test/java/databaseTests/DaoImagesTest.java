package databaseTests;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import keskusteluakuvista.ImageToHash;
import keskusteluakuvista.database.*;
import org.junit.After;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
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
    private static DaoImages imgdao;
    private static BufferedImage img1;
    private static BufferedImage img2;
    private static BufferedImage img3;
    
    
    @BeforeClass
    public static void setUp() throws IOException {
        File file = new File("resourcesDb/kkDatabase.db");
        file.delete();
        imgdao = DaoImages.getInstance();
        img1 = ImageIO.read(new File("testImages","koira1.jpg"));
        img2 = ImageIO.read(new File("testImages","koira2.jpg"));
        img3 = ImageIO.read(new File("testImages","koira3.jpg"));
        imgdao.addImage(new ImageToHash(img1),"Kalle");
        imgdao.addImage(new ImageToHash(img2),"Pekka");
        imgdao.addImage(new ImageToHash(img3),"Kalle");
    }
 
    @Test
    public void dogOneIdIsOne() throws IOException{
        assertEquals(1,(int) imgdao.addImage(new ImageToHash(img1),"Kalle"));
    }
    
    @Test
    public void dogtwoIdIstwo() throws IOException{
        assertEquals(2,(int) imgdao.addImage(new ImageToHash(img2),"Kalle"));
    }
    
    @Test
    public void dogThreeIdIsThree() {
        assertEquals(3,(int) imgdao.addImage(new ImageToHash(img3),"Kalle"));
    }
    
    @Test
    public void dogTwohistoryCorrectValues() throws IOException {
        List<String> history = imgdao.getHistrory(2);
        assertArrayEquals(new String[]{"2","Pekka"},new String[]{history.get(0),history.get(1)});   
    }    
}
