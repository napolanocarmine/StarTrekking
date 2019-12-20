/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import gamestate.State;

/**
 *
 * @author Gianluca
 */
public interface GsmListener {
    public void stateChanged(State s);
}
