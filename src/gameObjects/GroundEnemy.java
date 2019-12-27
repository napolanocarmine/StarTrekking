/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import gameObjects.entityState.GroundEnemyRunState;
import graphics.EntitySprite;
import java.awt.Graphics2D;
import panels.GamePanel;
import util.AABB;
import util.Position;

/**
 *
 * @author StarTrekking
 *
 *
 */
public class GroundEnemy extends Entity {

    public GroundEnemy(EntitySprite sprite, Position origin, int size) {
        super(sprite, origin, size);
        this.bounds = new AABB(pos, 16, 32, 40, 32);
        this.state = new GroundEnemyRunState(this);
        this.initialSpeed = -0.05f;
        this.vx = initialSpeed;
    }

    public void move() {
        dx = vx * timex++ + dx0;

        dy = (float) ((-0.5 * gravity * Math.pow(timey, 2))) + dy0;
        if (tc.collisionTileDown(0, dy - dy0)) {
            dy = previousY;
        } else {
            timey++;
            previousY = dy;
        }
    }

    @Override
    public void updateGame() {
        super.updateGame();
        if (pos.getWorldVar().getX() < -100) {
            deadAniEnded = true;
        }
        move();
        pos.setY(dy);
        pos.setX(dx);
    }

    @Override
    public void render(Graphics2D g) {
        if (pos.getY() < GamePanel.HEIGHT) {// riduzione lag : perchè se non hanno una posizione di appoggio cadono e non vengono più renderizzati
            g.drawImage(ani.getImage(), (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), size, size, null);
//            g.setColor(Color.green);
//            g.drawRect((int) (pos.getWorldVar().getX() + bounds.getXOffset()), (int) (pos.getWorldVar().getY() + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
        }
    }

}
