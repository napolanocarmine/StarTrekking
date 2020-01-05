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
    public SelectionLevelState(GameStateManager gsm) throws IOException {

        this.panel = new SelectionLevelPanel(this);
        this.gsm = gsm;
//        this.mg = new MusicGame("MainMenu");
    }

    @Override
    public void set() {
    }

    @Override
    public void nextState(State state) {
        this.stopMusic();
        gsm.setState((GameState) state);
    }

    @Override
    public void updateGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
