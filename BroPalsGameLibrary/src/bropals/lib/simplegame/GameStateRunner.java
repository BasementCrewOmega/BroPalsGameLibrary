/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame;

import bropals.lib.simplegame.state.GameState;
import bropals.lib.simplegame.util.AssetBank;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

/**
 * Continuously runs the update and render loop of a GameState object.
 * @author Kevin Prehn
 */
public class GameStateRunner implements KeyListener, MouseListener {
    
    private AssetBank assetBank;
    private GameState currentState;
    private GameWindow currentWindow;
    private long startTime, diff;
    private int millisBetweenFrames;
    
    /**
     * Creates a GameStateRunner with a GameWindow.
     * @param window The window that will be drawn to for this GameStateRunner
     */
    public GameStateRunner(GameWindow window) {
        currentState = null;
        startTime = 0;
        millisBetweenFrames = 40;
        currentWindow = window;
        currentWindow.getCanvas().addKeyListener(this);
        currentWindow.getCanvas().addMouseListener(this);
        assetBank = new AssetBank();
    }
    
    /**
     * Creates a GameStateRunner with an initial GameState.
     * @param window The window that will be draw to for this GameStateRunner.
     * @param initialState The initial state in this GameStateRunner.
     */
    public GameStateRunner(GameWindow window, GameState initialState) {
        this(window);
        setState(initialState);
    }
    
    /**
     * Set how  many frames per second this GameStateRunner runs at.
     * @param fps How many frames per second this GameStateRunner will run at.
     */
    public void setFps(int fps) {
        millisBetweenFrames = (int)(1000.0/(double)fps);
    }
    
    /**
     * Sets the given GameState tot be the current state. The state
     * that was there before is removed after onExit() is called in it.
     * onEnter is called in the new GameState.
     * @param state The new GameState
     */
    public void setState(GameState state) {
        if (currentState != null)
            currentState.onExit();
        
        currentState = state;
        state.setWindow(currentWindow);
        state.setAssetBank(assetBank);
        state.setGameStateRunner(this);
        state.onEnter();
    }
    
    /**
     * So we can extend and change how we render in other GameStates
     */
    protected void renderState(GameState state) {
        BufferStrategy bs = currentWindow.getCanvas().getBufferStrategy();
        Graphics g = bs.getDrawGraphics();
        state.render((Object)g);
        bs.show();
    }
    
    /**
     * Endlessly loops the current GameState until something goes wrong.
     * 
     */
    public void loop() {
        boolean running = true;
        while(running) {
            if (currentState == null || currentWindow == null)
                running = false;
            
            startTime = System.currentTimeMillis();
            GameState runState = currentState; // in case the state is changed
                                               // in the middle of the loop
            runState.update();
            renderState(runState);
            diff = System.currentTimeMillis() - startTime;
            if (diff < millisBetweenFrames) {
                try {
                    Thread.sleep(millisBetweenFrames - diff);
                } catch(Exception e) {}
            }
        }
    }

    
    // applying a level of indirection with the event methods...
    
    @Override
    public void keyTyped(KeyEvent e) {
        if (currentState != null)
            currentState.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (currentState != null)
            currentState.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
         if (currentState != null)
            currentState.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (currentState != null)
            currentState.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (currentState != null)
            currentState.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (currentState != null)
            currentState.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (currentState != null)
            currentState.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (currentState != null)
            currentState.mouseExited(e);
    }
    
    public AssetBank getAssetBank() {
        return assetBank;
    }
}
