package gameObjects.entityState;

import gameObjects.Shot;

public abstract class ShotState implements EntityState {
    
    Shot s;
    
    public ShotState(Shot s){
        this.s = s;
    }
    
    public abstract void nextState(int code);
    public abstract void previousState(int code);
    public abstract void updateGame();
    
}
