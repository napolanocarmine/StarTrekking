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
    private boolean pressed;//True if ctrl or space are pressed, otherwise false
    private int currentKey;

    /**
     * KeyHandler's constructor
     */
    public KeyHandler() {
        super();
        pressed = false;
        key = EntityState.NONE;
        currentKey = -1;
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
        if (!pressed) {
            switch (ke.getKeyCode()) {
                case 32://KeyEvent.VK_SPACE:                        //space -> 32
                    key = EntityState.JUMP;
                    currentKey = 32;
                    pressed = true;
                    stateChanged();
                    break;
                case 17://KeyEvent.VK_CONTROL:                     //control -> 17
                    key = EntityState.CRUNCH;
                    pressed = true;
                    currentKey = 17;
                    stateChanged();
                    break;
                case 88://KeyEvent.VK_X:                          //x -> 88
                    key = EntityState.ATTACK;
                    pressed = true;
                    currentKey = 88;
                    stateChanged();
                    break;
                default:
                    key = EntityState.NONE;
                    System.out.println("Unknown Key");
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
            stateChanged();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

}
