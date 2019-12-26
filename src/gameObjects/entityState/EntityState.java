package gameObjects.entityState;

import gameObjects.Entity;
import util.EntityEnum;

public interface EntityState {
    
    public void nextState(int code);
    public void previousState(int code);
    public void updateGame();
    
}
