package entitycommand;

import gameObjects.Player;
import gameObjects.entityState.PlayerAttackState;
import gameObjects.entityState.PlayerRunState;

/**
 *
 * @author StarTrekkking
 *
 * Class that manages the attack action of the player
 */
public class AttackPlayerCommand extends PlayerCommand {

    public AttackPlayerCommand(Player player) {
        super(player);
    }

    /**
     * Execute the attack command.
     */
    @Override
    public void execute() {
        if (player.getState() instanceof PlayerRunState) {
            player.setState(new PlayerAttackState(player));
        }
    }

}
