
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import keskusteluakuvista.Image;
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
        Dao dao = new Dao("resourcesDb/testDb.db");
        this.imgdao = new DaoImages(dao.getConnection());
    }
    
    @Test
    public void addOneDogThreeTimes() throws IOException {
        BufferedImage img = null;
        img = ImageIO.read(new File("testImages","koira1.jpg"));
        imgdao.addImage(new Image(img));
        imgdao.addImage(new Image(img));
        assertTrue(1==imgdao.addImage(new Image(img)));  
    }
    
    
    
}
