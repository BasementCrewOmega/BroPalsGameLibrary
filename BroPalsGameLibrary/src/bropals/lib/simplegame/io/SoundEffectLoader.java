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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package bropals.lib.simplegame.io;

import bropals.lib.simplegame.logger.ErrorLogger;
import bropals.lib.simplegame.sound.SoundEffect;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Loads SoundEffects. To be added to AssetLoader.
 * @author Jonathon
 */
public class SoundEffectLoader extends AssetLoader<SoundEffect> {
    
    @Override
    public void loadAsset(String key, InputStream inputStream) {
        try {
            AudioInputStream ais
                    = AudioSystem.getAudioInputStream(inputStream);

            AudioFormat format = ais.getFormat();

            /* 
             Convert the format if necessary

             Credit to "Killer Game Programming in Java" by Andrew Davidson 
             for the audio format conversion code
             */
            if ((format.getEncoding() == AudioFormat.Encoding.ULAW)
                    || (format.getEncoding() == AudioFormat.Encoding.ALAW)) {
                AudioFormat newFormat = new AudioFormat(
                        AudioFormat.Encoding.PCM_SIGNED,
                        format.getSampleRate(),
                        format.getSampleSizeInBits() * 2,
                        format.getChannels(),
                        format.getFrameSize() * 2,
                        format.getFrameRate(),
                        true
                );
                ais = AudioSystem.getAudioInputStream(newFormat, ais);
                format = newFormat;
            }
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                throw new UnsupportedAudioFileException();
            }
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
            ais.close();
            SoundEffect sfx = new SoundEffect(clip);
            add(key, sfx);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ErrorLogger.println("Could not load SoundEffect with key " + key);
        }
    }
}
