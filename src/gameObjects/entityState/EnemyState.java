package gameObjects.entityState;

import gameObjects.Enemy;
import State.State;


public abstract class EnemyState extends EntityState {
    
    Enemy enemy;
    
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
