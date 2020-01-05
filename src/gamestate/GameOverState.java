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
    @Override
    public void set(){
        gsm.setMusic("GameOverMenu");
        gsm.getMusicGame().play();
    }

    @Override
    public void nextState(State state) {
        this.stopMusic();
        gsm.setState((GameState) state);
    }
    

    @Override
    public void updateGame() {
        
    }

}
