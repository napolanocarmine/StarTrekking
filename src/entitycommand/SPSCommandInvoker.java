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
    Command setPauseState;
    
    /**
     * Default constructor of SPSCommandInvoker class.
     * @param sps the play state
     */
    public SPSCommandInvoker(StoryPlayState sps){
        this.sps = sps;
        this.setPauseState = new PauseCommand(sps);
    }
    /**
     * Pause the game if input param is related to pause button
     * @param code related to button pressed
     */
    @Override
    public void buttPressed(int code) {
        if(code == 27){
            setPauseState.execute();
        }
    }
    /**
     *  Do nothing
     * @param code related to button released
     */
    @Override
    public void buttReleased(int code) {}
    
}
