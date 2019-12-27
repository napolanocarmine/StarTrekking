package entitycommand;

import gameObjects.Player;
import gameObjects.entityState.PlayerJumpState;
import gameObjects.entityState.PlayerRunState;

/**
 *
 * @author StarTrekking
 *
 * Class to manage the jump command of the player
 */
public class JumpPlayerCommand extends PlayerCommand {

    public JumpPlayerCommand(Player player) {
        super(player);
    }

    /**
     * Execute the jump command.
     */
    @Override
    public void execute() {
        if (player.getState() instanceof PlayerRunState && player.getTc().collisionTileDown(0, 1)) {
            player.setState(new PlayerJumpState(player));
        }
    }

}
