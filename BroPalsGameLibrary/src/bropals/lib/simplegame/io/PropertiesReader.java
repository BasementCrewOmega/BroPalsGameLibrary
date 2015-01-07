/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.io;

import bropals.lib.simplegame.logger.ErrorLogger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Reads text files that are in property, value format.
 * The format for these files is
 * <p>
 * <code>
 * property=value<br>
 * property=value<br>
 * ...<br>
 * </code>
 * <p>
 * @author Jonathon
 */
public class PropertiesReader {
    
    private final HashMap<String, Object> propertyValueMap = new HashMap<>();
    
    /**
     * Clears the current property-value data and replaces it with the newly
     * read data from the given file. This function also attempts to
     * store values as integers, floats, or booleans if it is able to.
     * @param file the file to read property-value data from.
     */
    public void readProperties(File file) {
        propertyValueMap.clear();
        try {
            BufferedReader rdr = new BufferedReader(new FileReader(file));
            String property = null;
            String valueString;
            String line;
            
            while ( ( line = rdr.readLine() ) != null ) {
                String[] spl = line.split(Pattern.quote("="));
                property = spl[0];
                valueString = spl[1];
                Object value = null;
                
                try {
                    value = Integer.parseInt(valueString);
                } catch(NumberFormatException nfe) {
                    value = null;
                }
                if (value == null) {
                    try {
                        value = Float.parseFloat(valueString);
                    } catch (NumberFormatException nfe) {
                        value = null;
                    } 
                }
                if (value == null) {
                    if (valueString.equals("true")) {
                        value = true;
                    } else if (valueString.equals("false")) {
                        value = false;
                    }
                }
                if (value == null) {
                    value = valueString;
                }
                propertyValueMap.put(property, value);
            }
            rdr.close();
        } catch(IOException e) {
            ErrorLogger.println("Could not open file to read property-value pairs from: " + e);
        }
    }
    
    /**
     * Clears the current property-value data and replaces it with the newly
     * read data from the given file.
     * @param relativePath the relative path to the file to read property-value data from.
     */
    public void readProperties(String relativePath) {
        readProperties(new File(relativePath));
    } 
    
    /**
     * Gets the value for the property with the given name from the current
     * property data. Returns <code>null</code> if the property key does
     * not exist in the current property-value data.
     * @param <T> the value's type
     * @param property the name of the property
     * @return the value that corresponds to the given property.
     */
    public <T> T getProperty(String property) {
        return (T) propertyValueMap.get(property);
    }
    
    /**
     * Gets the current property-value map.
     * @return the current property-value map.
     */
    public HashMap<String, Object> getPropertyMap() {
        return propertyValueMap;
    }
}
