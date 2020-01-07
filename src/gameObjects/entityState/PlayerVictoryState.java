/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects.entityState;

import gameObjects.Player;
import State.State;

/**
 * Represent the VictoryState of the Player
 * @author Star Trekking
 */
public class PlayerVictoryState extends PlayerState{

    /**
     * Constructor of the Player Victory State
     * @param p is the player to set the state
     */
    public PlayerVictoryState(Player p){
        super(p);
    }
    
    @Override
    public void nextState(State state) { 
        p.setState(state);
    }

    @Override
    public void set() {
        
    }
    
}
