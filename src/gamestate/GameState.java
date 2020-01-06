/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import State.State;
import javax.swing.JPanel;
import music.MusicGame;

/**
 *
 * Abstract class which define the main properties of the game state.
 *
 */


public abstract class GameState implements State {

    
    protected JPanel panel;
    protected GameStateManager gsm;
//    protected MusicGame mg;

    /**
     *Getter method for the panel.
     * @return a Jpanel.
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     *Setter method for the panel.
     * @param panel is the panel that will be set.
     */
    public void setJPanel(JPanel panel) {
        this.panel = panel;
    }

    /**
     *Setter method for the game state manager.
     * @param gsm is the manager of the game's state.
     */
    public void setGSM(GameStateManager gsm) {
        this.gsm = gsm;
    }
    
    /**
     *Getter method for the game state manager.
     * @return of gsm that handles the state.
     */
    public GameStateManager getGSM() {
        return gsm;
    }
    
    @Override
    public void set(){
        throw new UnsupportedOperationException("method not overridden");
    }
    
    /**
     *
     */
    public void resume(){
        throw new UnsupportedOperationException("method not overridden");
    }
    
    /**
     *
     */
    public void startMusic(){
        throw new UnsupportedOperationException("method not overridden");
    }
    
    /**
     *It is used to stop the music clip.
     */
    public void stopMusic(){
        gsm.getMusicGame().stop();
    }
    
    /**
     *It is used to loop the music clip.
     */
    public void loopMusic(){
        gsm.getMusicGame().loop();
    }
    
    /**
     *It is used to get the music clip. 
     * @return
     */
    public MusicGame getMusicGame(){
        return gsm.getMusicGame();
    }
    
}
