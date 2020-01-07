/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import graphics.EntitySprite;
import java.util.ArrayList;
import util.EntityBox;
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
        positions.add(new Position(4000,groundY-285));
        positions.add(new Position(5000,groundY+20));
        positions.add(new Position(5300,groundY-135));
        positions.add(new Position(6500,groundY-125));
        positions.add(new Position(6500,groundY+20));
        positions.add(new Position(7100,groundY-115));
        positions.add(new Position(7100,groundY+20));
        positions.add(new Position(7700,groundY+20));
        positions.add(new Position(7700,groundY- 175));
        positions.add(new Position(8500,groundY- 125));
        positions.add(new Position(9000,groundY+20));
        positions.add(new Position(9500,groundY- 80));
        positions.add(new Position(9900,groundY- 80));
        //
        positions.add(new Position(12000,groundY-40));
        positions.add(new Position(13500,groundY-40));
    }
    
    /**
     * Produces the enemies.
     */  
    @Override
    protected final void createEnemies() {
        for (Position p : positions) {
            FlyingEnemy enemy = new FlyingEnemy(sprite, p, enemySize);
            enemy.setBounds(new EntityBox(p, 70, 32, 13, 32));
            addObj(enemy);
        }
    }
    
}
