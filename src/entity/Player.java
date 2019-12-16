 package entity;

import graphics.Sprite;
import graphics.EntitySprite;
import java.awt.Color;
import java.awt.Graphics2D;
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
    private int hp;
    
    private KeyHandler khdl;
    int action;
    float landingY = pos.getY();
    private ArrayList<Shot> shots = new ArrayList<Shot>();
    private float previousY;
    private float previousX;
    private float initialX = 32;
    private float initialY = ((GamePanel.HEIGHT) - 130);
    DecimalFormat df = new DecimalFormat();
    
    
    private float gravity = -0.5f;
    private float y0 = initialY;
    private float h = 100;
    private float dist = 150;
    private boolean falling = false;
    
    private float startingY;
    private float startingX;

    public Player(EntitySprite sprite, Position origin, int size, KeyHandler khdl) {
        super(sprite, origin, size, EntityState.RUN);
        this.hp = MAXHEALTHPOINTS;
        this.bounds = new AABB(pos, 16, 32, 40, 32);
        this.khdl = khdl;
        previousY = (int)pos.getY();
        previousX = (int)pos.getX();
        df.setMaximumFractionDigits(2);
        //this.acc = 0.00015f;
        this.acc = 0.00003f;
        this.initialSpeed = 0.3f;
        this.vx = initialSpeed;
    }

    public void move() {
        
        //PLAYER HORIZONTAL MOTION
        if(timex == 0) vx = initialSpeed;
        dx = (float)((0.5*acc*Math.pow(timex, 2) + vx*timex));
        //dx = (float)((vx*timex));
        if(tc.collisionTile(dx-previousX, 0)){
            //System.err.println("collision front");
            dx = previousX;
        }else{
            timex+= 1f;
            previousX = dx;
        }
        
        //PLAYER VERTICAL MOTION
        
        if(state == EntityState.JUMP){
            if(timey == 0){
                float vx0 = vx + acc*(timex);
                if(!falling) vy = -(float)((4*h*vx0)/dist);
                gravity = -(float)((h*8*Math.pow(vx0, 2))/Math.pow(dist, 2));
                
//                System.err.println("dy: " + dy);
//                System.err.println("gravity: " + gravity);
            }
            if(ani.playingLastFrame()) ani.setDelay(-1);
        }else{
            vy = 0;
            gravity = -0.01f;
        }
        
        dy = (float)((-0.5*gravity*Math.pow(timey, 2)+vy*timey)+y0);
        if(tc.collisionTileDown(0, dy-previousY)){
            //System.err.println("collision down");
            dy = previousY;
            falling = false;
            timey = 0;
            y0 = previousY;
            if(state != EntityState.ATTACK) state = EntityState.RUN;
        }else if(tc.collisionTileUp(0, dy-previousY)){
            dy = previousY;
            y0 = previousY;
            falling = true;
            vy = 0;
        }else{
            timey+=1f;
            previousY = dy;
        }
        
        if(state == EntityState.ATTACK){
            if(ani.playingLastFrame()){
                attack();
                state = EntityState.RUN;
            }
        }
        
    }

    private void attack(){
        shots.add(new Shot(new EntitySprite("Entity/shot", 32, 32), new Position(dx-15, pos.getY()+24), 48, vx+acc*(timex)));
    }
    
    private void restartPlayer(){
        System.err.println("restart");
        pos.setPos(0 + 32, 0 + (GamePanel.HEIGHT) - 130);
        GamePanel.getMapPos().setPos(0, 0);
        timex = 0;
        timey = 0;
        //previousX = initialX;
        y0 = initialY;
        state = EntityState.RUN;
    }
    
    public ArrayList<Shot> getShots(){
        return shots;
    }
    
    public void deleteShot(Shot s){
        shots.remove(s);
    }
    
    public void updateGame(){
        move();
        super.updateGame(state);
        pos.setX(dx);    //update x position
        if(GamePanel.getMapPos().getX()+GamePanel.WIDTH < TileFacade.mapWidth * 16){
            GamePanel.getMapPos().setX(dx);
        }
        pos.setY(dy);
        if(!shots.isEmpty()){
            for(int i=0; i<shots.size(); i++){
                if(shots.get(i).pos.getWorldVar().getX() - pos.getWorldVar().getX() > GamePanel.WIDTH ||
                        shots.get(i).collides()){
                    deleteShot(shots.get(i));
                }else{
                    shots.get(i).updateGame();
                }
            }
        }
        if(pos.getY() > GamePanel.HEIGHT){
            restartPlayer();
        }
    }

    @Override
    public void render(Graphics2D g) {  //draw the player in the panel
        g.drawImage(ani.getImage(), (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), size, size, null);
        g.setColor(Color.blue);
        g.drawRect((int) (pos.getWorldVar().getX()  + bounds.getXOffset()), (int) (pos.getWorldVar().getY() + bounds.getYOffset()), (int)bounds.getWidth(), (int)bounds.getHeight());
        if(!shots.isEmpty()){
            for(int i=0; i<shots.size(); i++){
                shots.get(i).render(g);
            }
        }
    }

    private void mapValueAction(int key, boolean b) {
        if (true) { //in case the player is alive
            if ((key == 4) && (state != EntityState.JUMP) &&
                    (!tc.collisionTileDown(0, dy-previousY)) && currentState == EntityState.RUN && b) {
                state = EntityState.JUMP;
                startingY = pos.getY();
                startingX = pos.getX();
                timey = 0;
                //initializeJump();
                //jumpSpeedDecrement = (dx*jumpSpeed)/100;
                //System.out.println("INIZIO SALTO");
                //System.out.println("check: " + df.format(currentJumpSpeed) + " - " + df.format(jumpSpeedDecrement) + ", tick: " + (currentJumpSpeed/jumpSpeedDecrement));
            }else if (key == 3 && currentState == EntityState.RUN) {
                state = EntityState.ATTACK;
            }/*else if(key == 5 && b && currentState == EntityState.RUN){
                state = EntityState.CRUNCH;
            }else if(key == 5 && !b ){
                state = EntityState.RUN;
            }*/
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
    
    public int getHP(){
        return this.hp;
    }
}
