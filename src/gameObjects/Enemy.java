/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import gameObjects.entityState.EnemyDeadState;
import gameObjects.entityState.EnemyRunState;
import gameObjects.entityState.EntityState;
import State.State;
import util.graphics.EntitySprite;
import java.awt.Graphics2D;
import panels.GamePanel;
import util.EntityBox;
import util.Position;

/**
 * @author StarTrekking
 */

/**
 * Represents the Enemy inside the level.
 * @author Star Trekking
 */
public abstract class Enemy extends Entity {

    private EntityState runState;
    private EntityState deadState;
    
    /**
     * Creates an enemy object.
     * @param sprite sprite of the enemy.
     * @param origin spawn position.
     * @param size size of the enemy.
     */
    public Enemy(EntitySprite sprite, Position origin, int size) {
        super(sprite, origin, size);
        this.bounds = new EntityBox(pos, 16, 32, 40, 32);
        this.initialSpeed = -0.05f;
        this.vx = initialSpeed;
        
        runState = new EnemyRunState(this);
        deadState = new EnemyDeadState(this);
        this.state = runState;
        state.set();
    }

    /**
     * 
     * @return the run state of the enemy
     */
    public State getRunState(){
        return runState;
    }
    
    /**
     * 
     * @return the dead state of the enemy
     */
    public State getDeadState(){
        return deadState;
    }
    
    /**
     * This method let evaluate next position of the enemy
     */
    public void move() {
        dx = vx * timex++ + dx0;
    }

    /**
     * Update the position of the enemy with the new one
     */
    @Override
    public void updateGame() {
        super.updateGame();
        if(pos.getWorldVar().getX() < GamePanel.WIDTH+100){
            if (pos.getWorldVar().getX() < -100) {
                deadAniEnded = true;
            }
            move();
            pos.setY(dy);
            pos.setX(dx);
        }
    }

    /**
     * Render graphically the entity enemy
     * @param g 
     */
    @Override
    public void render(Graphics2D g) {
        if (pos.getY() < GamePanel.HEIGHT) {// riduzione lag : perchè se non hanno una posizione di appoggio cadono e non vengono più renderizzati
            g.drawImage(ani.getImage(), (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), size, size, null);
        }
    }

}
