package entity;

import graphics.Sprite;
import java.awt.Graphics2D;
import frames.GameFrame;
import util.Position;

public class Shot extends Entity{

    public Shot(Sprite sprite, Position origin, int size) {
        super(sprite, origin, size);
    }
    
    public void move() {
        dx += 4;  //Player Acceleration
        if (dx > maxSpeed) {
            dx = maxSpeed; //if the delta x is over the max we reset it
        }
    }

    public void updateGame() {
        super.updateGame();
        move();
        pos.addX(dx);
    }

    @Override
    public void render(Graphics2D g) {  //draw the player in the panel
        g.drawImage(ani.getImage(), (int) pos.getX(), (int) pos.getY(), size, size, null);
    }
    
}
