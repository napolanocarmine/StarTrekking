/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitycommand;

import gamestate.StoryPlayState;
import panels.GamePanel;

/**
 *
 * @author Star Trekking
 * 
 * implements KeyHandlerListener
 */
public class SPSCommandInvoker implements KeyHandler.KeyHandlerListener{

    StoryPlayState sps;
    
    /**
     * Default constructor of SPSCommandInvoker class.
     * @param sps 
     */
    public SPSCommandInvoker(StoryPlayState sps){
        this.sps = sps;
    }
    /**
     * Pause the game if input param is related to pause button
     * @param code 
     */
    @Override
    public void buttPressed(int code) {
        if(code == 27){
            ((GamePanel)sps.getPanel()).setPause(true);
            sps.nextState(sps.getGSM().getPs());
        }
    }
    /**
     *  Do nothing
     * @param code 
     */
    @Override
    public void buttReleased(int code) {}
    
}
