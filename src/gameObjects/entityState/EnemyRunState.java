package gameObjects.entityState;

import gameObjects.Enemy;
import util.EntityEnum;

public class EnemyRunState extends EnemyState{
    
    public EnemyRunState(Enemy enemy){
        super(enemy);
    }

    @Override
    public void updateGame() {
        
    }

    @Override
    public void nextState(EntityState state) {
        enemy.setState(state);
    }

    @Override
    public void set() {
        enemy.setAnimation(enemy.getSprite().getSprite(EntityEnum.RUN), 80);
    }

}
