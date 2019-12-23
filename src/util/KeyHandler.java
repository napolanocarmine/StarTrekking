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
import entitycommand.Command;
import entitycommand.CommandInvoker;
import entitycommand.JumpPlayerCommand;
import gameObjects.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class for catching and communicating the pressed keys
 */
public class KeyHandler implements KeyListener {

    private EntityState key;
    private boolean pressed;//True if ctrl or space are pressed, otherwise false
    private int currentKey;
    
    private Player player;
    private Command jumpPlayerC;
    private CommandInvoker cmdInvoker;
    
    public KeyHandler(){
        this.cmdInvoker = new CommandInvoker();
    }
    
    /**
     * KeyHandler's constructor
     * @param player object that KeyHandler must manage;
     */
    public KeyHandler(Player player) {
        this.player = player;
        this.jumpPlayerC = new JumpPlayerCommand(player);
        this.cmdInvoker = new CommandInvoker();
//        pressed = false;
//        key = EntityState.NONE;
//        currentKey = -1;
    }

    /**
     * Receiver setter
     * @param player 
     */
    public void setPlayer(Player player){
        this.player = player;
        this.jumpPlayerC = new JumpPlayerCommand(player);
        this.cmdInvoker = new CommandInvoker();
    }
    
    /**
     * Return the key value
     *
     * @return
     */
    public int getValue() {
        return key.ordinal();
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
                    System.out.println("Jump");
                    this.cmdInvoker.setCommand(jumpPlayerC);
//                    key = EntityState.JUMP;
//                    currentKey = 32;
//                    pressed = true;
                    break;
                case 17://KeyEvent.VK_CONTROL:                     //control -> 17
                    key = EntityState.CROUCH;
                    pressed = true;
                    currentKey = 17;
                    break;
                case 88://KeyEvent.VK_X:                          //x -> 88
                    key = EntityState.ATTACK;
                    pressed = true;
                    currentKey = 88;
                    break;
                default:
                    key = EntityState.NONE;
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
        if (currentKey == ke.getKeyCode()) {
            pressed = false;
            currentKey = -1;
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

}
