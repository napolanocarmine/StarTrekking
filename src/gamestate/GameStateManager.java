/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import State.Context;
import State.State;
import java.io.IOException;
import util.music.MusicGame;
import panels.*;

/**
 *
 * @author Star Trekking
 */
/**
 * Class which handles the game's state.
 */
public class GameStateManager implements Context {

    private GameState mms;
    private GameState sps;
    private GameState gos;
    private GameState ps;
    private GameState vs;
    private GameState sls;
    private GsmListener listener;
    private GameState currentState;
    private MusicGame mg;

    /**
     * Create a new GameStateManager and initialize the initial state
     *
     * @throws java.io.IOException if the panel corresponding to the state
     * raises the IO Exception.
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
    /**
     * It is used to get the Main Menu state.
     *
     * @return the main menu state.
     */
    public GameState getMms() {
        return mms;
    }

    /**
     * It is used to get the Story Play state.
     *
     * @return the story play state.
     */
    public GameState getSps() {
        return sps;
    }

    /**
     * It is used to get the Game Over state.
     *
     * @return the Game Over state.
     */
    public GameState getGos() {
        return gos;
    }

    /**
     * It is used to get Pause state.
     *
     * @return the Pause state.
     */
    public GameState getPs() {
        return ps;
    }

    /**
     * It is used to get the Victory state.
     *
     * @return the Victory state.
     */
    public GameState getVs() {
        return vs;
    }

    /**
     * It is used to get the Selection Level state.
     *
     * @return the Selection Level state.
     */
    public GameState getSls() {
        return sls;
    }

    /**
     * It is used to get the listner which handles the modification of the shown
     * panel.
     *
     * @return the listner which comunicates with game frame.
     */
    public GsmListener getListener() {
        return listener;
    }

    /**
     * Set the current state to gameState.
     *
     * @param gameState represents the current state to be setted.
     */
    @Override
    public void setState(State gameState) {
        currentState = (GameState) gameState;

        currentState.set();
        if (listener != null) {
            listener.stateChanged(currentState);
        }
    }

    /**
     * It is used to resume a previuos given game state.
     *
     * @param gameState is the state to be resumed.
     */
    public void resumeState(GameState gameState) {
        currentState = gameState;
        gameState.resume();
        this.currentState = gameState;
        if (listener != null) {
            listener.stateChanged(gameState);
        }
    }

    /**
     * It is used to comunicate to game frame to dispose the frame.
     */
    public void exit() {
        if (listener != null) {
            listener.exit();
        }
    }

    /**
     * It is used to set the listner.
     *
     * @param l is the listner to be setted.
     */
    public void setListener(GsmListener l) {
        this.listener = l;
    }

    /**
     * Getter method to the CurrentState.
     *
     * @return the current state of the game.
     */
    public GameState getCurrentState() {
        return currentState;
    }

    /**
     * It is used to set a MusicGame variable
     *
     * @param mg represents the MusicGame variable.
     */
    protected void setMusicGame(MusicGame mg) {
        this.mg = mg;
    }

    /**
     * It is used to get a MusicGame variable
     *
     * @return mg represents the MusicGame variable.
     */
    protected MusicGame getMusicGame() {
        return mg;
    }

    /**
     * It is used to set a .wav file in Clip object; Note: this methods calls
     * MusicGame.setMusic(String)
     *
     * @param name represents the name of the file .wav to be setted.
     */
    protected void setMusic(String name) {
        mg.setMusic(name);
    }

    /**
     * It is used to stop the music clip.
     */
    protected void stopMusic() {
        mg.stop();
    }

}
