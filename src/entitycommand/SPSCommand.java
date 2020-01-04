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
public abstract class SPSCommand implements Command{

    StoryPlayState sps;
    
    public void StoryPlayStateCommand(StoryPlayState sps){
        this.sps = sps;
    }
    
    @Override
    public abstract void execute();
    
}
