/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.state.util;

import bropals.lib.simplegame.sound.SoundEffect;
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
    private HashMap<String, SoundEffect> soundEffectMap;
   
    /**
     * Creates a new AssetBank that contains nothing in its HashMaps.
     */
    public AssetBank() {
        imageMap = new HashMap<>();
        soundEffectMap = new HashMap<>();
    }
    
    /**
     * Get the HashMap containing BufferedImages
     * @return a HashMap containing BufferedImages
     */
    public HashMap<String, BufferedImage> getImageHashMap() {
        return imageMap;
    }
    
    public HashMap<String, SoundEffect> getSoundEffectHashMap() {
        return soundEffectMap;
    }
    
    
    public BufferedImage getImage(String key) {
        return imageMap.get(key);
    }
    
    public SoundEffect getSoundEffect(String key) {
        return soundEffectMap.get(key);
    }
}
