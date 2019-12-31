/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import javax.swing.JPanel;
import music.MusicGame;

/**
 *
 * @author Gianluca
 */
public abstract class GameState implements State {

    protected JPanel panel;
    protected GameStateManager gsm;
//    protected MusicGame mg;

    public JPanel getPanel() {
        return panel;
    }

    public void setJPanel(JPanel panel) {
        this.panel = panel;
    }

    public void setGSM(GameStateManager gsm) {
        this.gsm = gsm;
    }
    
    public abstract void set();
    
    public void stopMusic(){
        gsm.getMusicGame().stop();
    }
    public void loopMusic(){
        gsm.getMusicGame().loop();
    }
    
    public MusicGame getMusicGame(){
        return gsm.getMusicGame();
    }
    
}
