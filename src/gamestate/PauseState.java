/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import java.io.IOException;
import javax.swing.SwingUtilities;
import music.MusicGame;
import panels.PausePanel;

/**
 *
 * @author Gianluca
 */
public class PauseState extends GameState {

    private GameStateManager gsm;

    public PauseState(GameStateManager gsm) throws IOException {
        this.gsm = gsm;
        this.panel = new PausePanel(this);
        this.mg = new MusicGame("MainMenu");
    }

    @Override
    public void handleNext(int code) {
        if (code == 0) {
            mg.stop();
            gsm.getSps().restartGame();
            gsm.setState(gsm.getSps());

        }
        if (code == 1) {
            mg.stop();
            gsm.setState(gsm.getMms());

        }
    }

    @Override
    public void handlePrevious(int code) {
        if (code == 0) {

            mg.stop();
            gsm.setState(gsm.getSps());

        }
    }
}
