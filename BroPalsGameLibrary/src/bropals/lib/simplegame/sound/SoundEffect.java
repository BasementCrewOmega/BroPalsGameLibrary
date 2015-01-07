/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.sound;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;

/**
 * A simple sound effect.
 * @author Jonathon
 */
public class SoundEffect {
    
    private final Clip clip;
    
    /**
     * Wraps the given {@link javax.sound.sampled.Clip Clip} as a SoundEffect.
     * @param clip the clip to wrap
     */
    public SoundEffect(Clip clip) {
        this.clip = clip;
        clip.setFramePosition(0);
    }

    /**
     * Plays this sound effect once.
     */
    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }
}
