/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.state;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Graphics2D;

/**
 * A class that is meant to hold code for a state in the game.
 * Extend the class and implement all the code for that state of
 * the game.
 * 
 * To run the game state, 
 * @author Kevin Prehn
 */
public abstract class GameState implements KeyListener, MouseListener {
    
    /**
     * Update this game state.
     */
    public abstract void update();
    /**
     * Render the game state to an Object.
     * @param graphicsObj The graphics object being used to draw
     */
    public abstract void render(Object graphicsObj);
    /**
     * The method called when this game state is first set.
     */
    public abstract void onEnter();
    /**
     * The method called when this game state is being swapped
     * with a different game state.
     */
    public abstract void onExit();
}
