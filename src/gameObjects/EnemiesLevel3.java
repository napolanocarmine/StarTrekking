/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import graphics.EntitySprite;
import java.util.ArrayList;
import util.Position;

/**
 *
 * @author Star Trekking
 */
public class EnemiesLevel3 extends EnemiesLevel{
    
    
    /**
     * Creates enemies of Level1.
     */
    public EnemiesLevel3() {
        sprite = new EntitySprite("entity/dragon", 64, 64);
        groundY += 10;
        initPositionArray();
        createEnemies();
    }

    /**
     * Produces the position of the enemies.
     */    
    
    @Override
    protected final void initPositionArray() {
        positions = new ArrayList<>();
        positions.add(new Position(700,groundY-300));
        positions.add(new Position(1500,groundY-65));
        positions.add(new Position(2700,groundY-25));
        positions.add(new Position(3200,groundY-65));
        positions.add(new Position(3700,groundY-85));
        positions.add(new Position(4000,groundY-85));
        positions.add(new Position(4700,groundY-25));
        positions.add(new Position(4700,groundY-125));
        positions.add(new Position(5500,groundY-25));
        positions.add(new Position(6500,groundY-85));
        positions.add(new Position(6900,groundY-85));
        positions.add(new Position(6900,groundY));
        positions.add(new Position(7100,groundY-85));
        positions.add(new Position(7100,groundY));
        positions.add(new Position(7700,groundY));
        positions.add(new Position(7700,groundY- 125));
        positions.add(new Position(8000,groundY- 125));
        positions.add(new Position(8500,groundY- 125));
        positions.add(new Position(9000,groundY));
        positions.add(new Position(9500,groundY- 80));
        positions.add(new Position(9900,groundY- 80));
        positions.add(new Position(10500,groundY));
        positions.add(new Position(11000,groundY-150));
        //
        positions.add(new Position(12000,groundY));
        positions.add(new Position(13500,groundY-40));
    }
    
    /**
     * Produces the enemies.
     */  
    @Override
    protected final void createEnemies() {
        for (Position p : positions) {
            addObj(new FlyingEnemy(sprite, p, enemySize));
        }
    }
    
}
