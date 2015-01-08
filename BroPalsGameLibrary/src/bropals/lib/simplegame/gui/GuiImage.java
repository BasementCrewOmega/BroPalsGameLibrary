/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * A GUI element that simply displays an image.
 * @author Jonathon
 */
public class GuiImage extends GuiElement {

    private BufferedImage image;
    
    /**
     * Creates a GUI image to display an image.
     * @param x the x position.
     * @param y the y position.
     * @param w the width.
     * @param h the height.
     * @param img the image to display
     */
    public GuiImage(int x, int y, int w, int h, BufferedImage img) {
        super(x, y, w, h);
        image=img;
    }
    
    @Override
    public void render(Object graphicsObject) {
        ((Graphics)graphicsObject).drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }
    
}
