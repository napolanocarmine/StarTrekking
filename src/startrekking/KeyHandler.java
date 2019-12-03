/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startrekking;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author CARMINE
 */
public class KeyHandler implements KeyListener {

    public KeyHandler() {
    }

    public KeyHandler(GamePanel game) {
        game.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
