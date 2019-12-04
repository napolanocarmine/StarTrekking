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
import util.Position;
import util.KeyHandler;
import util.EntityState;

public abstract class Entity {
    
    protected final int RUN = 0;
    protected final int JUMP = 1;  //position of the row in the frames's png image  in which there will be the UP animation
    protected final int CRUNCH = 2;
    protected final int ATTACK = 3;
    protected final int DEAD = 4;
    protected int currentAnimation;
    
    protected Animation ani;
    protected Sprite sprite;
    protected Position pos;
    protected int size;
    
    //State
    protected boolean jump;
    protected boolean run;
    protected boolean attack;
    protected boolean dead;
    protected boolean crunch;
    protected EntityState state;
    
    protected float dx = 0;
    protected float dy = 0;
    
    protected float maxSpeed = 3f;
    protected float acc = 2f;
    protected float deacc = 0.3f;
    
    //protected AABB hitBounds;
    //protected AABB bounds;
    
    //protected TileCollision tc;
    
    public Entity(Sprite sprite, Position origin, int size){
        this.sprite = sprite;
        pos = origin;
        this.size = size;
        
        ani = new Animation();
        setAnimation(RUN, sprite.getSpriteArray(RUN), 10);
    }
    
    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }
    
    public void setDead(){
        state = EntityState.DEAD;
    }
    public void setSize(int i){ size = i; }
    public void setMaxSpeed(float f){ maxSpeed = f;}
    public void setAcc(float f){ acc = f;}
    public void setDeAcc(float f){ deacc = f; }
    
    public int getSize(){ return size; }
    public Animation getAnimation(){ return ani; }
    
    public void setAnimation(int i, BufferedImage[] frames, int delay){
        currentAnimation  = i;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }
    
    public void animate(int action){
        if(action == 1){
            if(currentAnimation != JUMP || ani.getDelay() == -1){
                setAnimation(JUMP, sprite.getSpriteArray(JUMP), 5);
            }
        }
        else if(action == 0){
            if(currentAnimation != RUN || ani.getDelay() == -1){
                setAnimation(RUN, sprite.getSpriteArray(RUN), 5);
            }
        }
        else if(dead){
            if(currentAnimation != DEAD || ani.getDelay() == -1){
                setAnimation(DEAD, sprite.getSpriteArray(DEAD), 5);
            }
        }
        
        else if(crunch){
            if(currentAnimation != CRUNCH || ani.getDelay() == -1){
                setAnimation(CRUNCH, sprite.getSpriteArray(CRUNCH), 5);
            }
        }
        else if(attack){
            if(currentAnimation != ATTACK || ani.getDelay() == -1){
                setAnimation(ATTACK, sprite.getSpriteArray(ATTACK), 5);
            }
        }else {
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }
    }
        
    public void updateGame(int action){
        animate(action);
        ani.updateGame();
    }
    
    public abstract void render(Graphics2D g);
    public void mapValueAction(int key, boolean b){};
}
