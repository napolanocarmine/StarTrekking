package gameObjects.entityState;

import gameObjects.Shot;

public abstract class ShotState implements EntityState {
    
    Shot s;
    
    public ShotState(Shot s){
        this.s = s;
    }
    
    public abstract void nextState(EntityState state);
    public abstract void updateGame();
    
}
