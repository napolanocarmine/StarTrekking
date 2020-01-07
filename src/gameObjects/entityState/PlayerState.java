package gameObjects.entityState;

import gameObjects.Player;
import State.State;
import util.music.MusicGame;

public abstract class PlayerState extends EntityState {
    
    Player p;
    MusicGame mg;
    
    public PlayerState(Player p){
        this.p = p;
    }
    
    @Override
    public abstract void nextState(State state);
    
    public abstract void set();
    
    
    public void updateGame(){
        p.horizontalMove();
        p.verticalMove();
    }
    
}
