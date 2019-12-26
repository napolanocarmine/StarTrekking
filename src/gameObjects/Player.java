package gameObjects;

import graphics.EntitySprite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import panels.GamePanel;
import static panels.GamePanel.unitTime;
import tiles.TileFacade;
import util.AABB;
import util.KeyHandler;
import util.Position;
import util.EntityState;

import music.MusicGame;

public class Player extends Entity implements Observer {

    private final int MAXHEALTHPOINTS = 3;
    private final float H = 100;
    private final float DIST = 150;
    private int hp;
    
    private KeyHandler khdl;
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
    private AABB standBounds;
    private AABB crouchBounds;
    
    public Player(EntitySprite sprite, Position origin, int size) {
        super(sprite, origin, size, EntityState.RUN);
        this.hp = MAXHEALTHPOINTS;
        this.standBounds = new AABB(pos, 16, 32, 40, 32);
        this.crouchBounds = new AABB(pos, 16, 12, 40, 52);
        this.bounds = this.standBounds;
        df.setMaximumFractionDigits(2);
        this.initialSpeed = 0.3f;
        this.vx = initialSpeed;
        this.acc = 0.00002f;
        this.visible = true;
        this.invincible = false;
        
        mg = new MusicGame();
    }

    public void move() {
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
        } else {
            if (state != EntityState.DEAD) {
                timex += 1f;
            }
            previousX = dx;
        }

        //PLAYER VERTICAL MOTION
        
        /*
        Computation of the gravity and of the vertical speed in order to let the player make a jump of constant distance and height, regardless of the horizontal speed.
        When the player isn't in the jump state anymore the standard values of gravity and vertical speed are reset.
        */
        if(state == EntityState.JUMP){
            if(timey == 0){
                if(!falling) vy = -(float)((4*H*instantVx)/DIST);
                gravity = -(float)((H*8*Math.pow(instantVx, 2))/Math.pow(DIST, 2));
            }
            if (ani.playingLastFrame()) {
                ani.setDelay(-1);
            }
        } else {
            vy = 0;
            gravity = -0.01f;
        }
        
        /*
        Equation of the linear accelerated motion on the vertical axis.
        */
        dy = (float)((-0.5*gravity*Math.pow(timey, 2)+vy*timey)+dy0);
        
        /*
        Collision detection: if the player is on the ground he will not fall, if he is jumping and he touches a solid tile above is head he will start to fall..
        */
        if(tc.collisionTileDown(0, dy-previousY)){
            //System.err.println("collision down");
            dy = previousY;
            timey = 0;
            dy0 = dy;
            falling = false;
            if(state == EntityState.JUMP) state = EntityState.RUN;
        }else if(tc.collisionTileUp(0, dy-previousY)){
            dy = previousY;
            dy0 = previousY;
            vy = 0;
            timey = 0;
            falling = true;
        } else {
            timey += 1f;
            previousY = dy;
        }

        /*
        When the player change his state into attack state, he will perform all the attack animation and then he will fire a shot.
        */
        if (state == EntityState.ATTACK) {
            if (ani.playingLastFrame()) {
                attack();
                state = EntityState.RUN;
            }
        }

        /*
        When the player change his state into dead state, he will perform all the dead animation and then he will set himself to dead.
        */
        if (state == EntityState.DEAD) {
            isDead();
        }
        
        /*
        When the player change his state into crouch state, he will remain in the last animation frame till his state changes.
        */
        if (state == EntityState.CROUCH) {
            if (ani.playingLastFrame()) {
                ani.setDelay(-1);
            }
        }

    }
    
    public void changeMotion(){
        dx0 = previousX;
        timex = 0;
        acc = 0;
        vx = maxSpeed;
    }
    
    public void setBounds(AABB box){
        this.standBounds = box;
        this.crouchBounds = new AABB(pos, 16, 12, 40, 44);                       //new AABB(pos, 16, 24, 40, 32);
        this.bounds = this.standBounds;
    }
    
    public void isDead(){
        if(ani.playingLastFrame()){
            dead = true;
        }
    }

    public void hitted() {
        invincible = true;
        visible = false;
        invStartTime = System.nanoTime();
        if(--hp==0){
            mg.setMusic("GameOver");
            mg.play();
            isDead();
            state = EntityState.DEAD;
        }
    }

    private void attack() {
        shots.add(new Shot(new EntitySprite("entity/shot", 32, 32), new Position(dx - 15, pos.getY() + 24), 48, vx + acc * (timex)));
    }
    
    public ArrayList<Shot> getShots(){ return shots; }
    public void setKeyHandler(KeyHandler k){ this.khdl = k; }

    public void deleteShot(Shot s) {
        shots.remove(s);
    }
    
    public boolean hitObs(){
        if(tc.collisionTileObs(0, dy-previousY) || tc.collisionTileObs(dx-previousX, 0)) return true;
        return false;
    }
    
    public void updateGame(){
        super.updateGame(state);
//        if(tc.collisionTileObs(0, dy-previousY) || tc.collisionTileObs(dx-previousX, 0)){
//            hitted();
//        }
        if(invincible){
            if(System.nanoTime()%9000 < 100 || System.nanoTime()%9000 > 100) visible = !visible;
            if(System.nanoTime() - invStartTime>= unitTime){
                invincible = false;
                visible = true;
            }
        }
        move();
        pos.setX(dx);    //update x position
        if (Level.getMapPos().getX() + GamePanel.WIDTH < TileFacade.mapWidth * 16) {
            Level.getMapPos().setX(dx);
        }
        pos.setY(dy);
        if(!shots.isEmpty()){
            for(int i=0; i<shots.size(); i++){
                if(shots.get(i).pos.getWorldVar().getX() - pos.getWorldVar().getX() > GamePanel.WIDTH || shots.get(i).collides()){
                    deleteShot(shots.get(i));
                } else {
                    shots.get(i).updateGame();
                }
            }
        }
        if(pos.getY() > GamePanel.HEIGHT && state != EntityState.DEAD){
            //dead = true; 
            hp = 1;
            hitted();
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

    private void mapValueAction(int key, boolean b) {
        if (true) { //in case the player is alive
            if ((key == 4) && state == EntityState.RUN && tc.collisionTileDown(0, 1)) {
                state = EntityState.JUMP;
                mg.setMusic("Jump");
                mg.play();
                timey = 0;
            }
            if (key == 3 && currentState == EntityState.RUN) {
                state = EntityState.ATTACK;
                mg.setMusic("Shot");
                mg.play();
            }
            if(key == 5 && (state == EntityState.RUN || state == EntityState.CROUCH)){
                state = EntityState.CROUCH;
                mg.setMusic("Crouch");
                mg.play();
                this.bounds = this.crouchBounds;
                if(!b){
                    this.bounds = this.standBounds;
                    state = EntityState.RUN;
                }
            } 
            
        } else {
            state = EntityState.DEAD;
        }

    }

    @Override
    public void update(Observable o, Object s) {
        if (o == this.khdl) {
            int key = this.khdl.getValue();
            boolean b = khdl.isPressed();
            mapValueAction(key, b);
        }
    }

    public int getHP() {
        return this.hp;
    }
}
