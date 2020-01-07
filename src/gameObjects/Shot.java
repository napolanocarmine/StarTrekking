package gameObjects;

import gameObjects.entityState.ShotRunState;
import util.graphics.EntitySprite;
import java.awt.Graphics2D;
import util.EntityBox;
import util.Position;

/**
 * Class representing the shot of the player (it is an entity object)
 * @author giomig
 */
public class Shot extends Entity {

    public Shot(EntitySprite sprite, Position origin, int size, float initialSpeed) {
        super(sprite, origin, size);
        this.bounds = new EntityBox(pos, 16, 16, 16, 16);
        this.state = new ShotRunState(this);
        this.initialSpeed = initialSpeed;
        this.vx = initialSpeed + 0.2f;
        this.acc = 0.001f;
    }

    /**
     * 
     * @return true if the shot collided with another object
     */
    public boolean collides() {
        return tc.collisionTileObj(5, 0);
    }

    /**
     * Change the position of the shot object
     */
    public void move() {
        dx = (float) ((0.5 * acc * Math.pow(timex, 2) + vx * timex)) + dx0 + 96;
        timex++;
    }

    /**
     * Update the position of the shot
     */
    @Override
    public void updateGame() {
        super.updateGame();
        move();
        pos.setX(dx);
    }

    /**
     * Render graphically the shot object
     * @param g 
     */
    @Override
    public void render(Graphics2D g) {  //draw the player in the panel
        g.drawImage(ani.getImage(), (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), size, size, null);
    }

}
