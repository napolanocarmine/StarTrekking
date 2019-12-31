package gameObjects;

import gameObjects.entityState.EntityState;
import java.awt.image.BufferedImage;
import graphics.Animation;
import graphics.EntitySprite;
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
public abstract class Entity implements GameObject {

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

    public void setDeadAniEnded(boolean b) {
        deadAniEnded = b;
    }

    public void setSize(int i) {
        size = i;
    }

    public void setMaxSpeed(float f) {
        maxSpeed = f;
    }

    public void setAcc(float f) {
        acc = f;
    }

    public int getSize() {
        return size;
    }

    public Animation getAnimation() {
        return ani;
    }

    public EntityBox getBounds() {
        return bounds;
    }

    public void setBounds(EntityBox bounds) {
        this.bounds = bounds;
    }

    public Position getPos() {
        return pos;
    }

    public boolean getDeadAniEnded() {
        return deadAniEnded;
    }

    public EntityState getState() {
        return state;
    }

    public void setState(EntityState state) {
        this.state = state;
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

    public float getVy() {
        return vy;
    }

    public void setVy(float v) {
        vy = v;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public void setDy0(float dy) {
        dy0 = dy;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float g) {
        gravity = g;
    }

    public TileCollision getTc() {
        return tc;
    }

    public float getDy() {
        return dy;
    }

    public float getPreviousY() {
        return previousY;
    }

    public void setAnimation(BufferedImage[] frames, int delay) {
        ani.setFrames(frames);
        ani.setDelay(delay);
    }

    @Override
    public void updateGame() {
        state.updateGame();
        ani.updateGame();
    }
}
