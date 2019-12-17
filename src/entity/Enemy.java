/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import graphics.Sprite;
import java.awt.Color;
import java.awt.Graphics2D;
import startrekking.GamePanel;
import util.AABB;
import util.EntityState;
import util.Position;
import util.TileCollision;


/**
 *
 * @author vince
 */
public class Enemy extends Entity {

    private float previousY;
    private float previousX;
    private final double gravity=-0.08f;
    private float timey; 
    private float moveleft=0.04f;
    

    public Enemy(Sprite sprite, Position origin, int size) {
        super(sprite, origin, size);
        this.timey = 0;
        this.bounds = new AABB(pos, 16, 32, 40, 32);
        previousY = (int)pos.getY();
        previousX = (int)pos.getX();
        tc=new TileCollision(this);
    }

   public void move() {
    dx = previousX - moveleft;
    previousX= dx;    
    dy = (float)((-0.5*gravity*Math.pow(timey, 2)));
    ani.setDelay(100);        
    if (tc.collisionTileDown(0, dy - previousY)) {
        dy = previousY;
    } else {
        timey += 0.5f;
        previousY = dy;
    }     
    }
    
    @Override
   public void updateGame() {
    super.updateGame();
    move();
    pos.setY(dy);
    pos.setX(dx);
   }
    
    @Override
    public void render(Graphics2D g) {
        if(pos.getY()<GamePanel.HEIGHT){// riduzione lag : perchè se non hanno una posizione di appoggio cadono e non vengono più renderizzati
        g.drawImage(ani.getImage(), (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), size, size, null);
        g.setColor(Color.green);
        g.drawRect((int) (pos.getWorldVar().getX() + bounds.getXOffset()), (int) (pos.getWorldVar().getY() + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
        }
    }

}
