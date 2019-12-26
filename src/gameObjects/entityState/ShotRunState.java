package gameObjects.entityState;

import gameObjects.Shot;
import util.EntityEnum;

public class ShotRunState extends ShotState{
    
    public ShotRunState(Shot s){
        super(s);
        s.setAnimation(s.getSprite().getSprite(EntityEnum.ATTACK), 80);
    }

    @Override
    public void updateGame() {
        
    }

    @Override
    public void nextState(int code) {
        
    }

    @Override
    public void previousState(int code) {
    
    }
}
