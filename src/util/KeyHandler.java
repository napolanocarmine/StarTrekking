/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Star Trekking Company
 */
import entitycommand.AttackPlayerCommand;
import entitycommand.Command;
import entitycommand.CommandInvoker;
import entitycommand.CrouchPlayerCommand;
import entitycommand.JumpPlayerCommand;
import entitycommand.RunPlayerCommand;
import gameObjects.Player;
import gameObjects.entityState.PlayerDeadState;
import gamestate.StoryPlayState;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class for catching and communicating the pressed keys
 */
public class KeyHandler implements KeyListener {

    private boolean pressed;//True if ctrl or space are pressed, otherwise false
    
    private Player player;
    private Command jumpPlayerC;
    private Command crouchPlayerC;
    private Command attackPlayerC;
    private Command runPlayerC;
    private CommandInvoker cmdInvoker;
    private StoryPlayState sps;
    
    public KeyHandler(StoryPlayState sps){
        this.cmdInvoker = new CommandInvoker();
        this.sps = sps;
        pressed = false;
    }
    
    /**
     * KeyHandler's constructor
     * @param player object that KeyHandler must manage;
     */
    public KeyHandler(Player player, StoryPlayState sps) {
        this.player = player;
        this.jumpPlayerC = new JumpPlayerCommand(player);
        this.crouchPlayerC = new CrouchPlayerCommand(player);
        this.attackPlayerC = new AttackPlayerCommand(player);
        this.runPlayerC = new RunPlayerCommand(player);
        this.cmdInvoker = new CommandInvoker();
        this.sps = sps;
        pressed = false;

    }

    /**
     * Receiver setter
     * @param player 
     */
    public void setPlayer(Player player){
        this.player = player;
        this.jumpPlayerC = new JumpPlayerCommand(player);
        this.crouchPlayerC = new CrouchPlayerCommand(player);
        this.attackPlayerC = new AttackPlayerCommand(player);
        this.runPlayerC = new RunPlayerCommand(player);
        this.cmdInvoker = new CommandInvoker();
    }
    
    

    /**
     * Return true if key is pressed, otherwise false
     *
     * @return
     */
    public boolean isPressed() {
        return pressed;
    }


    /**
     * Assign to key variable the pressed key, among the valid key
     *
     * @param ke
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        if (!pressed) {
            switch (ke.getKeyCode()) {
                case 32:                        //space -> 32
//                    System.err.println("Jump");
                    this.cmdInvoker.setCommand(jumpPlayerC);
                    pressed = true;
                    break;
                case 17://KeyEvent.VK_CONTROL:                     //control -> 17
                    //System.out.println("Crouch");
                    this.cmdInvoker.setCommand(crouchPlayerC);
                    pressed = true;
                    break;
                case 88://KeyEvent.VK_X:                          //x -> 88
                    //System.out.println("Attack");
                    this.cmdInvoker.setCommand(attackPlayerC);
                    pressed = true;
                    break;
                case 27:   //esc -> 27
                    sps.pause();
                    pressed = true;
                    break;
                default:
            }
        }
    }

    /**
     * Set to true the released variable
     *
     * @param ke
     */
    @Override
    public void keyReleased(KeyEvent ke) {
        pressed = false;
        if (ke.getKeyCode() == 17) {
            this.cmdInvoker.setCommand(runPlayerC);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

}
