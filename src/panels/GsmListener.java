/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import gamestate.GameState;


/**
 *
 * @author StarTrekking
 *
 * Interface about the behavior of a GameStateManager on the changing of the
 * state
 */
public interface GsmListener {

    /**
     *
     * @param s
     */
    public void stateChanged(GameState s);

    /**
     *
     */
    public void exit();
}
