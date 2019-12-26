package gameObjects.entityState;

import gameObjects.GroundEnemy;
import util.EntityEnum;

public class GroundEnemyDeadState extends GroundEnemyState{
    
    public GroundEnemyDeadState(GroundEnemy enemy){
        super(enemy);
        this.enemy.setAnimation(this.enemy.getSprite().getSprite(EntityEnum.DEAD), 80);
    }

    /*
        When the player change his state into dead state, he will perform all the dead animation and then he will set himself to dead.
    */
    @Override
    public void updateGame() {
        enemy.setTimeX(enemy.getTimeX() - 1);
        if(enemy.getAnimation().playingLastFrame()){
            enemy.setDeadAniEnded(true);
        }
    }

    @Override
    public void nextState(int code) {
        
    }

    @Override
    public void previousState(int code) {
    
    }
}