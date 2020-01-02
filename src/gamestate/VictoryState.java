/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import static gamestate.StoryPlayState.level;
import java.io.IOException;
import javax.swing.SwingUtilities;
import panels.VictoryPanel;

/**
 *
 * @author Gianluca
 */
public class VictoryState extends GameState {

    private GameStateManager gsm;
    private int level;

    public VictoryState(GameStateManager gsm) throws IOException {
        this.gsm = gsm;
        this.panel = new VictoryPanel(this);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void handleNext(int code) {
        if (code == 0) {
            if (level != 3) {
                ((StoryPlayState) gsm.getSps()).setLevel(level + 1);
            }
            ((StoryPlayState) gsm.getSps()).restartGame();
            gsm.setState(gsm.getSps());

        }
        if (code == 1) {
            ((StoryPlayState) gsm.getSps()).restartGame();
            gsm.setState(gsm.getSps());

        }
        if (code == 2) {
            ((StoryPlayState) gsm.getSps()).restartGame();
            gsm.setState(gsm.getMms());

        }
    }

    @Override
    public void handlePrevious(int code) {
       
    }

}
