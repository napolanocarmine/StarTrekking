package gameObjects.entityState;

import gameObjects.Enemy;
import State.State;
import util.EntityEnum;

/**
 * Represent the RunState of the Enemy
 * @author Star Trekking
 */
public class EnemyRunState extends EnemyState{
    
    /**
     * Constructor of Enemy Run State
     * @param enemy enemy to set the state
     */
    public EnemyRunState(Enemy enemy){
        super(enemy);
    }

    @Override
    public void updateGame() {
        
    }

    @Override
    public void nextState(State state) {
        enemy.setState(state);
    }

    @Override
    public void set() {
        enemy.setAnimation(enemy.getSprite().getSprite(EntityEnum.RUN), 80);
    }

}
