/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitycommand;

import gamestate.StoryPlayState;

/**
 *
 * @author Star Trekking
 */
public class SPSCommandInvoker implements KeyHandler.KeyHandlerListener{

    StoryPlayState sps;
    
    
    public SPSCommandInvoker(StoryPlayState sps){
        this.sps = sps;
    }
    
    @Override
    public void buttPressed(int code) {
        if(code == 27){
            System.out.println("HAI PREMUTO ESC");
            sps.nextState(sps.getGSM().getPs());
        }
    }

    @Override
    public void buttReleased(int code) {}
    
}
