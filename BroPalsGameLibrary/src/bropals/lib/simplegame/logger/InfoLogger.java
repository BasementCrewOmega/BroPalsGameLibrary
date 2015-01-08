/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Provides a level of indirection to message printing.
 * @author Jonathon
 */
public class InfoLogger {
    
    private static PrintStream info = System.out;
    private static boolean silent = false;
    
    /**
     * Set if you want the InfoLogger to stop printing messages or not.
     * @param silent whether or not to silence the InfoLogger.
     */
    public static void setSilent(boolean silent) {
        InfoLogger.println("InfoLogger is now silent = " + silent);
        InfoLogger.silent = silent;
    }
    
    /**
     * Sets where info messages are printed to.
     * @param err the new info reporting stream.
     */
    public static void setInfo(PrintStream err) {
        InfoLogger.info = err;
    }
    
    /**
     * Gets the print stream currently being used for info reporting.
     * @return the info message stream currently being used.
     */
    public static PrintStream getInfo() {
        return info;
    }
    
    /**
     * Sets the info output to go to a text file.
     * @param file the file location of the info log.
     */
    public static void openInfoLog(File file) {
        try {
            info = new PrintStream(file);
        } catch(IOException e) {
            println("Unable to open info log: " + e);
        }
    }
    
    /**
     * Identical to calling InfoLogger.getInfo().print(str).
     * @param str the string to print to the info stream.
     */
    public static void print(String str) {
        getInfo().print(str);
        getInfo().flush();
    }
    
    /**
     * Identical to calling InfoLogger.getInfo().println(str).
     * @param str the string to print to the info stream.
     */
    public static void println(String str) {
        getInfo().println(str);
        getInfo().flush();
    }
    
    /**
     * Identical to calling InfoLogger.getInfo().println(str).
     * @param obj the object to print to the info stream.
     */
    public static void println(Object obj) {
        println(obj.toString());
    }
}
