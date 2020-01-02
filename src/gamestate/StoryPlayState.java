/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import panels.GamePanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Star Trekking
 *
 * Class to describe the state of the game during he story mode
 */
public class StoryPlayState extends GameState {

    public static int level;

    /**
     * Create the Panel on which the Level is run.
     */
    public StoryPlayState(GameStateManager gsm) {
        this.panel = new GamePanel(this);
        this.gsm = gsm;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Method which handles the next state, that could be "Pause" or "Game-Over"
     *
     * @param code code represented the next state
     */
    @Override
    public void handleNext(int code) {
        /*
            codice che gestisce il prossimo stato,
            per questo sprint quando il maghetto muore da settare al Game-Over.
         */
        if (code == 1) {
            gsm.setState(gsm.getGos());
        }
        if (code == 2) {
            gsm.setState(gsm.getPs());
        }
        if (code == 3) {
            ((VictoryState) gsm.getVs()).setLevel(level);
            gsm.setState(gsm.getVs());

        }
    }

    public void restartGame() {
        ((GamePanel) panel).reset();
    }

    @Override
    public void handlePrevious(int code) {
    }

    public synchronized void pause() {

        ((GamePanel) panel).setPause(true);
        this.handleNext(2);
        notifyAll();
    }

    @Override
    public void set() {
        //super.set();
        ((GamePanel) panel).startThread();
        ((GamePanel) panel).setPause(false);

    }

}
