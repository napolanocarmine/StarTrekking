package gameObjects.entityState;

import gameObjects.Enemy;
import gamestate.State;


public abstract class EnemyState implements State {
    
    Enemy enemy;
    
    public EnemyState(Enemy enemy){
        this.enemy = enemy;
    }
    
    public abstract void nextState(State state);
    public abstract void updateGame();
    
}
