/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects.entityState;

import gameObjects.Player;
import State.State;

/**
 *
 * @author Demetrio
 */
public class PlayerVictoryState extends PlayerState{

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
