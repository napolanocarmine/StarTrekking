/* @author: StarTrekkingCompany
 *
 *
 */
package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import graphics.Animation;
import graphics.EntitySprite;
import util.AABB;
import util.Position;
import util.EntityState;
import util.TileCollision;

public abstract class Entity {

    protected Animation ani;
    protected EntitySprite sprite;
    protected Position pos;
    protected Position originPos;
    protected int size;

    protected EntityState state; //Key premuta
    protected EntityState currentState; //Animazione Corrente

    protected float dx;
    protected float dy;

    protected float maxSpeed = 20f;
    protected float acc = 0.0003f;
    
    protected AABB bounds;
    protected TileCollision tc;

    protected int aniDelay = 80;

    protected float timex = 0;
    protected float timey = 0;

    protected float vx = 0;
    protected float vy;

    protected float initialSpeed;
    
    protected float dy0;
    protected float dx0;
    
    protected float gravity = -0.01f;
    
    protected float previousY;
    protected float previousX;
    
    protected boolean dead = false;
    
    //protected AABB hitBounds;
    //protected AABB bounds;
    //protected TileCollision tc;
    public Entity(EntitySprite sprite, Position origin, int size, EntityState state) {
        this.sprite = sprite;
        this.pos = origin;
        this.originPos = origin;
        this.size = size;
        this.ani = new Animation(sprite.getSprite(state));
        this.ani.setDelay(aniDelay);
        this.state = state;
        this.currentState = state;
        this.dx0 = pos.getX();
        this.dy0 = pos.getY();
        this.previousY = dx0;
        this.previousX = dy0;
        this.tc = new TileCollision(this);
    }

    public Entity(EntitySprite sprite, Position origin, int size) {
        this.sprite = sprite;
        pos = origin;
        this.size = size;
        this.ani = new Animation(sprite.getSprite(state));
    }

    public void setSprite(EntitySprite sprite) { this.sprite = sprite; }
    public void setDead() { state = EntityState.DEAD; }
    public void setSize(int i) { size = i; }
    public void setMaxSpeed(float f) { maxSpeed = f; }
    public void setAcc(float f) { acc = f; }
    public int getSize() { return size; }
    public Animation getAnimation() { return ani; }
    public AABB getBounds(){ return bounds; }
    public Position getPos(){ return pos; }
    public boolean getDead(){ return dead; }
    public EntityState getState(){ return state; }
    
    public void setAnimation(EntityState state, BufferedImage[] frames, int delay) {
        this.state = state;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }

    //Setta l'animazione sulla base dello stato;
    public void animate(EntityState state) {
        if (state != this.currentState) {
            this.currentState = state;
            setAnimation(state, sprite.getSprite(state), aniDelay);
        }
    }

    public void updateGame(EntityState state) {
        animate(state);
        ani.updateGame();
    }

    public void updateGame() {
        ani.updateGame();
    }

    public abstract void isDead();
    public abstract void render(Graphics2D g);
}
