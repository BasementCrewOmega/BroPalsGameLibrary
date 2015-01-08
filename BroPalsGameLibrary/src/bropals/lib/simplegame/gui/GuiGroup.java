/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.gui;

import java.util.ArrayList;

/**
 * Makes it easier to group GuiElements together.
 * @author Jonathon
 */
public class GuiGroup {
    
    private ArrayList<GuiElement> group = new ArrayList<GuiElement>();
    
    /**
     * Adds an element to this GuiGroup
     * @param element the element to add
     */
    public void addElement(GuiElement element) {
        group.add(element);
    }
    
    /**
     * Removes an element from this GuiGroup
     * @param element the element to remove
     */
    public void removeElement(GuiElement element) {
        group.remove(element);
    }
    
    /**
     * Updates all elements in this GuiGroup
     * @param mouseX the current X mouse position
     * @param mouseY the current Y mouse position
     */
    public void update(int mouseX, int mouseY) {
        for (GuiElement element : group) {
            element.update(mouseX, mouseY);
        }
    }
    
    /**
     * Draws all elements in this GuiGroup
     * @param graphicsObject the graphics object to draw this GuiGroup with
     */
    public void render(Object graphicsObject) {
        for (GuiElement element : group) {
            element.render(graphicsObject);
        }
    }
    
    /**
     * Sends mouse pressed input to all elements in this GuiGroup
     * @param mouseX the X mouse position
     * @param mouseY the Y mouse position
     */
    public void mouseInput(int mouseX, int mouseY) {
        for (GuiElement element : group) {
            element.mouseInput(mouseX, mouseY);
        }
    }
}
