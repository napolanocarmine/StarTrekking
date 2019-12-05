package entity;

import graphics.Sprite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;
import startrekking.GamePanel;
import util.KeyHandler;
import util.Position;
import util.EntityState;

public class Player extends Entity implements Observer{
    
    private double jumpSpeed = 1;
    private double currentJumpSpeed = jumpSpeed;
    private KeyHandler khdl;
    int action;
    float landingY = pos.getY();    //start of y position
    
    public Player(Sprite sprite, Position origin, int size, KeyHandler khdl) {
        super(sprite, origin, size);
        this.khdl = khdl;
        this.khdl.addObserver(this);
    }
    
    public void move(){
        dx += acc;  //Player Acceleration
        if(dx > maxSpeed){
            dx = maxSpeed; //if the delta x is over the max we reset it
        }
        
        if(state == EntityState.JUMP){   //case of jump
            dy -= currentJumpSpeed;     //decrementation for jumping
            currentJumpSpeed -= .09;     //gravity
            if(currentJumpSpeed <= 0){
                state = EntityState.NONE;
            }
        }
        
        if(state == EntityState.NONE){
            dy += currentJumpSpeed;
            if(currentJumpSpeed < jumpSpeed){
                currentJumpSpeed += .09;
            }
            if(currentJumpSpeed >= jumpSpeed){
                currentJumpSpeed = jumpSpeed;   //reset currentHumpSpeed
            }  
            if(landingY <= pos.getY()){         //after the falling we
                pos.setY(landingY);
                dy = 0;                         //reset the delta
                state = EntityState.RUN;
            }
        }
    }
    
    public void updateGame(){   //this update all the aspect of a player
        super.updateGame(action);     //e.g. movement, animation, position
        move();
        pos.addX(dx);    //update x position
        pos.addY(dy);  //update y position
        GamePanel.getMapPos().addX(dx);  //RIVEDERE, NON PULITO DAL PUNTO DI VISTA DEL CODICE POICHE' map E' DICHIARATA COME POSITION STATICA
        //GamePanel.map.addY(dy);
    }
    
    @Override
    public void render(Graphics2D g) {  //draw the player in the panel
        g.drawImage(ani.getImage(), (int)pos.getWorldVar().getX(), (int)pos.getWorldVar().getY(), size, size, null);
    }
    
    public void mapValueAction(int key, boolean b){
        if(true){ //in case the player is alive
            if((key == 1) && !(state == EntityState.JUMP) && !(state == EntityState.NONE)){
                action = 1;
                state = EntityState.JUMP;
            }
            if(key == 2){
                state = EntityState.CRUNCH;
            }
            if(key == 3){
                state = EntityState.ATTACK;
            }
        }else{
            state = EntityState.DEAD;
        }
        
    }

    @Override
    public void update(Observable o, Object s) {
        if(o == this.khdl){
            int key = this.khdl.getValue();
            boolean b = khdl.isPressed();
            mapValueAction(key, b);
        }
    }
}
