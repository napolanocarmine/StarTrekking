/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects.entityState;

import State.State;

/**
 * Abstract Class to keep references to State.
 * @author Star Trekking
 */
public abstract class EntityState implements State {
    
    /**
     *It is used to implements the actions that update the game.
     */
    public abstract void updateGame();

}
