/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import frames.SelectionLevelFrame;

/**
 *
 * @author Gianluca
 */
public class SelectionLevelState extends State {

    private GameStateManager gsm;

    public SelectionLevelState(GameStateManager gsm) {
        this.gsm = gsm;
        this.frame = new SelectionLevelFrame("/screen/forest.png", this);
    }

    /**
     * Called when "Level 1" or "Level 2" or "Level 3" is pressed, based on the
     * pressed button the next state is set.
     *
     * @param code button code
     */
    @Override
    public void handleNext(int code){
        if(code == 1){
           
            frame.dispose();
            gsm.setState(new StoryPlayState(gsm));
//            frame.dispose();
//            gsm.setState(new StoryPlayState(gsm));
        }else if(code == 2){
        }else{
            frame.dispose();
            gsm.setState((new MainMenuState(gsm)));
        }
    }

    //Non so se per il main menu va settato.
    @Override
    public void handlePrevious(int code) {
    }

}
