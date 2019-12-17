/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import frames.MainMenuFrame;
import javax.swing.*;

/**
 *
 * @author Star Trekking
 */
/**
 * 
 * Class which represents the state in which the game shows the main menu.
 */
public class MainMenuState extends State{
    GameStateManager gsm;
    /**
     * Create the Main Menu.
     */
    public MainMenuState(GameStateManager gsm){
        this.gsm = gsm;
        this.frame = new MainMenuFrame(this);
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
     * @param state button code
     */
    @Override
     public void  handleNext(int state){
         if(state == 0){
             frame.dispose();
             gsm.setState(new SelectionLevelState(gsm));
         }else if(state == 1){
             frame.dispose();
             gsm.setState(new ExitState(gsm));
         }
     };
    
    //Non so se per il main menu va settato.
    @Override
    public void handlePrevious(int code){};
}
