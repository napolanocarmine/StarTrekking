package entitycommand;

import gameObjects.Player;
import gameObjects.entityState.PlayerAttackState;
import gameObjects.entityState.PlayerRunState;

public class RunPlayerCommand extends PlayerCommand{
    
    public RunPlayerCommand(Player player) {
        super(player);
    }
    
    /**
     * Execute the attack command.
     */
    @Override
    public void execute(){
        player.setState(new PlayerRunState(player));
    }
    
}