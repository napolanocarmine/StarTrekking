/* @author: StarTrekkingCompany
 *
 *
 */
package entity;

//import graphics.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import graphics.Animation;
import graphics.Sprite;
import util.AABB;
import util.Position;
import util.EntityState;
import util.TileCollision;

public abstract class Entity {
    
    protected Animation ani;
    protected Sprite sprite;
    protected Position pos;
    protected int size;

    protected EntityState state; //Key premuta
    protected EntityState currentState; //Animazione Corrente

    protected float dx = 0;
    protected float dy = 0;

    protected float maxSpeed = 20f;
    protected float acc = 0.0003f;
    protected float deacc = 0.3f;
    
    protected AABB bounds;
    protected TileCollision tc;
    
    protected int aniDelay = 80;

    //protected AABB hitBounds;
    //protected AABB bounds;
    //protected TileCollision tc;
    public Entity(Sprite sprite, Position origin, int size, EntityState state) {
        this.sprite = sprite;
        pos = origin;
        this.size = size;
        this.ani = new Animation(sprite.getSpriteArray(state.ordinal()));
        this.ani.setDelay(aniDelay);
        this.state = state;
        this.currentState = state;
        tc = new TileCollision(this);
    }
    
    public Entity(Sprite sprite, Position origin, int size) {
        this.sprite = sprite;
        pos = origin;
        this.size = size;
        this.ani = new Animation(sprite.getSpriteArray(0));
    }

    public void setSprite(Sprite sprite) { this.sprite = sprite; }
    public void setDead() { state = EntityState.DEAD; }
    public void setSize(int i) { size = i; }
    public void setMaxSpeed(float f) { maxSpeed = f; }
    public void setAcc(float f) { acc = f; }
    public void setDeAcc(float f) { deacc = f; }
    public int getSize() { return size; }
    public Animation getAnimation() { return ani; }
    public AABB getBounds(){ return bounds; }
    
    public void setAnimation(EntityState state, BufferedImage[] frames, int delay) {
        this.state = state;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }

    //Setta l'animazione sulla base dello stato;
    public void animate(EntityState state) {
        if (state != this.currentState) {
            this.currentState = state;
            setAnimation(state, sprite.getSpriteArray(state.ordinal()), aniDelay);
        }
    }

    public void updateGame(EntityState state) {
        animate(state);
        ani.updateGame();
    }
    
    public void updateGame(){
        ani.updateGame();
    }

    public abstract void render(Graphics2D g);
}
