/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame;

import bropals.lib.simplegame.state.GameState;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 * Continuously runs the update and render loop of a GameState object.
 * @author Kevin Prehn
 */
public class GameStateRunner {
    
    private GameState currentState;
    private GameWindow currentWindow;
    private long startTime, diff;
    private int millisBetweenFrames;
    
    public GameStateRunner() {
        currentState = null;
        currentWindow = null;
        startTime = 0;
        millisBetweenFrames = 40;
    }
    
    public GameStateRunner(GameWindow window) {
        this();
        currentWindow = window;
    }
    
    public GameStateRunner(GameWindow window, GameState initialState) {
        this(window);
        setState(initialState);
    }
    
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
        state.onEnter();
    }
    
    /**
     * So we can extend and change how we render in other GameStates
     */
    protected void renderState(GameState state) {
        BufferStrategy bs = currentWindow.getCanvas().getBufferStrategy();
        Graphics g = bs.getDrawGraphics();
        state.render(g);
        bs.show();
    }
    
    /**
     * Endlessly loops until something goes wrong.
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
            if (millisBetweenFrames - diff > 0) {
                try {
                    Thread.sleep(millisBetweenFrames - diff);
                } catch(Exception e) {}
            }
        }
    }
}
