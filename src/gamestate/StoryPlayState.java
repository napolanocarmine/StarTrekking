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
 * Class which represents the state in which the game shows the story mode.
 */
public class StoryPlayState extends GameState {

    private int level;
    private boolean pause;
    /**
     * Create the Panel on which the Level is run.
     * @param gsm is the manager of the game's state.
     */
    public StoryPlayState(GameStateManager gsm ) {
        this.panel = new GamePanel(this);
        this.gsm = gsm;
        pause = false;
    }

    /**
     *It is used to set the right level of the game. 
     * @param level is the number corresponding to the right level.
     */
    public void setLevel(int level){
        this.level = level;
    }
    
    /**
     *It is used to return the level.
     * @return 'level' corresponding to the right level.
     */
    public int getLevel(){
        return level;
    }
    
    /**
     *It is used to put in pause the Story Play state. 
     */
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
    
    /**
     *It is used to resume the game after the pause.
     */
    @Override
    public void resume(){
        //((GamePanel) panel).startThread();
        ((GamePanel)panel).setPause(false);
        //this.startMusic();
    }
    
    /**
     *It is used to comunicate to the Game Panel the behaviour corresponding 
     * to the game state.
     */
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

    /**
     *It is used to set the music based on the correpsonding level. 
     */
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

    /**
     *It is used to pass in the next state and to stop the corresponding music. 
     * @param state is the next state handles by gsm.
     */
    @Override
    public void nextState(State state) {
        gsm.setState((GameState) state);
    }

    /**
     *
     */
    @Override
    public void updateGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
