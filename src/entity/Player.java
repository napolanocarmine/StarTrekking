 package entity;

import graphics.Sprite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import startrekking.GamePanel;
import util.AABB;
import util.KeyHandler;
import util.Position;
import util.EntityState;
import util.TileCollision;

public class Player extends Entity implements Observer {

    private double jumpSpeed = 1.1;
    private double currentJumpSpeed = jumpSpeed;
    private KeyHandler khdl;
    int action;
    float landingY = pos.getY();
    private boolean falling = false;
    private ArrayList<Shot> shots = new ArrayList<Shot>();
    private float previousY;

    public Player(Sprite sprite, Position origin, int size, KeyHandler khdl) {
        super(sprite, origin, size, EntityState.RUN);
        this.bounds = new AABB(pos, 16, 32, 40, 32);
        this.khdl = khdl;
        previousY = pos.getY();
    }

    public void move() {
        dx += acc;  //Player Acceleration
        if (dx > maxSpeed) {
            dx = maxSpeed; //if the delta x is over the max we reset it
        }
        
        //System.out.println("check: " + pos.getY() + " - " +  previousY);
        if((int)pos.getY() > (int)previousY){
            System.out.println("if posy");
            state = EntityState.JUMP;
            falling = true;
        }
        previousY = pos.getY();
        

        if(state == EntityState.JUMP){
            if(ani.getFrame() == 3) ani.setDelay(-1);
            if(!falling){
                dy -= currentJumpSpeed;     //decrementation for jumping
                currentJumpSpeed -= .09;
                if(currentJumpSpeed<=0 || tc.collisionTileUp(0, dy)){
                  falling = true;
                }
            }else{
                System.out.println("FALLING");
                dy += currentJumpSpeed;
                currentJumpSpeed += .09;
                if(tc.collisionTileDown(0, dy)){
                    falling = false;
                    state = EntityState.RUN;
                    currentJumpSpeed = jumpSpeed;
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
        System.out.println("pos: " + pos.getX() + " - " + pos.getY());
        shots.add(new Shot(new Sprite("Entity/shot.png", 32, 32), new Position(96, pos.getY()+24), 48));
    }
    
    public void updateGame() {
        //System.out.println("y: " + pos.getY());
        move();//this update all the aspect of a player
        super.updateGame(state);     //e.g. movement, animation, position
        if(!tc.collisionTile(dx, 0)){
                pos.addX(dx);    //update x position
                GamePanel.getMapPos().addX(dx);
            }
        if(!tc.collisionTile(0, dy)){
            pos.addY(dy);  //update y position
        }
        //GamePanel.map.addY(dy);
        if(!shots.isEmpty()){
            for(int i=0; i<shots.size(); i++){
                shots.get(i).updateGame();
            }
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
            if ((key == 4) && (state != EntityState.JUMP) && (state != EntityState.NONE)
                    && (tc.collisionTileDown(0, dy) && !tc.collisionTileUp(0, dy)) && currentState == EntityState.RUN && b) {
                state = EntityState.JUMP;
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
