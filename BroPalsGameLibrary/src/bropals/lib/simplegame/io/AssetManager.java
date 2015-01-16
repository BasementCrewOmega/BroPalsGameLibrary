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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package bropals.lib.simplegame.io;

import bropals.lib.simplegame.logger.ErrorLogger;
import bropals.lib.simplegame.sound.SoundEffect;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Loads and keeps track of assets. Contans the SoundEffectLoader and the
 * BufferedImageLoader by default.
 * @author Jonathon
 */
public class AssetManager {
    
    private final File root;
    private final HashMap<Class, AssetLoader> loaders = new HashMap<>();

    /**
     * Make an AssetManager for the given root directory. This root directory is
     * where all locations will be made relative to.
     *
     * @param root the direction to use when looking for assets.
     */
    public AssetManager(File root) {
        this.root = root;
        addAssetLoader(new SoundEffectLoader(), SoundEffect.class);
        addAssetLoader(new BufferedImageLoader(), BufferedImage.class);
    }

    /**
     * Makes an AssetManager whose root is the default relative directory.
     */
    public AssetManager() {
        this(new File(""));
    }
    
    /**
     * Loads an image into this AssetManager.
     * @param loc the relative location of the image
     * @param key the key the image is stored as
     */
    public void loadImage(String loc, String key) {
        loadAsset(loc, key, BufferedImage.class);
    }
    
    /**
     * Identical to calling 
     * <code>loadAssetsInDirectories(loc, SoundEffect.class, true)</code>.
     * 
     * @param loc the relative location of the directory to load
     * @param recursive whether or not subdirectories should be loaded
     * @see bropals.lib.simplegame.io.AssetManager#loadAssetsInDirectories(java.lang.String, java.lang.Class, boolean) 
     */
    public void loadSoundEffectsInDirectories(String loc, boolean recursive) {
        loadAssetsInDirectories(loc, SoundEffect.class, true);
    }
    
    /**
     * Identical to calling 
     * <code>loadAssetsInDirectories(loc, BufferedImage.class, true)</code>.
     * 
     * @param loc the relative location of the directory to load
     * @param recursive whether or not subdirectories should be loaded
     * @see bropals.lib.simplegame.io.AssetManager#loadAssetsInDirectories(java.lang.String, java.lang.Class, boolean) 
     */
    public void loadImagesInDirectories(String loc, boolean recursive) {
        loadAssetsInDirectories(loc, BufferedImage.class, true);
    }
    
    /**
     * Loads a sound effect into this AssetManager.
     * @param loc the relative location of the sound effect
     * @param key the key the sound effect is stored as
     */
    public void loadSoundEffect(String loc, String key) {
        loadAsset(loc, key, SoundEffect.class);
    }
    
    /**
     * Gets a loaded image.
     * @param key the key of the loaded image
     * @return the loaded image.
     */
    public BufferedImage getImage(String key) {
        return getAsset(key, BufferedImage.class);
    }
    
    /**
     * Gets a loaded sound effect.
     * @param key the key of the loaded sound effect
     * @return the loaded sound effect.
     */
    public SoundEffect getSoundEffect(String key) {
        return getAsset(key, SoundEffect.class);
    }
    
    /**
     * Gets a loaded asset.
     * @param <T> the type of the loaded asset
     * @param key the key the asset is stored as
     * @param assetType the type of the asset
     * @return the loaded asset, or <code>null</code> if there is no asset
     * with the specified key.
     */
    public <T> T getAsset(String key, Class<T> assetType) {
        return (T) loaders.get(assetType).getAsset(key);
    }
        
    /**
     * Loads an asset and stores it as the given key.
     * @param <T> the type of the asset that is being loaded
     * @param loc the relative location of the asset that is being loaded
     * @param key the key to store the asset as
     * @param assetType the type of the asset that is being loaded
     */
    public <T> void loadAsset(String loc, String key, Class<T> assetType) {
        loaders.get(assetType).loadAsset(key, getFile(loc));
    }
    
    /**
     * Removes an asset from memory.
     * @param <T> the type of the asset to unload
     * @param key the key of the asset to unload
     * @param assetType the type of the asset to unload
     */
    public <T> void unloadAsset(String key, Class<T> assetType) {
        loaders.get(assetType).unload(key);
    }
    
    /**
     * Loads one type of asset in a directory and (optionally) all its subdirectories. 
     * The key that each asset is given will be its file name without the extension.
     * <p>
     * All files found in the given directory and its subdirectories are expected
     * to be the same asset type.
     * @param <T> the type of the asset that is being loaded
     * @param loc the relative location of the directory to load
     * @param assetType the type of the asset that is being loaded
     * @param recursive whether or not this should load the sub directories as well
     */
    public <T> void loadAssetsInDirectories(String loc, Class<T> assetType, 
            boolean recursive) {
        File directory = getFile(loc);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File f : files) {
                String name = extractNameWithoutExtension(f);
                String path = loc + System.getProperty("file.separator") + f.getName();
                if (f.isFile()) {
                    loadAsset(path, name, assetType);
                } else if (f.isDirectory() && recursive) {
                    loadAssetsInDirectories(path, assetType, true);
                }
            }
        }
    }
    
    /**
     * Adds an AssetLoader to this AssetManager.
     * @param <T> the type of assets the AssetLoader loads
     * @param loader the AssetLoader to add
     * @param type the type of assets the AssetLoader loads.
     */
    public <T> void addAssetLoader(AssetLoader<T> loader, Class<T> type) {
        loaders.put(type, loader);
    }
    
    private String extractNameWithoutExtension(File file) {
        return file.getName().split(Pattern.quote("."))[0];
    }

    private String extractExtension(File file) {
        try {
            return file.getName().split(Pattern.quote("."))[1];
        } catch (Exception e) {
            return "NO_EXTENSION";
        }
    }
    
    /**
     * Checks to see if this AssetManager as an AssetLoader for this type
     * of asset.
     * @param assetType the type of asset the asset loader loads.
     * @return <code>true</code> if this AssetManager has an AssetLoader for
     * the given asset type, <code>false</code> otherwise.
     */
    public boolean hasAssetLoader(Class assetType) {
        return loaders.get(assetType)!=null;
    }
    
    /**
     * Gets the asset loader that loads the specified type.
     * @param <T> the type of asset loader to get
     * @param assetType the type of asset the loader can load
     * @return the asset requested loader
     */
    public <T> AssetLoader<T> getAssetLoader(Class<T> assetType) {
        return loaders.get(assetType);
    }
    
    /**
     * Gets a file from a path relative to this AssetManager's root.
     * @param loc the relative location
     * @return the file at that location
     */
    private File getFile(String loc) {
        return new File(root.getAbsolutePath() + System.getProperty("file.separator") + loc);
    }
    
    /**
     * Loads the source from a file.
     * @param loc the relative location of the file.
     * @return the source from the file.
     */
    public String loadSource(String loc) {
        try {
            return loadSource(new FileInputStream(getFile(loc)));
        } catch (FileNotFoundException ex) {
            ErrorLogger.println("Could not find file: " + loc);
            return null;
        }
    }
    
    /**
     * Loads source from an input stream.
     * @param inputStream the input stream where the source is located
     * @return the source
     */
    private String loadSource(InputStream inputStream) {
        try {
            String source = "";
            InputStreamReader rdr = new InputStreamReader(inputStream);
            int c;
            while ( ( c = rdr.read() ) != -1) {
                source += (char)c;
            }
            rdr.close();
            return source;
        } catch(IOException ioe) {
            ErrorLogger.println("Could not load source from input stream");
            return null;
        }
    }
    
    /**
     * Loads a source file at a URL.
     * @param url the URL of where the source is.
     * @return the loaded source
\     */
    public String loadSourceFromURL(URL url) {
        try {
            return loadSource(url.openStream());
        } catch (IOException ex) {
            ErrorLogger.println("Could not load source from URL");
            return null;
        }
    }
}
