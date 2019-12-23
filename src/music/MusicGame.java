/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import sun.audio.AudioPlayer;

/**
 *
 * @author CARMINE
 */
public class MusicGame {

    private Clip clip;

    public void setMusic(String nameMusic) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(AudioPlayer.class.getResourceAsStream("/sounds/" + nameMusic + "Music.wav"));
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
        }
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop() {
        clip.stop();
        clip.flush();
        clip.close();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
}
