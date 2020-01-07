/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import util.graphics.EntitySprite;
import util.Position;

/**
 *
 * @author Star Trekking
 */
public class FlyingEnemy extends Enemy{
    
       public FlyingEnemy(EntitySprite sprite, Position origin, int size) {
        super(sprite, origin, size);
    }
    
    @Override
    public void move(){
        super.move();
        dy = dy0;
    }
}
