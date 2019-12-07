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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

/**
 * Class for catching and communicating the pressed keys
 */
public class KeyHandler extends Observable implements KeyListener {

    private EntityState key;
    private boolean released;

    /**
     * KeyHandler's constructor
     */
    public KeyHandler() {
        super();
        released = false;
        key = EntityState.NONE;
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
     * Return true if key has been released, otherwise false
     *
     * @return
     */
    public boolean isPressed() {
        return released;
    }

    /**
     * Notify that something is changed
     */
    private void stateChanged() {
        setChanged();
        notifyObservers();
    }

    /**
     * Assign to key variable the pressed key, among the valid key
     *
     * @param ke
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_SPACE:                        //space -> 32
                key = EntityState.JUMP;
                released = false;
                stateChanged();
                break;
            case KeyEvent.VK_CONTROL:                     //control -> 17
                key = EntityState.CRUNCH;
                released = false;
                stateChanged();
                break;
            case KeyEvent.VK_X:                          //x -> 88
                key = EntityState.ATTACK;
                released = false;
                stateChanged();
                break;
            default:
                key = EntityState.NONE;
                System.out.println("Unknown Key");
        }
        System.out.println(ke.getKeyCode());
    }

    /**
     * Set to true the released variable
     *
     * @param ke
     */
    @Override
    public void keyReleased(KeyEvent ke) {
        released = true;
        stateChanged();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

}
