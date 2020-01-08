/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import State.State;
import java.io.IOException;
import panels.VictoryPanel;

/**
 *Class which represents the state in which the game shows the Victory menu.
 */
public class VictoryState extends GameState {
    private int level;
    
    /**
     *Create the panel, which represents the Victory Menu.
     * @param gsm is the manager of the game's state.
     * @throws java.io.IOException if VictoryPanel raises IOException.
     */
    public VictoryState(GameStateManager gsm) throws IOException {
        this.gsm = gsm;
        this.panel = new VictoryPanel(this);
    }

    /**
     *It is used to set the right level of the game. 
     * @param level is the number corresponding to the right level.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     *It is used to return the level.
     * @return 'level' correpsonding to the right level.
     */
    public int getLevel() {
        return level;
    }
    
    /**
     *
     */
    @Override
    public void set(){
        this.startMusic();
    }
    
    /**
     *It is used to set the victory music. 
     */
    public void startMusic(){ 
        gsm.setMusic("Victory");
        gsm.getMusicGame().play();
    }
    
    /**
     *It is used to pass in the next state and to stop the corresponding music. 
     * @param state is the next state handles by gsm.
     */
    @Override
    public void nextState(State state) {
        this.stopMusic();
        gsm.setState((GameState) state);
    }
  
}
