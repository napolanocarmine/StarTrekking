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
public class EnemiesLevel2 extends EnemiesLevel{
    
    /**
     * Creates enemies of Level1.
     */
    public EnemiesLevel2() {
        sprite = new EntitySprite("entity/skeleton", 64, 64);
        int enemieSize = 96;
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
        positions.add(new Position(1000, groundY));
        positions.add(new Position(1700, 340));
        positions.add(new Position(3200, groundY));
        positions.add(new Position(3600, 210));
        positions.add(new Position(5000, 220));
        positions.add(new Position(6000, groundY));
        positions.add(new Position(7200, groundY));
        positions.add(new Position(8000, groundY));
        positions.add(new Position(10000, groundY));
        positions.add(new Position(12000, groundY));
    }

    /**
     * Produces the enemies.
     */  
    @Override
    protected final void createEnemies() {
        for (Position p : positions) {
            addObj(new GroundEnemy(sprite, p, 96));
        }
    }
    
}
