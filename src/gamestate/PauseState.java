/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import State.State;
import java.io.IOException;
import javax.swing.SwingUtilities;
import util.music.MusicGame;
import panels.PausePanel;

/**
 *
 * Class which represents the state in which the game shows the Pause menu.
 */
public class PauseState extends GameState{
    private boolean resume;
    
    /**
     *Create the panel, which represents the Pause Menu.
     * @param gsm is the manager of the game's state.
     * @@throws java.io.IOException if PausePanel raises IOException.
     */
    public PauseState(GameStateManager gsm) throws IOException {
        this.gsm = gsm;
        this.panel = new PausePanel(this);
        this.resume = false;
//       this.mg = new MusicGame("MainMenu");
    }

    /**
     *It is used to set a boolean variable when the game is in the pause state.
     * @param resume is setted by the pause panel when the game has to be 
     * resumed.
     */
    public void setResume(boolean resume) {
        this.resume = resume;
    }
    
    /**
     *
     */
    @Override
    public void set(){
//        gsm.setMusic("MainMenu");
//        gsm.getMusicGame().play();
    }

    /**
     It is used to pass in the next state based on the value of boolean variable
     * 'resume'.
     * @param state is the next state handles by gsm.
     */
    @Override
    public void nextState(State state) {
        if(resume){
            gsm.resumeState(gsm.getSps());
            resume = false;
        }else
            gsm.setState(state);
    }

    /**
     *It is used to get the resume variable.
     * @return the value of variable resume.
     */
    public boolean isResume() {
        return resume;
    }
    
    
}
