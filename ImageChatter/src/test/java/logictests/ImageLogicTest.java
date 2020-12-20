/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logictests;


import imagechatter.entity.ImageToHash;
import imagechatter.entity.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import imagechatter.database.FileDaoImages;
import imagechatter.logic.ImageIOStub;
import imagechatter.logic.ImageLogic;
import imagechatter.logic.UniversalLogic;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author aatukallio
 */
public class ImageLogicTest {
    ImageIOStub imageLoader;
    FileDaoImages daoImages;
    UniversalLogic uniLogic;
    ImageLogic imageLogic;
            
    @Before
    public void setUp() {
        daoImages = mock(FileDaoImages.class);
        uniLogic = mock(UniversalLogic.class);
        imageLoader = mock(ImageIOStub.class);
        imageLogic = new ImageLogic(daoImages, uniLogic,imageLoader);
    }
    
    @Test
    public void imageHistoryNullId() {
        when(uniLogic.getImageId()).thenReturn(null);
        assertEquals(0,imageLogic.getImageHistory().size());
    }
    
    @Test
    public void imageHistoryIdExists() {
        List<String> history = Arrays.asList(new String[]{"1","Aatu Kallio","someTime"});
        when(uniLogic.getImageId()).thenReturn(1);
        when(daoImages.getHistrory(1)).thenReturn(history);
        assertEquals(history,imageLogic.getImageHistory());
    }
    
    @Test
    public void searchImageWrongUrl() throws Exception {
        String urlString = "https://docs.oracle.com/javase/7/docs/api/index.html?javax/imageio/ImageIO.html";
        URL url = new URL(urlString);
        User testUser = new User(1,"Aatu",1);
        BufferedImage testImage = ImageIO.read(new File("testImages","koira1.jpg"));
        when(imageLoader.read(url)).thenReturn(testImage);
        when(uniLogic.getUser()).thenReturn(testUser);
        imageLogic.searchImageID(urlString);
        verify(daoImages,times(1)).addImage(refEq(new ImageToHash(testImage)), eq("Aatu"));
    }
    
    
    
   
    
    
    
    
    
    
    
    
    
}
