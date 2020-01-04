/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import javax.swing.*;

/**
 *
 * @author Star Trekking
 *
 * Abstract class describing objects and actions performed on a State object
 */

public interface State {
    public void nextState(State state);
    public void updateGame();
    public void set();
}
