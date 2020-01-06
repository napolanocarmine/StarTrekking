/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects.entityState;

import gameObjects.Player;
import State.State;
import util.EntityEnum;

/**
 *
 * @author Demetrio
 */
public class PlayerCrouchState extends PlayerState{
    
    public PlayerCrouchState(Player p){
        super(p);
    }

    /*
        When the player change his state into crouch state, he will remain in the last animation frame till his state changes.
    */
    @Override
    public void updateGame() {
        super.updateGame();
        if (p.getAnimation().playingLastFrame()) {
            p.getAnimation().setDelay(-1);
        }
    }

    @Override
    public void nextState(State state) {
        p.setState(state);
    }

    @Override
    public void set() {
        p.setAnimation(p.getSprite().getSprite(EntityEnum.CROUCH), 80);
        p.setBounds(p.getCrouchBounds());
        p.getMg().setMusic("Crouch");
        p.getMg().play();
    }

}