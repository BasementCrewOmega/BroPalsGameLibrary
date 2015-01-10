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
import java.util.ArrayList;

/**
 * A class that is a way to structure arrays of images for an Animation
 * @author Kevin Prehn
 */
public class Animation {
    
    ArrayList<Track> tracks;
    Track currentTrack;
    
    /**
     * Create a new Animation object with the given tracks.
     * @param tracks The initial tracks in this Animation object.
     */
    public Animation(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }
    
    /**
     * Create a new Animation object with the no tracks.
     */
    public Animation() {
        this.tracks = new ArrayList<>();
    }
    
    /**
     * Updates the animation
     */
    public void update() {
        for (Track t : tracks) {
            t.update();
        }
    }
    
    public BufferedImage getCurrentImage() {
        return currentTrack.getCurrentImage();
    }
    
    /**
     * Add a new track to the list
     * @param track The track being added
     * @return The index of the newly added track
     */
    public int addTrack(Track track) {
        tracks.add(track);
        return tracks.indexOf(track);
    }
    
    /**
     * Set the current track to be updated in this Animation object.
     * @param index The index of the track to be set to in this 
     * Animation object.
     */
    public void setTrack(int index) {
        currentTrack = tracks.get(index);
    }
}
