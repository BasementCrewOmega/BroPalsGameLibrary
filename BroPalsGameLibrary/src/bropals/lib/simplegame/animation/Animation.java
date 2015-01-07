/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public Animation(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }
    
    /**
     * Updates the animation
     */
    public void update() {
        for (Track t : tracks) {
            t.update();
        }
    }
    
    /**
     * Add a new track to the list
     * @param track The track being added
     * @return The index of the newly added track
     */
    public int addTrack(Track t) {
        tracks.add(t);
        return tracks.indexOf(t);
    }
    

}
