/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import panels.MainMenuPanel;
import java.io.IOException;
import music.MusicGame;

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
     * Create the Main Menu.
     */
    public MainMenuState(GameStateManager gsm) throws IOException {
        this.panel = new MainMenuPanel(this);
        this.gsm = gsm;
//        this.mg = new MusicGame("MainMenu");
        gsm.setMusic("MainMenu");
    }

    /**
     * Called when either "Story-Mode" or "Rush-Mode" is pressed, based on the
     * pressed button the next state is set.
     *
     * @param code button code
     */
    
    @Override
    public void set(){
        gsm.setMusic("MainMenu");
        gsm.getMusicGame().play();
    }
    
    @Override
    public void stopMusic(){
    }

    @Override
    public void nextState(State state) {
        this.stopMusic();
        gsm.setState((GameState)state);
    }

    @Override
    public void updateGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void exit(){
        this.gsm.exit();
    }
}
