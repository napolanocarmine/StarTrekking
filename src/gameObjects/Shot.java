package gameObjects;

import gameObjects.entityState.ShotRunState;
import graphics.EntitySprite;
import java.awt.Color;
import java.awt.Graphics2D;
import util.EntityBox;
import util.Position;

public class Shot extends Entity{
    
    public Shot(EntitySprite sprite, Position origin, int size, float initialSpeed) {
        super(sprite, origin, size);
        this.bounds = new EntityBox(pos, 16, 16, 16, 16);
        this.state = new ShotRunState(this);
        this.initialSpeed = initialSpeed;
        this.vx = initialSpeed + 0.2f;
        this.acc = 0.001f;
//        dx = (float) ((0.5 * acc * Math.pow(timex, 2) + vx * timex)) + dx0 + 96;
//        System.err.println("COLPO CREATO: dx: " + dx);
    }

    public boolean collides() {
        return tc.collisionTileObj(5, 0);
    }

    public void move() {
//        if (timex == 0) {
//            vx = initialSpeed;
//        }
        dx = (float) ((0.5 * acc * Math.pow(timex, 2) + vx * timex)) + dx0 + 96;
        timex++;
    }

    @Override
    public void updateGame() {
        super.updateGame();
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
    
}
