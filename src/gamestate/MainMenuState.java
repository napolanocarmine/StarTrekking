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
 * 
 * Class which represents the state in which the game shows the main menu.
 */
public class MainMenuState extends State{
    /**
     * Create the Main Menu.
     */
    public MainMenuState(){
        this.panel = new JPanel();
        //metodo che inizializza le componenti del JPanel;
        initComponent();
    }
    
    /**
     * Define the main menu's components.
     */
    private void initComponent(){
        
    }
    
    /**
     * Called when either "Story-Mode" or "Rush-Mode" is pressed, 
     * based on the pressed button the next state is set.
     * @param current_state 
     */
    @Override
     public void  handleNext(GameStateManager current_state){
         /*
            Codice che sulla base del bottone premuto setta il prossimo stato.
         */
     };
    
    //Non so se per il main menu va settato.
    @Override
    public void handlePrevious(GameStateManager current_state){};
}
