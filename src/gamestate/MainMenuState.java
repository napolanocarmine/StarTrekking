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
public class MainMenuState extends State {

    /**
     * Create the Main Menu.
     */
    public MainMenuState() throws IOException {
        this.panel = new MainMenuPanel(this);
    }

    /**
     * Called when either "Story-Mode" or "Rush-Mode" is pressed, based on the
     * pressed button the next state is set.
     *
     * @param state button code
     */
    @Override
    public void handleNext(int state) {
        if (state == 0) {
            System.out.println(gsm);
            gsm.setState(new SelectionLevelState());
        } else if (state == 1) {
            gsm.setState(new ExitState(gsm));
        }
    }

    @Override
    public void handlePrevious(int code) {
    }
    
    public MusicGame getMusicGame(){
        return gsm.getMusicGame();
    }
}
