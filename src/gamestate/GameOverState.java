/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import panels.GameOverPanel;
import java.io.IOException;

/**
 *
 * @author Star Trekking
 */
/**
 * Class which represents the state in which the game shows the Game Over Menu.
 */
public class GameOverState extends State {

    /**
     * Create the Panel, which represents the Game Over Menu.
     *
     * @throws java.io.IOException
     */
    public GameOverState() throws IOException {
        this.panel = new GameOverPanel(this);
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
        int level = StoryPlayState.level;
        if (code == 0) {
            gsm.setState(new StoryPlayState(level));
        } else if (code == 1) {
            try {
                gsm.setState(new MainMenuState());
            } catch (IOException ex) {
            }
        }

    }

    @Override
    public void handlePrevious(int code) {
    }

}
