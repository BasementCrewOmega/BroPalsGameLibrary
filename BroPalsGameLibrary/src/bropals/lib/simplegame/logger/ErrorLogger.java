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
 * Provides a level of indirection to error printing. Prints to System.err by
 * default but one can change where it prints to using ErrorLogger's methods.
 * @author Jonathon
 */
public class ErrorLogger {
    
    private static PrintStream err = System.err;
    private static boolean silent = false;
    
     /**
     * Set if you want the ErrorLogger to stop printing messages or not.
     * @param silent whether or not to silence the InfoLogger.
     */
    public static void setSilent(boolean silent) {
        ErrorLogger.println("ErrorLogger is now silent = " + silent);
        ErrorLogger.silent = silent;
    }
    
    /**
     * Sets where the errors are printed to.
     * @param err the new error reporting stream.
     */
    public static void setErr(PrintStream err) {
        ErrorLogger.err = err;
    }
    
    /**
     * Gets the print stream currently being used for error reporting.
     * @return the error stream currently being used.
     */
    public static PrintStream getErr() {
        return err;
    }
    
    /**
     * Sets the error output to go to a file that specifies a text file.
     * @param file the file location of the error log.
     */
    public static void openErrorLog(File file) {
        try {
            err = new PrintStream(file);
        } catch(IOException e) {
            println("Unable to open error log: " + e);
        }
    }
    
    /**
     * Identical to calling ErrorLogger.getErr().print(str).
     * @param str the string to print to the error stream.
     */
    public static void print(String str) {
        if (!silent) {
            getErr().print(str);
            getErr().flush();
        }
    }
    
    /**
     * Identical to calling ErrorLogger.getErr().println(str).
     * @param str the string to print to the error stream.
     */
    public static void println(String str) {
        if (!silent) {
            getErr().println(str);
            getErr().flush();
        }
    }
}
