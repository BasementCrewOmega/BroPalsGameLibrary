/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.animation;

import java.awt.image.BufferedImage;

/**
 * A track of images for an Animation;
 * @author Kevin Prehn
 */
public class Track {
    
    private BufferedImage[] images;
    private int framesBetweenImages, frameWaitOn, imageOn;
    
    /**
     * Creates a new Track
     * @param imgs 
     */
    public Track(BufferedImage[] imgs) {
        images = imgs;
        framesBetweenImages = 4;
        frameWaitOn = 0;
        imageOn = 0;
    }
    
    public Track(BufferedImage[] imgs, int framesBetweenImages) {
        this(imgs);
        this.framesBetweenImages = framesBetweenImages;
    }
    
    public void update() {
        frameWaitOn++;
        if (frameWaitOn >= framesBetweenImages) {
            imageOn++;
            if (imageOn >= images.length) {
                imageOn = 0;
            }
            frameWaitOn = 0;
        }
    }
    
    public BufferedImage getCurrentImage() {
        return images[imageOn];
    }
    
    /**
     * Creates a new track from splitting an image. The method will split
     * the image up. It will order the animation starting
     * from the top left and continue from left to right and
     * top to bottom.
     * @param img The image to be split up
     * @param width The width of each sub-image
     * @param height The height of each sub-image
     */
    public Track(BufferedImage img, int width, int height) {
        BufferedImage[] images = null;
        int rows = img.getHeight() / height;
        int cols = img.getWidth() / width;
        images = new BufferedImage[rows * cols];
        
        for (int r=0; r<rows; r++) {
            for (int c=0; c<cols; c++) {
                images[(r * c) + c] = img.getSubimage(c * width, r * height, 
                        width, height);
            }
        }
    }
}
