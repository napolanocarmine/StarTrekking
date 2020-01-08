/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import util.graphics.EntitySprite;
import util.Position;

/**
 * Class that represents a walking enemy on the ground
 * @author Star Trekking
 */
public class GroundEnemy extends Enemy{
    
    /**
     * Call the constructor of the enemy class
     * @param sprite
     * @param origin
     * @param size 
     */
    public GroundEnemy(EntitySprite sprite, Position origin, int size) {
        super(sprite, origin, size);
    }
    
    /**
     * Apply a movement to the enemy
     */
    @Override
    public void move(){
        super.move();
        dy = (float) ((-0.5 * gravity * Math.pow(timey, 2))) + dy0;
        if (tc.collisionTileDown(0, dy - previousY)) {
            dy = previousY;
            dy0 = dy;
            timey = 0;
        } else {
            previousY = dy;
            timey++;
        }
    }
}
