/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import State.State;
import panels.MainMenuPanel;
import java.io.IOException;
import util.music.MusicGame;

/**
 *
 * @author Star Trekking
 */
/**
 *
 * Class which represents the state in which the game shows the main menu.
 */
public class MainMenuState extends GameState {
    /**
     * Create the panel, which represents the Main Menu.
     * @param gsm is the manager of the game's state.
     * @throws java.io.IOException if MainMenuPanel raises IOException.
     */
    public MainMenuState(GameStateManager gsm) throws IOException {
        this.panel = new MainMenuPanel(this);
        this.gsm = gsm;
//        this.mg = new MusicGame("MainMenu");
        gsm.setMusic("MainMenu");
    }

    /**
     *It is used to set and then play the right music to Main Menu. 
     *
     */
    
    
    public void set(){
        gsm.setMusic("MainMenu");
        gsm.getMusicGame().play();
    }
    
    /**
     * It is used to stop music.
     */
    @Override
    public void stopMusic(){
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

    /**
     *It is used when you want to close the game.
     */
    public void exit(){
        this.gsm.exit();
    }
}
