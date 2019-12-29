/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

/**
 *
 * @author Gianluca
 */
public class VictoryState extends GameState {
    private GameStateManager gsm; 
    
    public VictoryState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    @Override
    public void handleNext(int code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handlePrevious(int code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
}
