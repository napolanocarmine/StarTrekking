package gameObjects.entityState;

import gameObjects.Player;
import gamestate.State;
import music.MusicGame;

public abstract class PlayerState implements State {
    
    Player p;
    MusicGame mg;
    
    public PlayerState(Player p){
        this.p = p;
    }
    
    @Override
    public abstract void nextState(State state);
    
    @Override
    public void updateGame(){
        p.horizontalMove();
        p.verticalMove();
    }
    
}
