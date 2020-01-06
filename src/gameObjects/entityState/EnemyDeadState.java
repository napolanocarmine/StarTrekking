package gameObjects.entityState;

import gameObjects.Enemy;
import State.State;
import util.EntityEnum;

public class EnemyDeadState extends EnemyState{
    
    public EnemyDeadState(Enemy enemy){
        super(enemy);
    }

    /*
     * When the player change his state into dead state, he will perform all the
     * dead animation and then he will set himself to dead.
    */
    @Override
    public void updateGame() {
        enemy.setTimeX(enemy.getTimeX() - 1);
        if(enemy.getAnimation().playingLastFrame()){
            enemy.setDeadAniEnded(true);
        }
    }

    @Override
    public void nextState(State state) {
        enemy.setState(state);
    }


    @Override
    public void set() {
        this.enemy.setAnimation(this.enemy.getSprite().getSprite(EntityEnum.DEAD), 80);    
    }

    



}