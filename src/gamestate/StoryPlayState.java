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


    public void setLevel(int level){
        this.level = level;
    }
    
    public int getLevel(){
        return level;
    }
    
   
    
    public synchronized void pause(){
        /*
        1) 
        2) settare variabile lock a true
        3) lock.notifyAll()
        4) handle next --> GSM stato di pausa.
        */
        //((GamePanel)panel).setPause(true);
        ((GamePanel) panel).setPause(true);
        this.nextState(gsm.getPs());
    }
    
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

    @Override
    public void nextState(State state) {
        gsm.setState((GameState) state);
    }

    @Override
    public void updateGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
