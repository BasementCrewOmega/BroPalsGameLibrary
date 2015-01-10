/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.gui;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * Groups together GuiGroups. Each GuiGroup will have a name that it is
 * referenced by, given to it when the GuiGroup is added to this Gui.
 * @author Jonathon
 */
public class Gui {
    
    private HashMap<String, GuiGroup> groups = new HashMap<>();
    private Collection<GuiGroup> groupSet;
    
    /**
     * Adds a GuiGroup to this Gui.
     * @param key the key to give to the group
     * @param group the group to add to the Gui
     */
    public void addGroup(String key, GuiGroup group) {
        groups.put(key, group);
        groupSet = groups.values();
    }
    
    /**
     * Removes the GuiGroup with the specified key.
     * @param key the key of the group to remove
     */
    public void removeGroup(String key) {
        groups.remove(key);
        groupSet = groups.values();
    }
    
    /**
     * Sets the enabled state of the specified GuiGroup
     * @param key the key of the group to set the enabled state of
     * @param enabled the enabled state of the group
     */
    public void setEnabled(String key, boolean enabled) {
        groups.get(key).setEnabled(enabled);
    }
    
    /**
     * Enables the GuiGroup with the specified key.
     * @param key the key of the specified GuiGroup
     */
    public void enable(String key) {
        setEnabled(key, true);
    }
    
    /**
     * Disables the GuiGroup with the specified key.
     * @param key the key of the specified GuiGroup
     */
    public void disable(String key) {
        setEnabled(key, false);
    }
    
    /**
     * Enables one GuiGroup while disabling another.
     * @param on the key of the GuiGroup to enable.
     * @param off the key of the GuiGroup to disable.
     */
    public void swap(String on, String off) {
        enable(on);
        disable(off);
    }
    
    /**
     * Updates all enabled GuiGroups in this Gui
     * @param mouseX the current X mouse position
     * @param mouseY the current Y mouse position
     */
    public void update(int mouseX, int mouseY) {
        for (GuiGroup group : groupSet) {
            group.update(mouseX, mouseY);
        }
    }
    
    /**
     * Draws all enabled GuiGroups in this Gui
     * @param graphicsObject the graphics object to draw the GuiGroups with
     */
    public void render(Object graphicsObject) {
        for (GuiGroup group : groupSet) {
            group.render(graphicsObject);
        }
    }
    
    /**
     * Sends mouse pressed input to all enabled GuiGroups in this Gui
     * @param mouseX the X mouse position
     * @param mouseY the Y mouse position
     */
    public void mouseInput(int mouseX, int mouseY) {
        for (GuiGroup group : groupSet) {
            group.mouseInput(mouseX, mouseY);
        }
    }
}
