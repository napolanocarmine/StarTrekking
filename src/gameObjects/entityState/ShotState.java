package gameObjects.entityState;

import gameObjects.Shot;
import State.State;

/**
 * Abstract Class to keep references to EntityState.
 * @author Star Trekking
 */
public abstract class ShotState extends EntityState {
    
    Shot s;
    
    /**
     * Costructor Of the state of the Shot
     * @param s is the shot to set state
     */
    public ShotState(Shot s){
        this.s = s;
    }
    
    @Override
    public abstract void nextState(State state);
    @Override
    public abstract void updateGame();
    @Override
    public abstract void set();
    
}
