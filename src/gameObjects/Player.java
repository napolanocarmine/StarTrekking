package gameObjects;

import gameObjects.entityState.*;
import graphics.Animation;
import graphics.EntitySprite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import music.MusicGame;
import panels.GamePanel;
import static panels.GamePanel.unitTime;
import tiles.LayerFacade;
import util.EntityBox;
import util.Position;

public class Player extends Entity{

    private final int MAXHEALTHPOINTS = 3;
    private int hp;
    private float h = 100;
    private float dist = 150;
    
//    private KeyHandler khdl;
    int action;
    private ArrayList<Shot> shots = new ArrayList<Shot>();
    
    DecimalFormat df = new DecimalFormat();
    private boolean invincible;
    private float invStartTime;
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
    
    public Player(EntitySprite sprite, Position origin, int size) {
        super(sprite, origin, size);
        this.hp = MAXHEALTHPOINTS;
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
    
    public boolean verticalMove(){

        //PLAYER VERTICAL MOTION
        
        /*
        Equation of the linear accelerated motion on the vertical axis.
        */
        dy = (float)((-0.5*gravity*Math.pow(timey, 2)+vy*timey)+dy0);
        
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
    
    public void changeMotion(){
        dx0 = previousX;
        timex = 0;
        acc = 0;
        vx = maxSpeed;
    }
    
    public void hitted() {
        invincible = true;
        visible = false;
        invStartTime = System.nanoTime();
        if(--hp==0){
            state.nextState(this.getPlayerDeadState());
        }
    }

    public void attack() {
        shots.add(new Shot(new EntitySprite("entity/shot", 32, 32), new Position(dx - 15, pos.getY() + 24), 48, vx + acc * (timex)));
    }
    
    public ArrayList<Shot> getShots(){ return shots; }
    public boolean getFalling(){ return falling; }
    public void setFalling(boolean b){ falling = b; }
    public float getDist(){ return dist; }
    public void setDist(float dist){ this.dist = dist; }
    public float getH(){ return h; }
    public void setH(float h){ this.h = h;}
    public float getInstantVx(){ return instantVx; }
    public MusicGame getMg(){ return mg; }
    public EntityBox getStandBounds(){ return standBounds; }
    public EntityBox getCrouchBounds(){ return crouchBounds; }
    public void setStandBounds(EntityBox bounds){ standBounds = bounds; }
    public void setCrouchBounds(EntityBox bounds){ crouchBounds = bounds; }
    public void setBounds(EntityBox bounds){ this.bounds = bounds; }
    public int getHP() { return this.hp; }
    public boolean isWinner() { return state instanceof PlayerVictoryState; }
    
    public PlayerState getPlayerRunState(){
        return this.runState;
    }
    
    public PlayerState getPlayerAttackState(){
        return this.attackState;
    }
    
    public PlayerState getPlayerJumpState(){
        return this.jumpState;
    }
    
    public PlayerState getPlayerCrounchState(){
        return this.crounchState;
    }
    
    public PlayerState getPlayerDeadState(){
        return this.deadState;
    }
    
    public PlayerState getPlayerVictoryState(){
        return this.winState;
    }
    
    public void deleteShot(Shot s) {
        shots.remove(s);
    }
    
    public boolean hitObs(){
        if(tc.collisionTileObs(0, dy-previousY) || tc.collisionTileObs(dx-previousX, 0)) return true;
        return false;
    }
    
    public void updateGame(){
        super.updateGame();
        if(invincible){
            if(System.nanoTime()%9000 < 100 || System.nanoTime()%9000 > 100) visible = !visible;
            if(System.nanoTime() - invStartTime>= unitTime){
                invincible = false;
                visible = true;
            }
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
        
        if(pos.getWorldVar().getX() >= (LayerFacade.mapWidth-100)){
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

    
    @Override
    public void setState(EntityState st){
        super.setState(st);
        st.set();
        //st.playMusic();
    }

}
