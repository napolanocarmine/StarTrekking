/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.music;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Star Trekking
 */
/**
 * Class that provides MusicGame and its control
 * 
 */
public class MusicGame {

    private Clip clip;
    private String name;
    private static boolean mute = false;

    /**
     * Default constructor of MusicGame Class.
     * 
     */
    public MusicGame() {
    }

    /**
     * Create a MusicGame.
     * 
     * @param  music is the name of the music to set
     */
    public MusicGame(String music) {
        this.name = music;
        setMusic(this.name);
    }
    
    /**
     * It is used to set a .wav file in Clip object
     *
     * @param nameMusic represents the name of the file .wav to be setted.
     */
    public void setMusic(String nameMusic) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource("sounds/" + nameMusic + "Music.wav"));
            clip = AudioSystem.getClip();
            clip.open(audio);
            audio.close();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * It is used to play a Clip if it is not mute
     *
     */    
    public void play() {
        if (!mute) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    /**
     * It is used to stop the Clip that is playing and we flush memory location
     *
     */
    public void stop() {
        clip.stop();
        clip.flush();
    }

    /**
     * It is used to flag to reproduce continuously a Clip
     *
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    /**
     * Return the clip.
     *
     * @return clip represents the clip setted.
     */
    public Clip getClip() {
        return clip;
    }

    /**
     * Return the name of Clip.
     *
     * @return name represents the clip's name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Return the mute value.
     *
     * @return mute represents bollean value.
     */
    public static boolean getMute() {
        return mute;
    }

    /**
     * If it is called set mute to its opposite;
     * It calls clip.stop() if mute is true;
     * It calls clip.start() if mute is false;
     *
     */
    public void toggleMute() {
        this.mute = !this.mute;
        if (mute == true) {
            clip.stop();
        } else {
            clip.start();
        }
    }

}
