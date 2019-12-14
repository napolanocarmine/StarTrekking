 package entity;

import graphics.Sprite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import startrekking.GamePanel;
import tiles.TileFacade;
import util.AABB;
import util.KeyHandler;
import util.Position;
import util.EntityState;
import util.TileCollision;
 
public class Player extends Entity implements Observer {

    private final int MAXHEALTHPOINTS = 3;
    private double jumpSpeed;
    private double currentJumpSpeed;
    private double jumpSpeedDecrement = .09;
    private KeyHandler khdl;
    int action;
    float landingY = pos.getY();
    private boolean falling = false;
    private ArrayList<Shot> shots = new ArrayList<Shot>();
    private int previousY;
    private int previousX;
    private float initialX;
    private float initialY;
    private float initialSpeed = 4;
    DecimalFormat df = new DecimalFormat();

    private int hp ;
    
    public Player(Sprite sprite, Position origin, int size, KeyHandler khdl) {
        super(sprite, origin, size, EntityState.RUN);
        this.hp = this.MAXHEALTHPOINTS;
        this.bounds = new AABB(pos, 16, 32, 40, 32);
        this.khdl = khdl;
        previousY = (int)pos.getY();
        previousX = (int)pos.getX();
        this.dx = initialSpeed;
        df.setMaximumFractionDigits(2);
    }
    
    public int getHP(){
        return this.hp;
    }
    public  EntityState getState(){
        return this.state;
    }
    
    public void move() {
        
        if((int)pos.getX() > (int)previousX){
            dx += acc;  //Player Acceleration
            if (dx > maxSpeed) {
                dx = maxSpeed; //if the delta x is over the max we reset it
            }    
        }
        previousX = (int)pos.getY();
        
        //System.out.println("check: " + pos.getY() + " - " +  previousY);
        if((int)pos.getY() > (int)(previousY) && state != EntityState.JUMP){
            //System.out.println("current Y: " + (int)pos.getY() + ", prevoius Y: " + (int)previousY);
            state = EntityState.JUMP;
            falling = true;
            initializeJump();
        }
        previousY = (int)pos.getY();
        

        if(state == EntityState.JUMP){
            if(ani.getFrame() == 3) ani.setDelay(-1);
            if(!falling){
                if(currentJumpSpeed<=0){
                    dy = 0;
                    System.err.println("FINE1");
                    currentJumpSpeed = 0;
                    falling = true;
                }
                if(tc.collisionTileUp(0, dy)){
                    dy = 0;
                    System.err.println("FINE2");
                    currentJumpSpeed = 0;
                    falling = true;
                }
                dy -= currentJumpSpeed;     //9
                currentJumpSpeed -= jumpSpeedDecrement; //3
            }else{
                //System.err.println("SCENDENDO");
                dy += currentJumpSpeed;
                currentJumpSpeed += jumpSpeedDecrement;
                if(currentJumpSpeed >= jumpSpeed){
                    currentJumpSpeed = jumpSpeed;
                }
                if(tc.collisionTileDown(0, dy)){
                    currentJumpSpeed = jumpSpeed;
                    falling = false;
                    state = EntityState.RUN;
                }
            }
        }else if(state == EntityState.ATTACK){
            if(/*ani.hasPlayed(1)*/ ani.getFrame() == 3){
                attack();
                state = EntityState.RUN;
            }
        }else if(state == EntityState.CRUNCH){
            if(ani.getFrame() == 1) ani.setDelay(-1);
        }else dy=1;
    }

    private void attack(){
        shots.add(new Shot(new Sprite("Entity/shot.png", 32, 32), new Position(96, pos.getY()+24), 48));
    }
    
    private void restartPlayer(){
        pos.setPos(0 + 32, 0 + (GamePanel.HEIGHT) - 130);
        GamePanel.getMapPos().setPos(0, 0);
        dx = initialSpeed;
    }
    
    public void initializeJump(){
        initialX = pos.getX();
        initialY = pos.getY();
        int h = 10;
        int dist = 14;
//        currentJumpSpeed = (dx * 2 * h)/dist;
//        jumpSpeedDecrement = ((Math.pow(dx, 2))*2*h)/(Math.pow(dist, 2));
        currentJumpSpeed = 1.3;
        jumpSpeedDecrement = .09;
        jumpSpeed = currentJumpSpeed;
        dy = 0;
    }
    
    public void updateGame() {
        //System.err.println("yt: " + ((int)(pos.getY()/16)+1) );
        //System.out.println("x: " + pos.getX() + "y: " + pos.getY());
        move();//this update all the aspect of a player
        super.updateGame(state);     //e.g. movement, animation, position
        if(!tc.collisionTile(dx, 0)){
                pos.addX(dx);    //update x position
                if(GamePanel.getMapPos().getX()+GamePanel.WIDTH < TileFacade.mapWidth * 16){
                    GamePanel.getMapPos().addX(dx);
                }
        }
//        if(!tc.collisionTileDown3(0, dy) || (tc.collisionTileDown3(0, dy) && state == EntityState.JUMP)){
//            pos.addY(dy);  //update y position
//        }
        if(!tc.collisionTile(0, dy)){
            pos.addY(dy);  //update y position
        }
        //GamePanel.map.addY(dy);
        if(!shots.isEmpty()){
            for(int i=0; i<shots.size(); i++){
                shots.get(i).updateGame();
            }
        }
        //System.out.println(pos.getY() + " - " + GamePanel.HEIGHT);
        if(pos.getY() > GamePanel.HEIGHT){
            restartPlayer();
        }
    }

    @Override
    public void render(Graphics2D g) {  //draw the player in the panel
        g.drawImage(ani.getImage(), (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), size, size, null);
        g.setColor(Color.blue);
        g.drawRect((int) (pos.getWorldVar().getX()  + bounds.getXOffset()), (int) (pos.getWorldVar().getY() + bounds.getYOffset()), (int)bounds.getWidth(), (int)bounds.getHeight());
       
                
        if(!shots.isEmpty()){        }
                
        if(!shots.isEmpty()){
            for(int i=0; i<shots.size(); i++){
                shots.get(i).render(g);
            }
        }
    }

    private void mapValueAction(int key, boolean b) {
        if (true) { //in case the player is alive
            if ((key == 4) && (state != EntityState.JUMP) && (state != EntityState.NONE)
                    && (tc.collisionTileDown2(0, dy) && !tc.collisionTileUp(0, dy)) && currentState == EntityState.RUN && b) {
                state = EntityState.JUMP;
                initializeJump();
                //jumpSpeedDecrement = (dx*jumpSpeed)/100;
                //System.out.println("INIZIO SALTO");
                //System.out.println("check: " + df.format(currentJumpSpeed) + " - " + df.format(jumpSpeedDecrement) + ", tick: " + (currentJumpSpeed/jumpSpeedDecrement));
            }else if(key == 5 && b && currentState == EntityState.RUN){
                state = EntityState.CRUNCH;
            }else if(key == 5 && !b ){
                state = EntityState.RUN;
            }else if (key == 3 && currentState == EntityState.RUN) {
                state = EntityState.ATTACK;
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
}
