/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import startrekking.Window;

/**
 *
 * @author Star Trekking
 */
/**
 * Class which handles the game's state.
 */
public class GameStateManager {
    private State gameState;
    private Window window;
    /**
     * Create a new GameStateManager and initialize the initial state
     * @param window window that is managed by the manager
     * @param gameState Initial state.
     */
    public GameStateManager(Window window,State gameState){
        this.gameState = gameState;
        this.window = window;
    }
    /**
     * Create a new GameStateManager and initialize the initial state to
     * MainMenuState.
     * @param window window that is managed by the manager
     */
    public GameStateManager(Window window){
        this.gameState = new MainMenuState(this);
        this.window = window;
    }
    
    /**
     * Return the current game state.
     * @return the current State.
     */
    public State getState(){
        return gameState;
    }
    
    /**
     *  Set the current state to gameState.
     * @param gameState represents the current state to be setted.
     */
    public void setState(State gameState){
        this.gameState = gameState;
        this.window.setPanel(gameState.getPanel());
    }
    
}
