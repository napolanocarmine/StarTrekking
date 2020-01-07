/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import util.graphics.EntitySprite;
import util.Position;

/**
 * Class representing a special kind of enemy: flying enemy
 *
 * @author Star Trekking
 */
public class FlyingEnemy extends Enemy {

    /**
     * Call the constructor of the enemy class
     * @param sprite
     * @param origin
     * @param size 
     */
    public FlyingEnemy(EntitySprite sprite, Position origin, int size) {
        super(sprite, origin, size);
    }

    /**
     * Apply a movement to the enemy
     */
    @Override
    public void move() {
        super.move();
        dy = dy0;
    }
}
