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
     * @param imgs The images in the track
     */
    public Track(BufferedImage[] imgs) {
        images = imgs;
        framesBetweenImages = 4;
        frameWaitOn = 0;
        imageOn = 0;
    }
    
    /**
     * Create a new Track object with the given images while setting how
     * many update cycles pass before switching the image
     * @param imgs The images in the track
     * @param updatesBetweenImages The amount of update cycles between an
     * image switch.
     */
    public Track(BufferedImage[] imgs, int updatesBetweenImages) {
        this(imgs);
        this.framesBetweenImages = updatesBetweenImages;
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
    
    /**
     * Creates a new track from splitting an image. The method will split
     * the image up. It will order the animation starting
     * from the top left and continue from left to right and
     * top to bottom. Also adds a parameter for setting the update cycles
     * between each image switch.
     * @param img The image to be split up
     * @param width The width of each sub-image
     * @param height The height of each sub-image
     * @param updatesBetweenImages The amount of update cycles between an
     * image switch.
     */
    public Track(BufferedImage img, int width, int height, int updatesBetweenImages) {
        this(img, width, height);
        this.framesBetweenImages = updatesBetweenImages;
    }
    
    /**
     * Updates the Track object.
     */
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
    
    /**
     * Reset the counters in the Track object.
     */
    public void resetCounter() {
        frameWaitOn = 0;
        imageOn = 0;
    }
    
    /**
     * Get the current image on the track.
     * @return The current image on the track.
     */
    public BufferedImage getCurrentImage() {
        return images[imageOn];
    }

    /**
     * Set how many update cycles need to pass in GameStateRunner before
     * it switches to the next image in the track.
     * @param framesBetweenImages The new amount of frames.
     */
    public void setFramesBetweenImages(int framesBetweenImages) {
        this.framesBetweenImages = framesBetweenImages;
        this.frameWaitOn = 0; // reset counter
    }

}
