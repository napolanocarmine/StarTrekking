package gameObjects.entityState;

import gameObjects.Player;
import gamestate.State;
import util.EntityEnum;

public class PlayerRunState extends PlayerState{
    
    public PlayerRunState(Player p){
        super(p);
    }
    
    @Override
    public void nextState(State state) { 
        p.setState(state);
    }

    @Override
    public void set() {
        p.setAnimation(p.getSprite().getSprite(EntityEnum.RUN), 80);
        p.setBounds(p.getStandBounds());
    }

}


