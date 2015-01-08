/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.gui;

/**
 * Specifies what happens when a GuiButton is pressed.
 * @author Jonathon
 */
public interface GuiButtonAction {
    /**
     * Called when the GuiButton that this GuiButtonAction is added to
     * is pressed.
     */
    public void onButtonPress();
}
