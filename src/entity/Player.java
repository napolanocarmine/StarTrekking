package entity;

import graphics.Sprite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;
import util.KeyHandler;
import util.Position;
import util.EntityState;

public class Player extends Entity implements Observer{
    
    private double jumpSpeed = 5;
    private double currentJumpSpeed =jumpSpeed;
    private KeyHandler khdl;
    
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
        
        float landingY = pos.getY();    //start of y position
        if(state == EntityState.JUMP)   //case of jump
            dy -= currentJumpSpeed;     //decrementation for jumping
            currentJumpSpeed -= .1;     //gravity
            if(currentJumpSpeed <= 0){
                dy += currentJumpSpeed; //incrementation for falling
                currentJumpSpeed += .1; //gravity
                if(pos.getY() == landingY){         //after the falling we
                    dy = 0;                         //reset the delta
                    currentJumpSpeed = jumpSpeed;   //reset currentHumpSpeed
            }
        }
    }
    
    public void updateGame(){   //this update all the aspect of a player
        super.updateGame();     //e.g. movement, animation, position
        move();
        float x = pos.getX();   //update x position
        x += dx;
        pos.setX(x);
        float y = pos.getY();   //update y position
        y += dy;
        pos.setY(y);
    }
    
    @Override
    public void render(Graphics2D g) {  //draw the player in the panel
        g.drawImage(ani.getImage(), 32, 32, size, size, null);
    }
    
    public void mapValueAction(int key, boolean b){
        
        if(true){ //in case the player is alive
            if(key == 1){
                state = EntityState.JUMP;
            }else if (b== false){
                state = EntityState.RUN;
            }
            if(key == 2){
                state = EntityState.CRUNCH;
            }else if(b == false){
                state = EntityState.RUN;
            }
            if(key == 3){
                state = EntityState.ATTACK;
            }else if(b == false){
                state = EntityState.RUN;
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
