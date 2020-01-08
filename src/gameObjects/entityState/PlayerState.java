package gameObjects.entityState;

import gameObjects.Player;
import State.State;
import util.music.MusicGame;

/**
 * Abstract Class to keep references to EntityState.
 * @author Star Trekking
 */
public abstract class PlayerState extends EntityState {
    
    Player p;
    MusicGame mg;
    
    /**
     * Costructor Of the state of the Player
     * @param p player to set the state
     */
    public PlayerState(Player p){
        this.p = p;
    }
    
    @Override
    public abstract void nextState(State state);
    
    @Override
    public abstract void set();
    
    
    @Override
    public void updateGame(){
        p.horizontalMove();
        p.verticalMove();
    }
    
}
