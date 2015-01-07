/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.test;

import bropals.lib.simplegame.*;
import bropals.lib.simplegame.io.PropertiesReader;
import bropals.lib.simplegame.state.util.AssetBank;
import bropals.lib.simplegame.state.util.AssetLoader;
import java.io.File;

/**
 *
 * @author Owner
 */
public class TesterClass {
    
    public static void main(String[] args) {
        /* Testing sound */
        AssetLoader al = new AssetLoader();
        AssetBank bank = new AssetBank();
        try {
            al.loadSoundEffect("test_files/sound.wav", "testSound", bank);
        } catch(Exception e) {
            System.out.println(e);
        }
        bank.getSoundEffect("testSound").play();
        bank.getSoundEffect("testSound").play();
        
        
        // make a window
        GameWindow window = new GameWindow("Super cool", 500, 350);
        
        // make a GameStateRunner that is using the newly made window with
        // an inital GameState.
        GameStateRunner runner = new GameStateRunner(window, new TestState());
        
        // begin looping the game!
        runner.loop();
    }
}
