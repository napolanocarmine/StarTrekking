/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeyHandler;

/**
 *
 * @author Star Trekking Company
 */
import entitycommand.AttackPlayerCommand;
import entitycommand.Command;
import entitycommand.PlayerCommandInvoker;
import entitycommand.CrouchPlayerCommand;
import entitycommand.JumpPlayerCommand;
import entitycommand.RunPlayerCommand;
import gameObjects.Player;
import gameObjects.entityState.PlayerDeadState;
import gamestate.StoryPlayState;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Class for catching and communicating the pressed keys
 */
public class KeyHandler implements KeyListener {

    private boolean pressed;//True if ctrl or space are pressed, otherwise false
    
    private ArrayList<KeyHandlerListener> listeners;
    
    
    /**
     * KeyHandler's constructor
     * @param player object that KeyHandler must manage;
     */
    public KeyHandler(StoryPlayState sps){
        listeners = new ArrayList<>();
        pressed = false;
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
            int code = ke.getKeyCode();
            if(code == 32 || code == 17 || code == 88 || code == 27) {
                for (KeyHandlerListener l : listeners){
                    l.buttPressed(code);
                }
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
        int code = ke.getKeyCode();
        if(code == 17) {
            for (KeyHandlerListener l : listeners){
                l.buttReleased(code);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }
    
    public void setListener(KeyHandlerListener l) {
        listeners.add(l);
    }

}
