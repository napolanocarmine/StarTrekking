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
 * @author Star Trekking
 */
public class PauseState extends GameState{
    private boolean resume;
    
    public PauseState(GameStateManager gsm) throws IOException {
        this.gsm = gsm;
        this.panel = new PausePanel(this);
        this.resume = false;
//       this.mg = new MusicGame("MainMenu");
    }

    public void setResume(boolean resume) {
        this.resume = resume;
    }
    
    @Override
    public void set(){
//        gsm.setMusic("MainMenu");
//        gsm.getMusicGame().play();
    }

    @Override
    public void nextState(State state) {
        if(resume){
            gsm.resumeState(gsm.getSps());
            resume = false;
        }else
            gsm.setState((GameState) state);
        
    }

    @Override
    public void updateGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isResume() {
        return resume;
    }
    
    
}
