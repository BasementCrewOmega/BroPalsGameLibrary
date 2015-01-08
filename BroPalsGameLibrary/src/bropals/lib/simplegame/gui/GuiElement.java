/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
