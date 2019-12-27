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

    private State gameState;
    private GsmListener listener;
    private MusicGame mg = new MusicGame("MainMenu");

    /**
     * Create a new GameStateManager and initialize the initial state
     *
     * @param gameState Initial state.
     */
    public GameStateManager(State gameState) {
        this.listener = null;
        this.gameState = gameState;
        gameState.setGSM(this);
        setState(gameState);
    }

    /**
     * Create a new GameStateManager and initialize the initial state to
     * MainMenuState.
     */
    public GameStateManager() throws IOException {
        this.listener = null;
        this.gameState = new MainMenuState();
    }

    /**
     * Return the current game state.
     *
     * @return the current State.
     */
    public State getState() {
        return gameState;
    }

    /**
     * Set the current state to gameState.
     *
     * @param gameState represents the current state to be setted.
     */
    public void setState(State gameState) {
        this.gameState = gameState;

        if (getState() instanceof MainMenuState) {
            mg.stop();
            mg.setMusic("MainMenu");
            mg.play();
        }

        if (getState() instanceof StoryPlayState && StoryPlayState.level == 1) {
            mg.stop();
            mg.setMusic("LevelOne");
            mg.play();
        }

        if (getState() instanceof StoryPlayState && StoryPlayState.level == 2) {
            mg.stop();
            mg.setMusic("LevelTwo");
            mg.play();
        }

        if (getState() instanceof GameOverState) {
            mg.stop();
            mg.setMusic("MainMenu");
            mg.play();
        }

        if (listener != null) {
            gameState.setGSM(this);
            System.out.println(gameState);
            listener.stateChanged(gameState);
        }
    }

    public void setListener(GsmListener l) {
        this.listener = l;
    }

}
