package entitycommand;

import gameObjects.Player;
import gameObjects.entityState.PlayerCrouchState;
import gameObjects.entityState.PlayerRunState;

/**
 *
 * @author StarTrekking
 *
 * Class which manages the command of crouching the player
 */
public class CrouchPlayerCommand extends PlayerCommand {

    public CrouchPlayerCommand(Player player) {
        super(player);
    }

    /**
     * Execute the attack command.
     */
    @Override
    public void execute() {
        if (player.getState() instanceof PlayerRunState || player.getState() instanceof PlayerCrouchState) {
            player.setState(new PlayerCrouchState(player));
        }
    }

}
