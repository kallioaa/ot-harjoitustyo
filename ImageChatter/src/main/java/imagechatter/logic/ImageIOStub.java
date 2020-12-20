/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagechatter.logic;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *Class is created to ease the testing.
 * @author aatukallio
 */
public class ImageIOStub {
    
    /**
     * Returns a BudfferdImage from the URL.
     * @param url
     * @return Buffered image
     * @throws IOException 
     */
    public BufferedImage read(URL url) throws IOException {
        return ImageIO.read(url);
    }
}
