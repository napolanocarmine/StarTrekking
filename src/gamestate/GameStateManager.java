/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;


/**
 *
 * @author Star Trekking
 */
/**
 * Class which handles the game's state.
 */
public class GameStateManager {
    private State gameState;
    /**
     * Create a new GameStateManager and initialize the initial state
     * @param gameState Initial state.
     */
    public GameStateManager(State gameState){
        this.gameState = gameState;
    }
    /**
     * Create a new GameStateManager and initialize the initial state to
     * MainMenuState.
     */
    public GameStateManager(){
        this.gameState = new MainMenuState(this);
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
    }
    
}
