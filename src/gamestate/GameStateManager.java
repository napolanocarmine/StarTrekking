/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import java.io.IOException;
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

    /**
     * Create a new GameStateManager and initialize the initial state
     *
     * @param gameState Initial state.
     */
    public GameStateManager() throws IOException {
        this.listener = null;
        this.mms = new MainMenuState(this);
        this.sps = new StoryPlayState(1,this);
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

        /*if (getCurrentState() instanceof MainMenuState) {
            mg.stop();
            mg.setMusic("MainMenu");
            mg.play();
        }

        if (getCurrentState() instanceof StoryPlayState && StoryPlayState.level == 1) {
            mg.stop();
            mg.setMusic("LevelOne");
            mg.play();
        }

        if (getCurrentState()instanceof StoryPlayState && StoryPlayState.level == 2) {
            mg.stop();
            mg.setMusic("LevelTwo");
            mg.play();
        }

        if (getCurrentState() instanceof GameOverState) {
            mg.stop();
            mg.setMusic("MainMenu");
            mg.play();
        }*/

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

}
