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
     * Creates a visible GameWindow that is the given size and contains
     * the a Canvas object. The Canvas object is resized, inserted, 
     * and then creates a BufferStrategy
     * @param width The window's width in pixels
     * @param height The window's height in pixels
     */
    public GameWindow(String title, int width, int height) {
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int)(screenSize.getWidth()/2)-(width/2), // center the window
                 (int)(screenSize.getHeight()/2)-(height/2));
        setVisible(true);
        containedCanvas = new Canvas();
        containedCanvas.setPreferredSize(new Dimension(width, height));
        containedCanvas.setSize(width, height);
        add(containedCanvas);
        containedCanvas.createBufferStrategy(2);
        containedCanvas.setFocusable(true);
        containedCanvas.requestFocus();
        containedCanvas.setIgnoreRepaint(true);
    }
            
    public Canvas getCanvas() {
        return containedCanvas;
    }
}
