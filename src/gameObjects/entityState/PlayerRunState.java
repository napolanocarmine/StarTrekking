package gameObjects.entityState;

import gameObjects.Player;
import util.EntityEnum;

public class PlayerRunState extends PlayerState{
    
    public PlayerRunState(Player p){
        super(p);
        p.setAnimation(p.getSprite().getSprite(EntityEnum.RUN), 80);
        p.setBounds(p.getStandBounds());
    }

    @Override
    public void updateGame() {
        p.horizontalMove();
        p.verticalMove();
    }

    @Override
    public void nextState(int code) {
        
    }

    @Override
    public void previousState(int code) {
    
    }
}


