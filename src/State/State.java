/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package State;

import javax.swing.*;

/**
 *
 * @author Star Trekking
 *
 * Interface describing objects and actions performed on a State object.
 */

public interface State {

    /**
     *It is used to pass in the next state.
     * @param state is the next state that has to be setted.
     */
    public void nextState(State state);

          /**
     *It is used to implement setter operations on the state.
     */
    public void set();
}
