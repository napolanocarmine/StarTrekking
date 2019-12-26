package gameObjects.entityState;

import gameObjects.GroundEnemy;
import util.EntityEnum;

public class GroundEnemyRunState extends GroundEnemyState{
    
    public GroundEnemyRunState(GroundEnemy enemy){
        super(enemy);
        enemy.setAnimation(enemy.getSprite().getSprite(EntityEnum.RUN), 80);
    }

    @Override
    public void updateGame() {
        
    }

    @Override
    public void nextState(int code) {
        
    }

    @Override
    public void previousState(int code) {
    
    }
}
