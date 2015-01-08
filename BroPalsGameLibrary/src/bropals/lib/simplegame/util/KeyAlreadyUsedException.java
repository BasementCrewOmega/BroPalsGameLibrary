/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.util;

/**
 * An exception used by AssetLoader
 * @author Kevin Prehn
 */
public class KeyAlreadyUsedException extends Exception {
    
    public KeyAlreadyUsedException(String msg) {
        super(msg);
    }
}
