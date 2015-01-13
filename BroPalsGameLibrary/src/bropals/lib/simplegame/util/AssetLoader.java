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
package bropals.lib.simplegame.util;

import bropals.lib.simplegame.logger.ErrorLogger;
import bropals.lib.simplegame.logger.InfoLogger;
import bropals.lib.simplegame.sound.SoundEffect;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Pattern;
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
     * Make an AssetLoader for the given root directory. This root directory is
     * where all locations will be made relative to.
     *
     * @param root the direction to use when looking for assets.
     */
    public AssetLoader(File root) {
        this.root = root;
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
     * Loads all images in a directory into the given AssetBank, giving each one
     * their file name (without the extension) as its key. Does nothing if the
     * supplied path is not a directory.
     *
     * @param dir the directory that will have its contents loaded.
     * @param bank the bank to load the images into.
     * @param readSubDirectories if true, this will read all of the given
     * directory's subdirectories, their subdirectories and so on.
     */
    public void loadBufferedImagesInDirectory(String dir, AssetBank bank, boolean readSubDirectories) {
        File directory = getFile(dir);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File f : files) {
                String name = extractNameWithoutExtension(f);
                String path = dir + System.getProperty("file.separator") + f.getName();
                if (f.isFile()) {
                    try {
                        loadBufferedImage(path,
                                name,
                                bank);
                    } catch (IOException ioe) {
                        ErrorLogger.println("Error while loading image \""
                                + path + "\" " + ioe);
                    } catch (KeyAlreadyUsedException ex) {
                        ErrorLogger.println("There is already an image named \""
                                + name + "\" in this AssetBank.");
                    }
                } else if (f.isDirectory() && readSubDirectories) {
                    loadBufferedImagesInDirectory(path, bank, true);
                }
            }
        }
    }

    /**
     * Loads all sound effects in a directory into the given AssetBank, giving
     * each one their file name (without the extension) as its key. Does nothing
     * if the supplied path is not a directory.
     *
     * @param dir the directory that will have its contents loaded
     * @param bank the bank to load the images into.
     * @param readSubDirectories if true, this will read all of the given
     * directory's subdirectories, their subdirectories and so on.
     */
    public void loadSoundEffectsInDirectory(String dir, AssetBank bank, boolean readSubDirectories) {
        File directory = getFile(dir);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File f : files) {
                String name = extractNameWithoutExtension(f);
                String path = dir + System.getProperty("file.separator") + f.getName();
                if (f.isFile()) {
                    try {
                        loadSoundEffect(path,
                                name,
                                bank);
                    } catch (IOException ioe) {
                        ErrorLogger.println("Error while loading sound effect \""
                                + path + "\" " + ioe);
                    } catch (KeyAlreadyUsedException ex) {
                        ErrorLogger.println("There is already a sound effect named \""
                                + name + "\" in this AssetBank.");
                    }
                } else if (f.isDirectory() && readSubDirectories) {
                    loadSoundEffectsInDirectory(path, bank, true);
                }
            }
        }
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
     *
     * @param loc the relative path to the source file
     * @return the source contained in the file.
     * @throws java.io.IOException if there is an error in reading the file
     */
    public String loadSource(String loc) throws IOException {
        //Arbitrary string builder initial capacity
        StringBuilder sourceBuilder = new StringBuilder(500);
        BufferedReader rdr = new BufferedReader(new FileReader(getFile(loc)));
        int cur;
        while ((cur = rdr.read()) != -1) {
            sourceBuilder.append((char) cur);
        }
        rdr.close();
        return sourceBuilder.toString();
    }

    /**
     * Loads all the text from a URL. This method keeps all white space
     * including new lines.
     *
     * @param url the URL to load the source from
     * @return the source contained in the file.
     * @throws java.io.IOException if there is an error in reading the URL
     */
    public static String loadSourceFromURL(URL url) throws IOException {
        //Arbitrary string builder initial capacity
        StringBuilder sourceBuilder = new StringBuilder(500);
        BufferedReader rdr = new BufferedReader(new InputStreamReader(url.openStream()));
        int cur;
        while ((cur = rdr.read()) != -1) {
            sourceBuilder.append((char) cur);
        }
        rdr.close();
        return sourceBuilder.toString();
    }

    /**
     * Loads all of the lines in a text file at the specified URL.
     *
     * @param url the URL to load the lines from
     * @return the lines in the file organized in an ArrayList.
     * @throws java.io.IOException if there is an error in reading the URL
     */
    public static ArrayList<String> loadLinesFromURL(URL url) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader rdr = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        while ((line = rdr.readLine()) != null) {
            lines.add(line);
        }
        rdr.close();
        return lines;
    }

    /**
     * Loads tabular data. The first row is expected to be the one that defines
     * the column names. If the type of data in a column is not one of the eight
     * primitives or a <code>String</code>, then it is expected to have a
     * default constructor for the function to build it out of.
     * <p>
     * The format of the data is space separated columns, and line separated
     * rows.
     *
     * @param loc the relative path to the file that holds the tabular data
     * @param types the data types
     * @param skipLines the lines to skip
     * @return the data put into a data table.
     * @throws java.io.IOException if an error occurs with reading the file
     * @throws java.lang.IllegalStateException if the data is incorrectly
     * formatted
     */
    public DataTable loadDataTable(String loc, Class[] types, int skipLines) throws IOException, IllegalStateException {
        BufferedReader rdr = new BufferedReader(new FileReader(getFile(loc)));
        return loadDataTable(rdr, types, skipLines);
    }

    /**
     * Loads tabular data. The first row is expected to be the one that defines
     * the column names. If the type of data in a column is not one of the eight
     * primitives or a <code>String</code>, then it is expected to have a
     * default constructor for the function to build it out of.
     * <p>
     * The format of the data is space separated columns, and line separated
     * rows.
     *
     * @param url the URL to load the tabular data from.
     * @param types the data types
     * @param skipLines the lines to skip
     * @return the data put into a data table.
     * @throws java.io.IOException if an error occurs with reading the file
     * @throws java.lang.IllegalStateException if the data is incorrectly
     * formatted
     */
    public static DataTable loadDataTableFromURL(URL url, Class[] types, int skipLines) throws IOException, IllegalStateException {
        BufferedReader rdr = new BufferedReader(new InputStreamReader(url.openStream()));
        return loadDataTable(rdr, types, skipLines);
    }
    
    /**
     * Generified load data table function
     * @param rdr
     * @param types
     * @param skipLines
     * @return
     * @throws IOException
     * @throws IllegalStateException 
     */
    private static DataTable loadDataTable(BufferedReader rdr, Class[] types, int skipLines) throws IOException, IllegalStateException {
        ArrayList<Object[]> rows = new ArrayList<>();
        String line;
        String spaceLiteral = Pattern.quote(" ");
        String newLineLiteral = Pattern.quote(System.getProperty("line.separator"));
        /* Skip some lines */
        for (int i = 0; i < skipLines; i++) {
            rdr.readLine();
        }
        line = rdr.readLine(); //Read the column
        String[] columnNames = line.split(spaceLiteral); //Now you have column names
        //Reality checking
        if (columnNames.length != types.length) {
            throw new IllegalStateException("The column length "
                    + "of the types array needs to match "
                    + "the number of columns in the data table!");
        }
        //Now read the data
        int row = 1;
        Object[] rowData;
        String[] split;
        Class curType;
        
        while ((line = rdr.readLine()) != null) {
            rowData = new Object[columnNames.length];
            split = simpleSplit(line, columnNames.length);
            if (split.length != columnNames.length) {
                throw new IllegalStateException("The column length of row "
                        + row + " does not match the column header length of "
                        + columnNames.length + "! (it is " + split.length + " columns)");
            }
            //Interpret the file
            for (int i = 0; i < columnNames.length; i++) {
                curType = types[i];
                try {
                    if (curType == Integer.class) {
                        rowData[i] = Integer.parseInt(split[i]);
                    } else if (curType == Long.class) {
                        rowData[i] = Long.parseLong(split[i]);
                    } else if (curType == Boolean.class) {
                        if (split[i].equals("true")) {
                            rowData[i] = true;
                        } else if (split[i].equals("false")) {
                            rowData[i] = false;
                        } else {
                            throw new NumberFormatException("Boolean is not true or false");
                        }
                    } else if (curType == Float.class) {
                        rowData[i] = Float.parseFloat(split[i]);
                    } else if (curType == Byte.class) {
                        rowData[i] = Byte.parseByte(split[i]);
                    } else if (curType == Short.class) {
                        rowData[i] = Short.parseShort(split[i]);
                    } else if (curType == Character.class) {
                        rowData[i] = split[i].charAt(0);
                    } else if (curType == Double.class) {
                        rowData[i] = Double.parseDouble(split[i]);
                    } else if (curType == String.class) {
                        rowData[i] = split[i];
                    } else {
                        try {
                            Class cl = Class.forName(split[i]);
                            rowData[i] = cl.newInstance();
                        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ie) {
                            throw new IllegalStateException("Invalid"
                                    + " data at row " + row + " column " + i + ", "
                                    + ": " + ie);
                        }
                    }
                } catch (NumberFormatException nfe) {
                    throw new IllegalStateException("Invalid"
                            + " data at row " + row + " column " + i + ", "
                            + ": " + nfe);
                }
            }
            rows.add(rowData);
            row++;
        }
        rdr.close();
        return new DataTable(types, columnNames, rows);
    }
    
    private static String[] simpleSplit(String str, int amount) {
        String[] split = new String[amount];
        char[] c = str.toCharArray();
        String buf = "";
        int splitIndex = 0;
        boolean makeNew = false;
        for (int i=0; i<c.length; i++) {
            if (c[i] == ' ') {
                makeNew = true;
            } else {
                if (makeNew) {
                    split[splitIndex] = buf;
                    buf = "";
                    splitIndex++;
                    makeNew = false;
                }
                buf += c[i];
            }
        }
        split[splitIndex] = buf;
        return split;
    }
    
    private File getFile(String loc) {
        return new File(root.getAbsolutePath() + System.getProperty("file.separator") + loc);
    }
}
