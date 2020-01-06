package gameObjects.entityState;

import gameObjects.Enemy;
import State.State;


public abstract class EnemyState extends EntityState {
    
    Enemy enemy;
    
    public EnemyState(Enemy enemy){
        this.enemy = enemy;
    }
    
    public abstract void updateGame();
    public abstract void set();
    
}
