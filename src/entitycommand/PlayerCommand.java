/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitycommand;

import gameObjects.Player;

/**
 *
 * @author Star Trekking
 *
 * Abstract class that represents the command to give to the player
 */
public abstract class PlayerCommand implements Command {

    protected Player player;

    /**
     * Create a PlayerCommand object that has a reference to the Receiver.
     *
     * @param player
     */
    public PlayerCommand(Player player/*, PlayerState playerState*/) {
        this.player = player;
    }

    /**
     * Method that executes the command, represented the setting of the State.
     */
    @Override
    public void execute() {
    }

    /**
     * Player getter
     *
     * @return the receiver of the commands.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Allow to set the receiver.
     *
     * @param player receiver of the commands.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

}
