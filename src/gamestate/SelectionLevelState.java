/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import panels.SelectionLevelPanel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import music.MusicGame;

/**
 *
 * @author StarTrekking
 *
 * Class to define the state of the game in which one playable level can be
 * choose
 */
public class SelectionLevelState extends GameState {

    /**
     * Initialize the background with a picture
     */
    public SelectionLevelState(GameStateManager gsm) {
        try {
            this.panel = new SelectionLevelPanel("/screen/forest.png", this);
        } catch (IOException e) {
            System.out.print(e);
        }
        this.gsm = gsm;
        this.mg = new MusicGame("MainMenu");
    }

    /**
     * Called when "Level 1" or "Level 2" or "Level 3" is pressed, based on the
     * pressed button the next state is set.
     *
     * @param code button code
     */
    @Override
    public void handleNext(int code) {
        this.stopMusic();
        if (code == 4) {
            gsm.setState(gsm.getMms());
        } else {
            // I know that I must go to StoryPlayState;
            StoryPlayState sps = gsm.getSps();
            switch (code) {
                case 1:
                    //call sps_level1;
                    System.err.println("è stato selezionato il livello 1");
                    sps.setLevel(1);
                    break;
                case 2:
                    //call sps_level2;
                    System.err.println("è stato selezionato il livello 2");
                    sps.setLevel(2);
                    break;
                default:
                    //call sps_level3;
                    System.err.println("è stato selezionato il livello 3");
                    sps.setLevel(3);
                    break;
            }
            gsm.setState(gsm.getSps());
        }
    }

    @Override
    public void handlePrevious(int code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
