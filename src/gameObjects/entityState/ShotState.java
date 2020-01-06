package gameObjects.entityState;

import gameObjects.Shot;
import State.State;

public abstract class ShotState extends EntityState {
    
    Shot s;
    
    public ShotState(Shot s){
        this.s = s;
    }
    
    public abstract void nextState(State state);
    public abstract void updateGame();
    public abstract void set();
    
}
