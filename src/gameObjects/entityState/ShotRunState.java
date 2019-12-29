package gameObjects.entityState;

import gameObjects.Shot;
import util.EntityEnum;

public class ShotRunState extends ShotState{
    
    public ShotRunState(Shot s){
        super(s);
        set();
    }

    @Override
    public void updateGame() {
        
    }

    @Override
    public void nextState(EntityState state) {
        this.s.setState(state);
    }

    @Override
    public void set() {
        s.setAnimation(s.getSprite().getSprite(EntityEnum.ATTACK), 80);
    }
}
