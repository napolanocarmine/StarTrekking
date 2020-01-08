package gameObjects.entityState;

import gameObjects.Shot;
import State.State;
import util.EntityEnum;

/**
 * Represent the RunState of the Shot
 * @author Star Trekking
 */
public class ShotRunState extends ShotState{
    
    /**
     * Constructor of the Shot Run State
     * @param s is the shot to set state
     */
    public ShotRunState(Shot s){
        super(s);
        set();
    }

    @Override
    public void updateGame() {
        
    }

    @Override
    public void nextState(State state) {
        this.s.setState(state);
    }

    @Override
    public void set() {
        s.setAnimation(s.getSprite().getSprite(EntityEnum.ATTACK), 80);
    }
}
