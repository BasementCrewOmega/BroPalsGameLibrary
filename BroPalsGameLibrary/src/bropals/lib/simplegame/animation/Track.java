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
        this(null);
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
