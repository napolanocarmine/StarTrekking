/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects.entityState;

import gameObjects.Player;
import util.EntityEnum;

/**
 *
 * @author Demetrio
 */
public class PlayerCrouchState extends PlayerState{
    
    public PlayerCrouchState(Player p){
        super(p);
        p.setAnimation(p.getSprite().getSprite(EntityEnum.CROUCH), 80);
        p.getMg().setMusic("Crouch");
        p.getMg().play();
        p.setBounds(p.getCrouchBounds());
    }

    /*
        When the player change his state into crouch state, he will remain in the last animation frame till his state changes.
    */
    @Override
    public void updateGame() {
        p.horizontalMove();
        p.verticalMove();
        if (p.getAnimation().playingLastFrame()) {
            p.getAnimation().setDelay(-1);
        }
    }

    @Override
    public void nextState(int code) {
        
    }

    @Override
    public void previousState(int code) {
    
    }
}