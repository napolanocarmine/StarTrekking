package gameObjects.entityState;

import gameObjects.GroundEnemy;


public abstract class GroundEnemyState implements EntityState {
    
    GroundEnemy enemy;
    
    public GroundEnemyState(GroundEnemy enemy){
        this.enemy = enemy;
    }
    
    public abstract void nextState(int code);
    public abstract void previousState(int code);
    public abstract void updateGame();
    
}
