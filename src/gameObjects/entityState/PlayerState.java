package gameObjects.entityState;

import gameObjects.Player;
import util.EntityEnum;


public abstract class PlayerState implements EntityState {
    
    Player p;
    
    public PlayerState(Player p){
        this.p = p;
    }
    
    public abstract void nextState(int code);
    public abstract void previousState(int code);
    public abstract void updateGame();
    
}
