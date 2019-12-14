/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;
import javax.swing.JPanel;

/**
 *
 * @author StarTrekking
 */
public abstract class State {
    protected JPanel panel;
    
    public void  handleNext(GameStateManager current_state){};
    
    public void handlePrevious(GameStateManager current_state){};
    
    public JPanel getPanel(){
        return panel;
    }
}
