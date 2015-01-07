/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.io;

import java.io.DataInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import static bropals.lib.simplegame.logger.ErrorLogger.*;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Reads and writes to files via a binary stream.
 * <p>
 * This is for saving and loading game data to a file via binary storage, but
 * is different than a {@link java.io.DataInputStream DataInputStream} and
 * {@link java.io.DataOutputStream DataOutputStream} because one should extend
 * and override the <code>readFile()</code> and <code>writeFile()</code> methods 
 * to better encapsulate what is being read and written.
 * 
 * @author Jonathon
 * @param <T> the type of object that is being read and written to a binary file.
 */
public abstract class BinaryFileStorage<T> {
    
    private DataInputStream input = null;
    private DataOutputStream output = null;
    
    /**
     * Reads a binary file and interprets it as an object.
     * @param file the file that is being read.
     * @return the object created from the data stored in the file.
     */
    public abstract T readFile(File file);
    
    /**
     * Writes the object to the given binary file. Whether or not this function
     * overwrites the file is dependent on the implementation of it.
     * @param object the object to write.
     * @param file the binary file to write it to.
     */
    public abstract void writeFile(T object, File file);
    
    /**
     * Opens binary input with the specified file so that this object can
     * use its read functions.
     * @param file the file to open the input to.
     */
    protected void openInput(File file) {
        try {
            input = new DataInputStream(
                    Files.newInputStream(file.toPath(), 
                    StandardOpenOption.READ)
            );
        } catch(Exception e) {
            println("Unable to open binary input steam with " + file + ": " + e);
        }
    }
    
    /**
     * Opens binary output with the specified file so that this object can
     * use its write functions.
     * @param file the file to open the output to.
     * @param append whether or not the file should be appended to.
     */
    protected void openOutput(File file, boolean append) {
        try {
            output = new DataOutputStream(
                    Files.newOutputStream(file.toPath(),
                            StandardOpenOption.WRITE,
                            append ? StandardOpenOption.APPEND : StandardOpenOption.CREATE_NEW )
                    );
        } catch(Exception e) {
            println("Unable to open binary output steam with " + file + ": " + e);
        }
    }
    
    /**
     * Closes the binary input if it is open.
     */
    protected void closeInput() {
        if (input!=null) {
            try {
                input.close();
                input = null;
            } catch(IOException e) {
                println("Unable to close binary input stream: " + e);
            }
        }
    }
    
    /**
     * Flushes and closes the binary output if it is open.
     */
    protected void closeOutput() {
        if (output!=null) {
            try {
                output.flush();
                output.close();
                output = null;
            } catch(IOException e) {
                println("Unable to flush and close binary output stream: " + e);
            }
        }
    }
    
    /**
     * Reads the next byte from the binary stream.
     * @return the read byte.
     */
    public byte readByte() {
        if (input==null) {
            println("Open the binary input stream before using any read functions.");
            return 0;
        }
        try {
            return input.readByte();
        } catch(IOException e) {
            println("Unable to read byte from current input stream: " + e);
            return 0;
        }
    }
}
