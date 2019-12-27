package entitycommand;

import gameObjects.Player;
import gameObjects.entityState.PlayerRunState;

/**
 *
 * @author StarTrekking
 *
 * Class representing the run command for the player
 */
public class RunPlayerCommand extends PlayerCommand {

    public RunPlayerCommand(Player player) {
        super(player);
    }

    /**
     * Execute the attack command.
     */
    @Override
    public void execute() {
        player.setState(new PlayerRunState(player));
    }

}
