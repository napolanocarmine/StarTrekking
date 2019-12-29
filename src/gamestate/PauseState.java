/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import javax.swing.SwingUtilities;
import music.MusicGame;
import panels.PausePanel;

/**
 *
 * @author Gianluca
 */
public class PauseState extends GameState{
    private GameStateManager gsm; 
    
    public PauseState(GameStateManager gsm) {
        this.gsm = gsm;
        this.panel = new PausePanel(this);
        this.mg = new MusicGame("MainMenu");
    }

    @Override
    public void handleNext(int code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handlePrevious(int code) {
        if (code == 0){
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    mg.stop();
                    gsm.setState(gsm.getSps());
                }
            });
        }
    }
}
