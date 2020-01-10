/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import State.State;
import panels.SelectionLevelPanel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.music.MusicGame;

/**
 *
 *
 * 
 * Class to define the state of the game in which one playable level can be
 * choose.
 */
public class SelectionLevelState extends GameState {

    /**
     *Create the panel, which represents the Pause Menu.
     * @param gsm is the manager of the game's state.
     * @throws @throws java.io.IOException if SelectionPanel raises IOException.
     */
    public SelectionLevelState(GameStateManager gsm) throws IOException {

        this.panel = new SelectionLevelPanel(this);
        this.gsm = gsm;
    }

    /**
     * Set the behavior of the SelectionLevelState.
     * It doesn't do anything.
     */
    @Override
    public void set() {
    }

    /**
     *It is used to pass in the next state and to stop the corresponding music. 
     * @param state is the next state handles by gsm.
     */
    @Override
    public void nextState(State state) {
        this.stopMusic();
        gsm.setState(state);
    }

}
