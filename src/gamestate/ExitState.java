/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import javax.swing.JPanel;

/**
 *
 * @author Gianluca
 */
public class ExitState extends State {

    private GameStateManager gsm;

    public ExitState(GameStateManager gsm) {
        this.gsm = gsm;
        this.panel = new JPanel();
    }

}
