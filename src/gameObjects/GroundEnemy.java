/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import graphics.EntitySprite;
import util.Position;

/**
 *
 * @author Star Trekking
 */
public class GroundEnemy extends Enemy{
    
    public GroundEnemy(EntitySprite sprite, Position origin, int size) {
        super(sprite, origin, size);
    }
    
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
