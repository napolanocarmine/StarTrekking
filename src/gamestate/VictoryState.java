/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import java.io.IOException;
import panels.VictoryPanel;

/**
 *
 * @author Gianluca
 */
public class VictoryState extends GameState {
    private int level;
    
    public VictoryState(GameStateManager gsm) throws IOException {
        this.gsm = gsm;
        this.panel = new VictoryPanel(this);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
    

    @Override
    public void set(){
        
    }

    @Override
    public void nextState(State state) {
        gsm.setState((GameState) state);
    }

    @Override
    public void updateGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
