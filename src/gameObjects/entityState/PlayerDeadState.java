package gameObjects.entityState;

import gameObjects.Player;
import State.State;
import util.EntityEnum;

/**
 * Represent the DeadState of the Player
 * @author Star Trekking
 */
public class PlayerDeadState extends PlayerState{
    
    /**
     * Constructor of the Player Dead State
     * @param p is the player to set the state
     */
    public PlayerDeadState(Player p){
        super(p);
    }

    /*
        When the player change his state into dead state, he will perform all the dead animation and then he will set himself to dead.
    */
    @Override
    public void updateGame() {
        super.updateGame();
        p.setTimeX(p.getTimeX() - 1);
        if(p.getAnimation().playingLastFrame()){
            p.setDeadAniEnded(true);
        }
    }

    @Override
    public void nextState(State state) {
        p.setState(state);
    }

    @Override
    public void set() {
        p.setAnimation(p.getSprite().getSprite(EntityEnum.DEAD), 80);
        p.getMg().setMusic("GameOver");
        p.getMg().play();
    }

}