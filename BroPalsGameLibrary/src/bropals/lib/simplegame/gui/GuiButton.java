/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Represents a GUI button.
 * @author Jonathon
 */
public class GuiButton extends GuiElement {

    private BufferedImage down, up, hover, current = null;
    private GuiButtonAction guiButtonAction;
    private int downCounter = 0, downTime = 15;
    
    /**
     * Creates a new GuiButton by specifying its position, size, image data,
     * and press response.
     * @param x the x position.
     * @param y the y position.
     * @param w the width.
     * @param h the height.
     * @param down the image to show when the button is pressed.
     * @param up the image to show when the button is not being hovered over.
     * @param hover the image to show when the button is being hovered over.
     * @param guiButtonAction the method to call when this button is pressed.
     */
    public GuiButton(int x, int y, int w, int h,
            BufferedImage down, BufferedImage up, BufferedImage hover,
            GuiButtonAction guiButtonAction
    ) {
        super(x, y, w, h);
        this.down=down;
        this.up=up;
        this.hover=hover;
        this.guiButtonAction=guiButtonAction;
    }

    /**
     * Sets the number of frames the button is shown in the down state after
     * being pressed.
     * @param downTime the number of the frames the button is down. 
     */
    public void setDownTime(int downTime) {
        this.downTime=downTime;
    }
    
    @Override
    public void update(int mouseX, int mouseY) {
        super.update(mouseX, mouseY);
        if (downCounter > 0) {
            downCounter--;
        } else {
            if (contains(mouseX, mouseY)) {
                current = hover;
            } else {
                current = up;
            }
        }
    }

    @Override
    public void onMousePress() {
        current = down;
        downCounter = downTime;
        guiButtonAction.onButtonPress();
    }
    
    @Override
    public void render(Object graphicsObject) {
        ((Graphics)graphicsObject).drawImage(current, getX(), getY(), getWidth(), getHeight(), null);
    }
}
