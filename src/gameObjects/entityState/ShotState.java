package gameObjects.entityState;

import gameObjects.Shot;
import gamestate.State;

public abstract class ShotState implements State {
    
    Shot s;
    
    public ShotState(Shot s){
        this.s = s;
    }
    
    public abstract void nextState(State state);
    public abstract void updateGame();
    
}
