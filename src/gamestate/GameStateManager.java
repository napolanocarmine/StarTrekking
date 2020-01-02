/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import java.io.IOException;
import music.MusicGame;
import panels.*;

/**
 *
 * @author Star Trekking
 */
/**
 * Class which handles the game's state.
 */
public class GameStateManager {

    private GameState mms;
    private StoryPlayState sps;
    private GameState gos;
    private GameState ps;
    private GameState vs;
    private GameState sls;
    private GameState es;
    private GsmListener listener;
    private GameState currentState;
    private MusicGame mg;

    /**
     * Create a new GameStateManager and initialize the initial state
     *
     * @param gameState Initial state.
     */
    public GameStateManager() throws IOException {
        this.listener = null;
        this.mg = new MusicGame();
        this.mms = new MainMenuState(this);
        this.sps = new StoryPlayState(this);
        this.gos = new GameOverState(this);
        this.ps = new PauseState(this);
        this.vs = new VictoryState(this);
        this.sls = new SelectionLevelState(this);
        this.es = new ExitState(this);
        this.currentState = mms;
        currentState.set();
    }

    /**
     * Create a new GameStateManager and initialize the initial state to
     * MainMenuState.
     */
    /*
    public GameStateManager() throws IOException {
        this.listener = null;
        this.gameState = new MainMenuState();
    }*/

    /**
     * Return the current game state.
     *
     * @return the current State.
     */
    /*
    public State getState() {
        return gameState;
    }*/

    public GameState getMms() {
        return mms;
    }

    public StoryPlayState getSps() {
        return sps;
    }

    public GameState getGos() {
        return gos;
    }

    public GameState getPs() {
        return ps;
    }

    public GameState getVs() {
        return vs;
    }

    public GameState getSls() {
        return sls;
    }

    public GsmListener getListener() {
        return listener;
    }

    public GameState getEs() {
        return es;
    }

    /**
     * Set the current state to gameState.
     *
     * @param gameState represents the current state to be setted.
     */
    public void setState(GameState gameState) {
        this.currentState = gameState;

        if (listener != null) {
            listener.stateChanged(gameState);
        }
    }

    public void setListener(GsmListener l) {
        this.listener = l;
    }

    public GameState getCurrentState() {
        return currentState;
    }
    
    /**
     * It is used to set a MusicGame variable
     *
     * @param mg represents the MusicGame variable.
     */    
    protected void setMusicGame(MusicGame mg){
        this.mg = mg;
    }
    
    /**
     * It is used to get a MusicGame variable
     *
     * @return mg represents the MusicGame variable.
     */
    protected MusicGame getMusicGame(){
        return mg;
    }
    
    /**
     * It is used to set a .wav file in Clip object;
     * Note: this methods calls MusicGame.setMusic(String)
     *
     * @param name represents the name of the file .wav to be setted.
     */
    protected void setMusic(String name){
        mg.setMusic(name);
    }

}
