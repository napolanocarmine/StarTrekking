package gameObjects;

import State.Context;
import gameObjects.entityState.EntityState;
import State.State;
import java.awt.image.BufferedImage;
import util.graphics.Animation;
import util.graphics.EntitySprite;
import util.EntityBox;
import util.Position;
import util.TileCollision;

/**
 *
 * @author StarTrekking
 *
 * Abstract class to represent each game object as an entity with animations,
 * position, collision, velocity, gravity
 */
public abstract class Entity implements GameObject, Context{

    protected Animation ani;
    protected EntitySprite sprite;
    protected Position pos;
    protected Position originPos;
    protected int size;

    protected EntityState state; //Key premuta

    protected float dx;
    protected float dy;

    protected float maxSpeed = 0.5f;
    protected float acc = 0.0003f;

    protected EntityBox bounds;
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

    protected boolean deadAniEnded = false;

    /**
     * Creates a general entity object.
     * @param sprite sprite of the entity.
     * @param origin spawn position.
     * @param size size of the enemy.
     */
    public Entity(EntitySprite sprite, Position origin, int size) {
        this.sprite = sprite;
        this.pos = origin;
        this.originPos = origin;
        this.size = size;
        this.dx0 = pos.getX();
        this.dy0 = pos.getY();
        this.previousY = dy0;
        this.previousX = dx0;
        this.tc = new TileCollision(this);
        this.ani = new Animation();

    }

    /**
     * Set the sprite of the entity.
     * @param sprite 
     */
    public void setSprite(EntitySprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Return entity's sprite.
     * @return entity's sprite.
     */
    public EntitySprite getSprite() {
        return sprite;
    }

    /**
     * Set the animation of the entity to dead
     * @param b 
     */
    public void setDeadAniEnded(boolean b) {
        deadAniEnded = b;
    }
    
    /**
     * Set the size of the entity
     * @param i 
     */
    public void setSize(int i) {
        size = i;
    }

    /**
     * Set the max speed of the entity
     * @param f 
     */
    public void setMaxSpeed(float f) {
        maxSpeed = f;
    }

    /**
     * Set the acceleration of the entity
     * @param f 
     */
    public void setAcc(float f) {
        acc = f;
    }

    /**
     * 
     * @return the size of the entity
     */
    public int getSize() {
        return size;
    }

    /**
     * 
     * @return the animation played by the entity
     */
    public Animation getAnimation() {
        return ani;
    }

    /**
     * 
     * @return the collision bounds of the entity
     */
    public EntityBox getBounds() {
        return bounds;
    }

    /**
     * Set the collision bounds of the entity
     * @param bounds 
     */
    public void setBounds(EntityBox bounds) {
        this.bounds = bounds;
    }

    /**
     * 
     * @return the position of the entity
     */
    public Position getPos() {
        return pos;
    }
    
    /**
     * Set the position of the entity
     * @param p 
     */
    public void setPos(Position p) {
        this.pos = p;
    }

    /**
     * 
     * @return true if the dead animation is ended
     */
    public boolean getDeadAniEnded() {
        return deadAniEnded;
    }

    /**
     * 
     * @return the current state of the entity
     */
    public State getState() {
        return state;
    }

    public float getTimey() {
        return timey;
    }

    public float getTimeX() {
        return timex;
    }

    public void setTimeX(float t) {
        this.timex = t;
    }

    public void setTimeY(float t) {
        this.timey = t;
    }

    /**
     * 
     * @return the vertical speed of the entity
     */
    public float getVy() {
        return vy;
    }

    /**
     * Set the vertical speed
     * @param v 
     */
    public void setVy(float v) {
        vy = v;
    }

    /**
     * Set the vertical movement
     * @param dy 
     */
    public void setDy(float dy) {
        this.dy = dy;
    }

    /**
     * Set the initial vertical movement
     * @param dy 
     */
    public void setDy0(float dy) {
        dy0 = dy;
    }

    /**
     * 
     * @return the gravity applied on the entity
     */
    public float getGravity() {
        return gravity;
    }

    /**
     * Set the gravity
     * @param g 
     */
    public void setGravity(float g) {
        gravity = g;
    }

    public TileCollision getTc() {
        return tc;
    }

    /**
     * 
     * @return the vertical movement
     */
    public float getDy() {
        return dy;
    }

    /**
     * 
     * @return the old vertical position
     */
    public float getPreviousY() {
        return previousY;
    }

    /**
     * Set the animation of the entity
     * @param frames
     * @param delay 
     */
    public void setAnimation(BufferedImage[] frames, int delay) {
        ani.setFrames(frames);
        ani.setDelay(delay);
    }

    /**
     * Update the game state and animation
     */
    @Override
    public void updateGame() {
        state.updateGame();
        ani.updateGame();
    }
    
    /**
     * Set the current state to a new state
     * @param st 
     */
    @Override
    public void setState(State st){
        state = (EntityState) st;
        st.set();
    }
}
