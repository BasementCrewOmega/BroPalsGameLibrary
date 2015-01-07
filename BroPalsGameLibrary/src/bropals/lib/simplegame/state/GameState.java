/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.state;

import bropals.lib.simplegame.GameStateRunner;
import bropals.lib.simplegame.GameWindow;
import bropals.lib.simplegame.state.util.AssetBank;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

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
     * A reference to the Window being used by the GameStateRunner running
     * this game state.
     */
    private GameWindow window;
    /**
     * A reference to the AssetBank in the GameStateRunner running
     * this game state.
     */
    private AssetBank assetBank;
    
    /**
     * A reference to the GameStateRunner that is running this GameState.
     */
    private GameStateRunner runner;
    
    /**
     * For internal use.
     */
    public void setWindow(GameWindow window) {
        this.window = window;
    }
    
    /**
     * Returns a reference to the GameWindow used by the GameStateRunner 
     * that is running this GameState.
     * @return a reference to the GameWindow used by the GameStateRunner 
     * that is running this GameState.
     */
    public GameWindow getWindow() {
        return window;
    }
    
    /**
     * For internal use.
     */
    public void setAssetBank(AssetBank bank) {
        assetBank = bank;
    }
    
    /**
     * Returns a reference to the AssetBank used by the GameStateRunner 
     * that is running this GameState.
     * @return a reference to the AssetBank used by the GameStateRunner 
     * that is running this GameState.
     */
    public AssetBank getAssetBank() {
        return assetBank;
    }

    /**
     * Returns a reference to the GameStateRunner running this game state.
     * @return a reference to the GameStateRunner running this game state.
     */
    public GameStateRunner getGameStateRunner() {
        return runner;
    }

    /**
     * For internal use.
     */
    public void setGameStateRunner(GameStateRunner runner) {
        this.runner = runner;
    }
    
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
