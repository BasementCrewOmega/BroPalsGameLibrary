/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.state.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The class to load Assets into an AssetBank.
 * @author Kevin Prehn
 */
public class AssetLoader {
    
    private Class mainClass;
    
    /**
     * Make an AssetLoader for the given main class. This class's location is where
     * all relative paths passed into the AssetLoader will start from.
     * @param theMainClass The main class.
     */
    public AssetLoader(Class theMainClass) {
        mainClass = theMainClass;
    }
    
    /**
     * Load a BufferedImage from a relative path into an AssetBank.
     * @param loc The relative path of where the image being loaded is located
     * @param key The key that will be used in the AssetBank
     * @param intoBank The AssetBank object the Image will be loaded into
     * @throws KeyAlreadyUsedException If the given AssetBank already has an
     *         entry using the given Key, a KeyAlreadyUsedException is thrown.
     * @throws IOException If there is an error loading the image, an IOException
     *         is thrown.
     */
    public void loadBufferedImage(String loc, String key, AssetBank intoBank) 
            throws IOException, KeyAlreadyUsedException {
        // make sure the key isn't already used in the bank
        if (intoBank.getImageHashMap().get(key) != null) {
            throw new KeyAlreadyUsedException("The key " + key + " already used"
                    + " by " + intoBank.toString());
        }
        
        BufferedImage img = ImageIO.read(mainClass.getResource(loc));  
        intoBank.getImageHashMap().put(key, img);
        System.out.println("Loaded " + loc + " with key " + key); // report it
    }
}
