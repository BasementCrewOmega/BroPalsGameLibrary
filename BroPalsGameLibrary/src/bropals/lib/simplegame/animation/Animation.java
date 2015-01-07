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
    
    /**
     * Create a new Animation object with the given tracks.
     * @param tracks The initial tracks in this Animation object.
     */
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
