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
 * Abstract class that represents the command to give to the player
 */
public class PauseCommand extends SPSCommand {

    /**
     * Constructs a RunPlayerCommand.
     * @param state player affected by the command.
     */
    public PauseCommand(StoryPlayState state) {
        super(state);
    }

    /**
     * Execute the attack command.
     */
    @Override
    public void execute() {
        ((GamePanel)sps.getPanel()).setPause(true);
        sps.nextState(sps.getGSM().getPs());
    }

}
