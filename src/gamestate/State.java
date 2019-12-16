/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;
import javax.swing.*;

/**
 *
 * @author StarTrekking
 */
public abstract class State {
    protected JFrame frame;
    
    public void  handleNext(int code){};
    
    public void handlePrevious(int code){};
    
    public JFrame getFrame(){
        return frame;
    }
    
    public void setJFrame(JFrame frame){
        this.frame = frame;
    }
}
