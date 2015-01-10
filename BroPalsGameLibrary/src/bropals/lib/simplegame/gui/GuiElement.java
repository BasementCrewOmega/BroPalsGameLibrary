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
package bropals.lib.simplegame.gui;

import java.awt.Rectangle;

/**
 * The superclass for all GUI elements. 
 * @author Jonathon
 */
public abstract class GuiElement {
    
    private Rectangle rectangle;
    
    /**
     * Creates a GUI element with the specified size and position.
     * @param x the x position
     * @param y the y position
     * @param w the width
     * @param h the height
     */
    public GuiElement(int x, int y, int w, int h) {
        rectangle = new Rectangle(x, y, w, h);
    }
    
    public int getX() {
        return rectangle.x;
    }
    
    public int getY() {
        return rectangle.y;
    }
    
    public int getWidth() {
        return rectangle.width;
    }
    
    public int getHeight() {
        return rectangle.height;
    }
    
    /**
     * Sends mouse input to this GuiElement. Must call super() if overriden.
     * @param x the x mouse position
     * @param y the y mouse position
     */
    public void mouseInput(int x, int y) {
        if (rectangle.contains(x, y))
            onMousePress();
    }
    
    /**
     * Called when the GUI element is clicked on.
     */
    public void onMousePress() {
    }
    
    /**
     * Sees if the point is inside of this GuiElement
     * @param x the x position of the point
     * @param y the y position of the point
     * @return if the point is inside this GuiElement.
     */
    public boolean contains(int x, int y) {
        return rectangle.contains(x, y);
    }
    
    /**
     * Updates the GUI element. This method does not need to call super()
     * @param mouseX the current x position of the mouse
     * @param mouseY the current y position of the mouse
     */
    public void update(int mouseX, int mouseY) {
    }
    
    /**
     * Renders this GuiElement.
     * @param graphicsObject the graphics object for drawing
     */
    public abstract void render(Object graphicsObject);
}
