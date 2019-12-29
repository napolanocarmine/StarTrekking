package gameObjects.entityState;

import gameObjects.Entity;
import util.EntityEnum;

public interface EntityState {
    
    public void nextState(EntityState state);
    public void updateGame();
    public void set();
}
