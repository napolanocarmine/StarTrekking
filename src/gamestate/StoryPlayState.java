/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import panels.GamePanel;

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
        //((GamePanel) panel).stopThread();
        if (code == 1) {
            this.stopMusic();
            gsm.setState(gsm.getGos());
        }else if (code == 2) {
            ((GamePanel)panel).setPause(true);
            gsm.setState(gsm.getPs());
        }else if (code == 3) {
            this.stopMusic();
            VictoryState vs = (VictoryState) gsm.getVs();
            vs.setLevel(level);
            gsm.setState(vs);
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
    
//    public synchronized void pause(){
//        /*
//        1) 
//        2) settare variabile lock a true
//        3) lock.notifyAll()
//        4) handle next --> GSM stato di pausa.
//        */
//        //((GamePanel)panel).setPause(true);
//        this.handleNext(2);
//    }
    
    @Override
    public void resume(){
        //((GamePanel) panel).startThread();
        ((GamePanel)panel).setPause(false);
        //this.startMusic();
    }
    
    @Override 
    public void set(){
        //super.set();
        // Comunicate the level to the Panel;
        ((GamePanel) panel).setLevel(level);
        
        ((GamePanel)panel).setPause(false);
        // Init the thread;
        ((GamePanel) panel).startThread();
        
        System.err.println("THREAD STARTED");
        // Run the thread;
        System.err.println("PAUSE CHANGED");
        this.startMusic();
    }

    @Override
    public void startMusic(){
        if(level == 1){    
            gsm.setMusic("LevelOne");
            gsm.getMusicGame().play();
        }else if(level == 2){    
            gsm.setMusic("LevelTwo");
            gsm.getMusicGame().play();
        }else if(level == 3){    
            gsm.setMusic("LevelThree");
            gsm.getMusicGame().play();
        }
    }
    
}
