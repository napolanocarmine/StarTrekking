package gameObjects.entityState;

import gameObjects.Enemy;
import State.State;

/**
 * Abstract Class to keep references to EntityState.
 * @author Star Trekking
 */
public abstract class EnemyState extends EntityState {
    
    Enemy enemy;
    
    /**
     * Costructor Of the state of the Enemy
     * @param enemy enemy to set the state
     */
    public EnemyState(Enemy enemy){
        this.enemy = enemy;
    }
    
    @Override
    public abstract void nextState(State state);
    @Override
    public abstract void updateGame();
    @Override
    public abstract void set();
    
}
