package gameObjects.entityState;

import gameObjects.Player;
import util.EntityEnum;

public class PlayerDeadState extends PlayerState{
    
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
    public void nextState(EntityState state) {
        p.setState(state);
    }

    @Override
    public void set() {
        p.setAnimation(p.getSprite().getSprite(EntityEnum.DEAD), 80);
        p.getMg().setMusic("GameOver");
        p.getMg().play();
    }

}