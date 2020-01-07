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
 * Represent the AttackState of the Player
 * @author Star Trekking
 */
public class PlayerAttackState extends PlayerState{
    
    /**
     * Constructor of the Player Attack State
     * @param p
     */
    public PlayerAttackState(Player p){
        super(p);
    }

    /*
    When the player change his state into attack state, he will perform all the attack animation and then he will fire a shot.
    */
    @Override
    public void updateGame() {
        super.updateGame();
        if (p.getAnimation().playingLastFrame()) {
            p.attack();
            nextState(p.getPlayerRunState());
        }
    }

    @Override
    public void nextState(State state) {
        p.setState(state);
    }

    @Override
    public void set() {
        p.setAnimation(p.getSprite().getSprite(EntityEnum.ATTACK), 30);
        p.getMg().setMusic("Shot");
        p.getMg().play();
    }
    
}
