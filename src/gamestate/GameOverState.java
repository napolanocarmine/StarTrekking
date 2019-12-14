/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import javax.swing.JPanel;

/**
 *
 * @author Star Trekking
 */
/**
 * Class which represents the state in which the game shows the Game Over Menu.
 */
public class GameOverState extends State{
     /**
     * Create the Panel, which represents the Game Over Menu.
     */
    public GameOverState(){
        this.panel = new JPanel();
        //metodo che inizializza le componenti del JPanel;
        initComponent();
    }
    
    /**
     * Define the menu's components.
     */
    private void initComponent(){
        
    }
    
    /**
     * Method which handles the next state, that could be "Play" or "Main Menu"
     * @param current_state 
     */
    @Override
     public void  handleNext(GameStateManager current_state){
         /*
            codice che gestisce il prossimo stato,
         */
     };
    
    //Non so se per il game over va settato.
    @Override
    public void handlePrevious(GameStateManager current_state){};
}
