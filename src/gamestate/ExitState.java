/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import java.awt.AWTEvent;
import javax.swing.JButton;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

/**
 *
 * @author Gianluca
 */
public class ExitState extends State {
    private GameStateManager gsm;
   
    
    public ExitState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    
        
    
}
