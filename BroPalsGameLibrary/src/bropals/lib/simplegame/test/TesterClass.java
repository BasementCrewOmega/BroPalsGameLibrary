/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.test;

import bropals.lib.simplegame.*;

/**
 *
 * @author Owner
 */
public class TesterClass {
    
    public static void main(String[] args) {
        // make a window
        GameWindow window = new GameWindow("Super cool", 500, 350);
        
        // make a GameStateRunner that is using the newly made window with
        // an inital GameState.
        GameStateRunner runner = new GameStateRunner(window, new TestState());
        
        // begin looping the game!
        runner.loop();
    }
}
