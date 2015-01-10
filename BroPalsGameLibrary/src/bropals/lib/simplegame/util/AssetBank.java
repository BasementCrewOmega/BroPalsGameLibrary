/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Jonathon Prehn and Kevin Prehn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 **/
package bropals.lib.simplegame.util;

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
