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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 **/
package bropals.lib.simplegame.state;

import bropals.lib.simplegame.GameStateRunner;
import bropals.lib.simplegame.GameWindow;
import bropals.lib.simplegame.util.AssetBank;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

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
 that is running this GameState.
     * @return a reference to the GameWindow used by the GameStateRunner 
 that is running this GameState.
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
     * Gets the image with the specified key from this GameState's AssetBank.
     * This is the same thing as doing 
     * <code>getAssetBank().getImage(key)</code>
     * @param key the image key
     * @return the image with the given key if it exists.
     */
    public BufferedImage getImage(String key) {
        return getAssetBank().getImage(key);
    }
    
    /**
     * Convenience method for playing a sound effect from this GameState's
     * AssetBank. This is the same as doing 
     * <code>getAssetBank().getSoundEffect(key).play()</code>
     * @param key 
     */
    public void playSoundEffect(String key) {
        getAssetBank().getSoundEffect(key).play();
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
