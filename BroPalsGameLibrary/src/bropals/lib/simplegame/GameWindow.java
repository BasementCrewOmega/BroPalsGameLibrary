/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * A window that holds the Canvas
 * @author Kevin Prehn
 */
public class GameWindow extends JFrame {
    
    private Canvas containedCanvas;
    
    /**
     * Creates an invisible GameWindow that is the given size and contains
     * the given Canvas object. The Canvas object is resized, inserted, 
     * and then creates a BufferStrategy
     * @param width The window's width in pixels
     * @param height The window's height in pixels
     * @param canvas The canvas that the GameWindow will contain
     */
    public GameWindow(String title, int width, int height, Canvas canvas) {
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int)(screenSize.getWidth()/2)-(width/2), // center the window
                 (int)(screenSize.getHeight()/2)-(height/2));
        containedCanvas = canvas;
        containedCanvas.setSize(width, height);
        add(containedCanvas);
        containedCanvas.createBufferStrategy(2);
    }
    
    public Canvas getCanvas() {
        return containedCanvas;
    }
}
