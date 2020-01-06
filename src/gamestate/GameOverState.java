/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import panels.GameOverPanel;
import java.io.IOException;
import music.MusicGame;

/**
 *
 * @author Star Trekking
 */
/**
 * Class which represents the state in which the game shows the Game Over Menu.
 */
public class GameOverState extends GameState {


    /**
     * Create the Panel, which represents the Game Over Menu.
     * @param gsm is the manager of the game's state.
     * 
     * @throws java.io.IOException if GameOverPanel raises IOException.
     */
    public GameOverState(GameStateManager gsm) throws IOException {
        this.panel = new GameOverPanel(this);
        this.gsm = gsm;
//        this.mg = new MusicGame("MainMenu");
    }

    /**
     *
     * It is used to set and then play the right music corresponding to the Game
     * Over Menu.  
     */
    @Override
    public void set(){
        gsm.setMusic("GameOverMenu");
        gsm.getMusicGame().play();
    }
    
    /**
     * It is used to pass in the next state.
     * @param state is the next state handles by gsm.
     */

    @Override
    public void nextState(State state) {
        this.stopMusic();
        gsm.setState((GameState) state);
    }
    

    @Override
    public void updateGame() {
        
    }

}
