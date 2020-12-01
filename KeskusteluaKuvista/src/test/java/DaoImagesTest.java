
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import keskusteluakuvista.ImageToHash;
import keskusteluakuvista.database.*;
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
public class DaoImagesTest {
    private DaoImages imgdao;
    
    @Before
    public void setUp() {
        Dao dao = new Dao("resourcesDb/testDb1.db",true);
        this.imgdao = new DaoImages(dao.getConnection());
    }
    
    @Test
    public void addOneDogThreeTimes() throws IOException {
        BufferedImage img = null;
        img = ImageIO.read(new File("testImages","koira1.jpg"));
        imgdao.addImage(new ImageToHash(img));
        imgdao.addImage(new ImageToHash(img));
        assertTrue(1==imgdao.addImage(new ImageToHash(img)));  
    }
    
    @Test
    public void addAllDogAskSecond() throws IOException {
        BufferedImage img1 = null;
        BufferedImage img2 = null;
        BufferedImage img3 = null;
        img1 = ImageIO.read(new File("testImages","koira1.jpg"));
        img2 = ImageIO.read(new File("testImages","koira2.jpg"));
        img3 = ImageIO.read(new File("testImages","koira3.jpg"));
        imgdao.addImage(new ImageToHash(img1));
        imgdao.addImage(new ImageToHash(img2));
        imgdao.addImage(new ImageToHash(img2));
        imgdao.addImage(new ImageToHash(img1));
        imgdao.addImage(new ImageToHash(img3));
        imgdao.addImage(new ImageToHash(img3));
        imgdao.addImage(new ImageToHash(img1));
        imgdao.addImage(new ImageToHash(img2));
        assertTrue(2==imgdao.addImage(new ImageToHash(img2)));
    }
       
    
    
}
