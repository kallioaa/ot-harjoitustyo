
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import javax.imageio.ImageIO;
import junit.framework.Assert;
import imagechatter.entities.ImageToHash;
import imagechatter.database.Dao;
import imagechatter.database.FileDaoImages;
import static org.junit.Assert.*;
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
public class ImageToHashTest {

    /**
     * @param args the command line arguments
     */
    private List<ImageToHash> images;
    
    @Before
    public void setUp() throws Exception {
        List<ImageToHash> t = new ArrayList<>();
        for (int i=1;i<=16;i++) {
            t.add(new ImageToHash(ImageIO.read(new File("testImages","image" + i + ".jpeg"))));
        }
        this.images = t;
    }
    
    @Test
    public void uniqueHashCodes() throws IOException {
        ImageToHash image;
        ListIterator<ImageToHash> imagesIter = images.listIterator();
        HashSet<ImageToHash> test = new HashSet<>();
        boolean value = true;
        while (imagesIter.hasNext()) {
            image = imagesIter.next();
            if (test.contains(image)) {
                value = false;
                break;
            }
            test.add(image);
        }
        assertTrue(value);

    }
    
    
}
