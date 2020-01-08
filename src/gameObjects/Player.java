package gameObjects;

import gameObjects.entityState.*;
import util.graphics.EntitySprite;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import util.music.MusicGame;
import panels.GamePanel;
import static panels.GamePanel.unitTime;
import tiles.LayerFacade;
import util.EntityBox;
import util.Position;
import util.TileCollision;

/**
 * Represents the Player inside the game.
 * @author Star Trekking
 */
public class Player extends Entity{

    private final int MAXHEALTHPOINTS = 3;
    private final int MAXMANAPOINTS = 3;
    private int hp;
    private int mana;
    private float h = 100;
    private float dist = 150;
    
//    private KeyHandler khdl;
    int action;
    private ArrayList<Shot> shots = new ArrayList<Shot>();
    
    DecimalFormat df = new DecimalFormat();
    private boolean invincible;
    private float invStartTime;
    private float lastShotUsedTime;
    private boolean visible;
    private boolean falling = false;
    
    private float instantVx = 0;
    private boolean changeMotion = false;

    private MusicGame mg;
    private EntityBox standBounds;
    private EntityBox crouchBounds;
    
    private PlayerState runState;
    private PlayerState jumpState;
    private PlayerState crounchState;
    private PlayerState deadState;
    private PlayerState attackState;
    private PlayerState winState;
    
    /**
     * Creates an Player object.
     * @param sprite sprite associated with the Player
     * @param origin start position of the Player
     * @param size size of the Player
     */
    public Player(EntitySprite sprite, Position origin, int size) {
        super(sprite, origin, size);
        this.hp = MAXHEALTHPOINTS;
        this.mana = MAXMANAPOINTS;
        this.standBounds = new EntityBox(pos, 16, 32, 40, 32);
        this.crouchBounds = new EntityBox(pos, 16, 12, 40, 52);
        this.state = new PlayerRunState(this);
        df.setMaximumFractionDigits(2);
        this.initialSpeed = 0.3f;
        this.vx = initialSpeed;
        this.acc = 0.00002f;
        this.visible = true;
        this.invincible = false;
        mg = new MusicGame();
        
        runState = new PlayerRunState(this);
        jumpState = new PlayerJumpState(this);
        crounchState = new PlayerCrouchState(this);
        deadState = new PlayerDeadState(this);
        attackState = new PlayerAttackState(this);
        winState = new PlayerVictoryState(this);
        
        this.state = runState;
        state.set();
    }

    /**
     * This method update the coordinate of the player along the horizontal axis
     * @return true if collide during the movement otherwise false
     */
    public boolean horizontalMove() {
        //PLAYER HORIZONTAL MOTION
        
        /*
        The following 2 if statements determine the motion of the player, based on his instant speed.
        If it reaches the max speed, the motion change from linear accelerated motion to linear motion, so the player doesn't increase his speed anymore.
        */
        if(instantVx < maxSpeed){
            instantVx = vx + acc*(timex);
        }
        if(instantVx > maxSpeed -0.001 && instantVx < maxSpeed +0.001 && changeMotion == false){
            changeMotion = true;
            changeMotion();
        }
        
        /*
        Equation of the linear accelerated motion on the horizontal axis.
        */
        dx = (float)((0.5*acc*Math.pow(timex, 2) + vx*timex)) + dx0;
        //System.out.println("dx : "+ dx);
        /*
        Collision detection: if the player touches a solid tile during his motion he will stop moving.
        */
        if(tc.collisionTileObj(dx-previousX, 0)){
            dx = previousX;
            return false;
        } else {
            timex ++;
            previousX = dx;
            return true;
        }
    }
    
    /**
     * This method update the coordinate of the player along the vertical axis
     * @return true if collide during the movement otherwise false
     */
    public boolean verticalMove(){

        //PLAYER VERTICAL MOTION
        
        /*
        Equation of the linear accelerated motion on the vertical axis.
        */
        dy = (float)((-0.5*gravity*Math.pow(timey, 2)+vy*timey)+dy0);
        System.out.println("dy :"+dy);
        /*
        Collision detection: if the player is on the ground he will not fall, if he is jumping and he touches a solid tile above is head he will start to fall..
        */
        if(tc.collisionTileDown(0, dy-previousY) /*&& !(state instanceof PlayerJumpState)*/){
            dy = previousY;
            dy0 = dy;
            timey = 0;
            falling = false;
            return false;
        }else if(tc.collisionTileUp(0, dy-previousY)){
            dy = previousY;
            dy0 = dy;
            timey = 0;
            vy = 0;
            falling = true;
            return true;
        } else {
            previousY = dy;
            timey ++;
            return true;
        }
    }
    
    /**
     * this methods allows to change the motion change from linear accelerated motion to linear motion
     */
    public void changeMotion(){
        dx0 = previousX;
        timex = 0;
        acc = 0;
        vx = maxSpeed;
    }
    
    /**
     * This method change hp of the player ad if the player loses all hp he dies, so the player change the state in PlayerDeadState
     */
    public void hitted() {
        invincible = true;
        visible = false;
        invStartTime = System.nanoTime();
        if(--hp==0){
            state.nextState(this.getPlayerDeadState());
        }
    }

    /**
     * this method allows the player to shot if the mana are available
     */
    public void attack() {
        mana--;
        shots.add(new Shot(new EntitySprite("entity/shot", 32, 32), new Position(dx - 15, pos.getY() + 24), 48, vx + acc * (timex)));
    }
    
    /**
     * Getter of the shots of the player
     * @return ArrayList of Shot
     */
    public ArrayList<Shot> getShots(){ return shots; }

    /**
     * Getter of the falling status of the player
     * @return true o false
     */
    public boolean getFalling(){ return falling; }

    /**
     * Setter of the falling status of the player
     * @param b
     */
    public void setFalling(boolean b){ falling = b; }

    /**
     * Getter of the distance of the player
     * @return distance
     */
    public float getDist(){ return dist; }

    public float getDx() {
        return dx;
    }

    
    
    
    public void setTc(TileCollision tc){ this.tc = tc; }
    
    /**
     * Setter of the distance of the player
     * @param dist distance to set
     */
    public void setDist(float dist) {
        this.dist = dist;
    }

    /**
     * Getter of the H of the player
     * @return h
     */
    public float getH(){ return h; }

    /**
     * Setter of the player H
     * @param h to set
     */
    public void setH(float h){ this.h = h;}

    /**
     * Getter of the player's instant speed along x
     * @return instant speed along x
     */
    public float getInstantVx(){ return instantVx; }

    /**
     *  Getter of the Player's music game
     * @return Music Game
     */
    public MusicGame getMg(){ return mg; }

    /**
     * Getter of the player's Stand Bounds 
     * @return EntityBox
     */
    public EntityBox getStandBounds(){ return standBounds; }

    /**
     * Getter of the player's Crouch Bounds
     * @return EntityBox
     */
    public EntityBox getCrouchBounds(){ return crouchBounds; }

    /**
     * Getter of the player's Stand Bounds
     * @param bounds
     */
    public void setStandBounds(EntityBox bounds){ standBounds = bounds; }

    /**
     * Setter of the player's Crouch Bounds
     * @param bounds
     */
    public void setCrouchBounds(EntityBox bounds){ crouchBounds = bounds; }
    
    /**
     * Setter of the Player's Bounds
     * @param bounds are the bounds to set
     */
    @Override
    public void setBounds(EntityBox bounds){ this.bounds = bounds; }

    /**
     * Getter of the Player's HP
     * @return HP of the player
     */
    public int getHP() { return this.hp; }

    /**
     * Getter of the Player's Mana
     * @return Mana of the player
     */
    public int getMana() { return this.mana; }

    /**
     * This method return the play victory state of the player
     * @return state of victory
     */
    public boolean isWinner() { return state instanceof PlayerVictoryState; }
    
    /**
     * Getter of the PlayerRunState 
     * @return RunState
     */
    public PlayerState getPlayerRunState(){
        return this.runState;
    }
    
    /**
     * Getter of the PlayerAttackState
     * @return attackState
     */
    public PlayerState getPlayerAttackState(){
        return this.attackState;
    }
    
    /**
     * Getter of the PlayerJumpState
     * @return jumpState
     */
    public PlayerState getPlayerJumpState(){
        return this.jumpState;
    }
    
    /**
     * Getter of the PlayerCrounchState
     * @return crounchState
     */
    public PlayerState getPlayerCrounchState(){
        return this.crounchState;
    }
    
    /**
     * Getter of the PlayerDeadState
     * @return deadState
     */
    public PlayerState getPlayerDeadState(){
        return this.deadState;
    }
    
    /**
     * Getter of the PlayerVictoryState
     * @return winState
     */
    public PlayerState getPlayerVictoryState(){
        return this.winState;
    }
    
    /**
     * this method allows the delete the shot from the array
     * @param s the shot to remove
     */
    public void deleteShot(Shot s) {
        shots.remove(s);
    }
    
    /**
     * This method verify if the player hit an Obstacle
     * @return true if collide otherwise false
     */
    public boolean hitObs(){
        return tc.collisionTileObs(0, dy-previousY) || tc.collisionTileObs(dx-previousX, 0);
    }
    
    @Override
    public void updateGame(){
        super.updateGame();
        if(invincible){
            if(System.nanoTime()%9000 < 100 || System.nanoTime()%9000 > -100) visible = !visible;
            if(System.nanoTime() - invStartTime>= unitTime){
                invincible = false;
                visible = true;
            }
        }
        if(timex%1000 == 0){
            if(mana < MAXMANAPOINTS) mana++;
        }
                
        //move();
        pos.setX(dx);    //update x position
        if (Level.getMapPos().getX() + GamePanel.WIDTH < LayerFacade.getMapWidth() * 16) {
            Level.getMapPos().setX(dx);
        }
        pos.setY(dy);
        if(!shots.isEmpty()){
            for(int i=0; i<shots.size(); i++){
                if(shots.get(i).pos.getWorldVar().getX() - pos.getWorldVar().getX() > GamePanel.WIDTH || shots.get(i).collides()){
                    deleteShot(shots.get(i));
                }else {
                    shots.get(i).updateGame();
                }
            }
        }
        if(pos.getY() > GamePanel.HEIGHT && !(state instanceof PlayerDeadState)){
            state.nextState(this.getPlayerDeadState());
        }
        
        if((pos.getWorldVar().getX()) >= (LayerFacade.getMapWidth() - 30)){
            System.err.println("check: " + pos.getWorldVar().getX() + " check 2: " + (LayerFacade.getMapWidth() -100));
            System.err.println("VINTO");
            state.nextState(winState);
        }
    }

    @Override
    public void render(Graphics2D g) {  //draw the player in the panel
        if (visible == true) {
            g.drawImage(ani.getImage(), (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), size, size, null);
//            g.setColor(Color.blue);
//            g.drawRect((int) (pos.getWorldVar().getX() + bounds.getXOffset()), (int) (pos.getWorldVar().getY() + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
        }

        if (!shots.isEmpty()) {
            for (int i = 0; i < shots.size(); i++) {
                shots.get(i).render(g);
            }
        }
    }


}
