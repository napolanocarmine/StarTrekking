package gameObjects.entityState;

import gameObjects.Player;
import State.State;
import util.EntityEnum;

/**
 * Represent the RunState of the Player
 * @author Star Trekking
 */
public class PlayerRunState extends PlayerState{
    
    /**
     * Constructor of the Player Run State
     * @param p is the player to set the state
     */
    public PlayerRunState(Player p){
        super(p);
    }
    
    @Override
    public void nextState(State state) { 
        p.setState(state);
    }

    @Override
    public void set() {
        p.setAnimation(p.getSprite().getSprite(EntityEnum.RUN), 80);
        p.setBounds(p.getStandBounds());
    }

}


