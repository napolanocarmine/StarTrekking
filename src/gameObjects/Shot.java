package gameObjects;

import graphics.EntitySprite;
import java.awt.Color;
import java.awt.Graphics2D;
import util.AABB;
import util.EntityState;
import util.Position;

public class Shot extends Entity{
    
    public Shot(EntitySprite sprite, Position origin, int size, float initialSpeed) {
        super(sprite, origin, size, EntityState.ATTACK);
        this.bounds = new AABB(pos, 16, 16, 16, 16);
        this.initialSpeed = initialSpeed;
        this.vx = initialSpeed + 0.4f;
        this.acc = 0.001f;
    }

    public boolean collides() {
        return tc.collisionTileObj(dx - previousX, 0);
    }

    public void move() {
        if (timex == 0) {
            vx = initialSpeed;
        }
        previousX = dx;
        dx = (float) ((0.5 * acc * Math.pow(timex, 2) + vx * timex)) + dx0 + 96;
        timex++;
    }

    @Override
    public void updateGame() {
        super.updateGame(state);
        move();
        pos.setX(dx);
    }

    @Override
    public void render(Graphics2D g) {  //draw the player in the panel
        g.drawImage(ani.getImage(), (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), size, size, null);
//        g.setColor(Color.blue);
//        g.drawRect((int) (pos.getWorldVar().getX() + bounds.getXOffset()),
//                (int) (pos.getWorldVar().getY() + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
    }

    @Override
    public void isDead() {
        
    }
    
}
