package gameObjects.entityState;

import gameObjects.Enemy;


public abstract class EnemyState implements EntityState {
    
    Enemy enemy;
    
    public EnemyState(Enemy enemy){
        this.enemy = enemy;
    }
    
    public abstract void nextState(EntityState state);
    public abstract void updateGame();
    
}
