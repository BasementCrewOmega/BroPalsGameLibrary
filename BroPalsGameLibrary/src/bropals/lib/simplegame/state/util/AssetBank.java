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
    
    /**
     * Get the HashMap containing SoundEffects
     * @return a HashMap containing SoundEffects
     */
    public HashMap<String, SoundEffect> getSoundEffectHashMap() {
        return soundEffectMap;
    }
    
    /**
     * Gets the image with the specified key.
     * @param key the key of the image
     * @return the image with that key.
     */
    public BufferedImage getImage(String key) {
        return imageMap.get(key);
    }
    
    /**
     * Gets the sound effect with the specified key.
     * @param key the key of the sound effect
     * @return the sond effect with that key.
     */
    public SoundEffect getSoundEffect(String key) {
        return soundEffectMap.get(key);
    }
}
