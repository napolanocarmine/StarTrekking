package gameObjects.entityState;

import gameObjects.GroundEnemy;
import util.EntityEnum;

public class GroundEnemyRunState extends GroundEnemyState{
    
    public GroundEnemyRunState(GroundEnemy enemy){
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
