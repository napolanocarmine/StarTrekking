package gameObjects.entityState;

import gameObjects.GroundEnemy;


public abstract class GroundEnemyState implements EntityState {
    
    GroundEnemy enemy;
    
    public GroundEnemyState(GroundEnemy enemy){
        this.enemy = enemy;
    }
    
    public abstract void nextState(EntityState state);
    public abstract void updateGame();
    
}
