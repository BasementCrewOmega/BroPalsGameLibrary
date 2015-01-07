/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.state.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

/**
 * A class that holds a bunch of HashMaps so there is an easily
 * accessible place to get all our assets.
 * 
 * Feel free to add more HashMaps.
 * 
 * @author Kevin Prehn
 */
public class AssetBank {
    
    private HashMap<String, BufferedImage> imageMap;
   
    /**
     * Creates a new AssetBank that contains nothing in its HashMaps.
     */
    public AssetBank() {
        imageMap = new HashMap<>();
        
    }
    
    /**
     * Get the HashMap containing BufferedImages
     * @return a HashMap containing BufferedImages
     */
    public HashMap<String, BufferedImage> getImageHashMap() {
        return imageMap;
    }
    
}
