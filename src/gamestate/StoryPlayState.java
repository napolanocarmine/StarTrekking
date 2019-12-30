/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import panels.GamePanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Star Trekking
 *
 * Class to describe the state of the game during he story mode
 */
public class StoryPlayState extends GameState {

    private int level;
    private boolean pause;
    /**
     * Create the Panel on which the Level is run.
     */
    public StoryPlayState(GameStateManager gsm ) {
        this.panel = new GamePanel(this);
        this.gsm = gsm;
        pause = false;
    }

    /**
     * Method which handles the next state, that could be "Pause" or "Game-Over"
     *
     * @param code code represented the next state
     */
    @Override
    public void handleNext(int code) {
        if (code == 1) {
            gsm.setState(gsm.getGos());
        }
        if (code == 2) {
            gsm.setState(gsm.getPs());
        }
    }

    public void setLevel(int level){
        this.level = level;
    }
    
    public int getLevel(){
        return level;
    }
    
    @Override
    public void handlePrevious(int code) {
    }
    
    public synchronized void pause(){
        /*
        1) 
        2) settare variabile lock a true
        3) lock.notifyAll()
        4) handle next --> GSM stato di pausa.
        */
        ((GamePanel)panel).setPause(true);
        pause = true; //Sto in pausa;
        this.handleNext(2);
        notifyAll();
    }
    
    @Override 
    public void set(){
        //super.set();
        // Comunicate the level to the Panel;
        if(pause != true)
            ((GamePanel) panel).setLevel(level);
        
        // Init the thread;
        ((GamePanel) panel).startThread();
        // Run the thread;
        ((GamePanel) panel).setPause(false);
        pause = false;
    }

}
