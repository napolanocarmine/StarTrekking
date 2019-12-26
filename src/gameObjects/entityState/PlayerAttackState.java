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
public class PlayerAttackState extends PlayerState{
    
    public PlayerAttackState(Player p){
        super(p);
        p.setAnimation(p.getSprite().getSprite(EntityEnum.ATTACK), 80);
        p.getMg().setMusic("Shot");
        p.getMg().play();
    }

    /*
    When the player change his state into attack state, he will perform all the attack animation and then he will fire a shot.
    */
    @Override
    public void updateGame() {
        p.horizontalMove();
        p.verticalMove();
        if (p.getAnimation().playingLastFrame()) {
            p.attack();
            nextState(1);
        }
    }

    @Override
    public void nextState(int code) {
        switch(code){
            case 0:
                break;
            case 1:
                p.setState(new PlayerRunState(p));
        }
        
    }

    @Override
    public void previousState(int code) {
    
    }
}