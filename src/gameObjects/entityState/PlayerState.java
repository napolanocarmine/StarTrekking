package gameObjects.entityState;

import gameObjects.Player;
import music.MusicGame;
import util.EntityEnum;


public abstract class PlayerState implements EntityState {
    
    Player p;
    MusicGame mg;
    
    public PlayerState(Player p){
        this.p = p;
    }
    
    @Override
    public abstract void nextState(EntityState state);
    
    @Override
    public void updateGame(){
        p.horizontalMove();
        p.verticalMove();
    }
    
}
