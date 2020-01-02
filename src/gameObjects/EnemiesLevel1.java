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
 * Concrete Class that represents the enemies inside the Level 1.
 * @author Star Trekking
 */
public class EnemiesLevel1 extends EnemiesLevel{
    
    /**
     * Creates enemies of Level1.
     */
    public EnemiesLevel1(){
        sprite = new EntitySprite("entity/goblin", 64, 64);
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
        positions.add(new Position(1500, groundY));
        positions.add(new Position(2100, groundY));
        positions.add(new Position(3080, groundY));
        positions.add(new Position(4700, groundY));
        positions.add(new Position(5500, groundY));
        positions.add(new Position(7400, groundY));
        positions.add(new Position(7400, 220));
        positions.add(new Position(9500, groundY));
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
