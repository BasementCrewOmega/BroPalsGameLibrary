/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.util;

import bropals.lib.simplegame.logger.ErrorLogger;
import bropals.lib.simplegame.logger.InfoLogger;
import bropals.lib.simplegame.sound.SoundEffect;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The class to load Assets into an AssetBank.
 *
 * @author Kevin Prehn
 */
public class AssetLoader {

    private File root;

    /**
     * Make an AssetLoader for the given root directory. This root
     * directory is where all locations will be made relative to.
     *
     * @param root the direction to use when looking for assets.
     */
    public AssetLoader(File root) {
        this.root=root;
    }
    
    /**
     * Makes an AssetLoader whose root is the default relative directory.
     */
    public AssetLoader() {
        this(new File(""));
    }
    
    /**
     * Load a BufferedImage from a relative path into an AssetBank.
     *
     * @param loc The relative path of where the image being loaded is located
     * @param key The key that will be used in the AssetBank
     * @param intoBank The AssetBank object the Image will be loaded into
     * @throws KeyAlreadyUsedException If the given AssetBank already has an
     * entry using the given Key, a KeyAlreadyUsedException is thrown.
     * @throws IOException If there is an error loading the image, an
     * IOException is thrown.
     */
    public void loadBufferedImage(String loc, String key, AssetBank intoBank)
            throws IOException, KeyAlreadyUsedException {
        // make sure the key isn't already used in the bank
        if (intoBank.getImageHashMap().get(key) != null) {
            throw new KeyAlreadyUsedException("The image key \"" + key
                    + "\" already used by " + intoBank.toString());
        }
        
        BufferedImage img = ImageIO.read(getFile(loc));
        intoBank.getImageHashMap().put(key, img);
        InfoLogger.println("Loaded image " + loc + " with key " + key); // report it
    }

    /**
     * Load a SoundEffect from a relative path into an AssetBank.
     *
     * @param loc The relative path of where the sound effect being loaded is
     * located
     * @param key The key that will be used in the AssetBank
     * @param intoBank The AssetBank object the sound will be loaded into
     * @throws KeyAlreadyUsedException If the given AssetBank already has an
     * entry using the given Key, a KeyAlreadyUsedException is thrown.
     * @throws IOException If there is an error loading the sound, an
     * IOException is thrown.
     */
    public void loadSoundEffect(String loc, String key, AssetBank intoBank)
            throws IOException, KeyAlreadyUsedException {
        if (intoBank.getSoundEffectHashMap().get(key) != null) {
            throw new KeyAlreadyUsedException("The sound effect key \"" + key
                    + "\" already used by " + intoBank.toString());
        }
        try {
            AudioInputStream ais
                    = AudioSystem.getAudioInputStream(getFile(loc));

            AudioFormat format = ais.getFormat();

            /* 
             Convert the format if necessary

             Credit to "Killer Game Programming in Java" by Andrew Davidson 
             for the audio format conversion code
             */
            if ((format.getEncoding() == AudioFormat.Encoding.ULAW)
                    || (format.getEncoding() == AudioFormat.Encoding.ALAW)) {
                AudioFormat newFormat = new AudioFormat(
                        AudioFormat.Encoding.PCM_SIGNED,
                        format.getSampleRate(),
                        format.getSampleSizeInBits() * 2,
                        format.getChannels(),
                        format.getFrameSize() * 2,
                        format.getFrameRate(),
                        true
                );
                ais = AudioSystem.getAudioInputStream(newFormat, ais);
                format = newFormat;
            }
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                throw new UnsupportedAudioFileException();
            }
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
            ais.close();
            SoundEffect sfx = new SoundEffect(clip);
            intoBank.getSoundEffectHashMap().put(key, sfx);
            InfoLogger.println("Loaded sound " + loc + " with key " + key); // report it
        } catch (UnsupportedAudioFileException uafe) {
            ErrorLogger.println("Unsupported audio format; file loc is " + loc + "; exception is " + uafe);
        } catch (LineUnavailableException lue) {
            ErrorLogger.println("The requested audio line is unable to be "
                    + "opened: another application might be using it?");
        }
    }
    
    /**
     * Loads all the text from a file. This method keeps all white space
     * including new lines.
     * @param loc the relative path to the source file
     * @return the source contained in the file.
     * @throws java.io.IOException if there is an error in reading the file
     */
    public String loadSource(String loc) throws IOException {
        //Arbitrary string builder initial capacity
        StringBuilder sourceBuilder = new StringBuilder(500);
        BufferedReader rdr = new BufferedReader(new FileReader(getFile(loc)));
        int cur;
        while ( (cur = rdr.read()) != -1 ) {
            sourceBuilder.append((char)cur);
        }
        rdr.close();
        return sourceBuilder.toString();
    }
    
    private File getFile(String loc) {
        return new File(root.getAbsolutePath() + System.getProperty("file.separator") + loc);
    }
}
