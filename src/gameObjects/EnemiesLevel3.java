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
        positions.add(new Position(1400, groundY-50));
        positions.add(new Position(3200, groundY-50));
        positions.add(new Position(3700, groundY+50));
        positions.add(new Position(3700, groundY-250));
        positions.add(new Position(3900, groundY-250));
        positions.add(new Position(4000, groundY-350));
        positions.add(new Position(4200, groundY-350));
        positions.add(new Position(4500, groundY-350));
        positions.add(new Position(4700, groundY+50));
        positions.add(new Position(5000, groundY+50));
        positions.add(new Position(5000, groundY+250));
        positions.add(new Position(5500, groundY+50));
        positions.add(new Position(6000, groundY+50));
        positions.add(new Position(7200, groundY+50));
        positions.add(new Position(8000, groundY+50));
        positions.add(new Position(10000, groundY+50));
        positions.add(new Position(12000, groundY+50));
    }
    
    /**
     * Produces the enemies.
     */  
    @Override
    protected final void createEnemies() {
        for (Position p : positions) {
            addObj(new GroundEnemy(sprite, p, enemySize));
        }
    }
    
}
