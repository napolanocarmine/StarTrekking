package gameObjects.entityState;

import gameObjects.Player;
import util.EntityEnum;

public class PlayerJumpState extends PlayerState{
    
    public PlayerJumpState(Player p){
        super(p);
    }

    
    /*
    Computation of the gravity and of the vertical speed in order to let the player make a jump of constant distance and height, regardless of the horizontal speed.
    When the player isn't in the jump state anymore the standard values of gravity and vertical speed are reset.
    */
    @Override
    public void updateGame() {
        p.horizontalMove();
        if(p.getTimey() == 0 ){
            if(!(p.getFalling())) p.setVy(-(float)((4*p.getH()*p.getInstantVx())/p.getDIST()));
                p.setGravity(-(float)((p.getH()*8*Math.pow(p.getInstantVx(), 2))/Math.pow(p.getDIST(), 2)));
            }
        if (p.getAnimation().playingLastFrame()) {
            p.getAnimation().setDelay(-1);
        }
        
        if(!p.verticalMove()){
            p.setVy(0);            //definire come costante di player
            p.setGravity(-0.01f);  //definire come costante di player
            nextState(p.getPlayerRunState());
        }
        
    }

    @Override
    public void nextState(EntityState state) {
        p.setState(state);
    }

    @Override
    public void set() {
        p.setAnimation(p.getSprite().getSprite(EntityEnum.JUMP), 80);
        p.getMg().setMusic("Jump");
        p.getMg().play();
        p.setTimeY(0);
    }

}




