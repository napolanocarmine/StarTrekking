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
     *
     * @throws java.io.IOException
     */
    public GameOverState(GameStateManager gsm) throws IOException {
        this.panel = new GameOverPanel(this);
        this.gsm = gsm;
//        this.mg = new MusicGame("MainMenu");
    }

    /**
     * Define the menu's components.
     */
    /**
     * Method which handles the next state, that could be "Play" or "Main Menu"
     *
     * @param code code represented the next state.
     */
    @Override
    public void handleNext(int code) {
        //code to manage the next state
        this.stopMusic();
        if (code == 0) {
            gsm.setState(gsm.getSps());
        } else if (code == 1) {
            gsm.setState(gsm.getMms());
        }

    }

    @Override
    public void handlePrevious(int code) {
    }

    @Override
    public void set(){
        gsm.setMusic("GameOverMenu");
        gsm.getMusicGame().play();
    }

}
