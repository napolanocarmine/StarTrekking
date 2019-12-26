package entitycommand;

import gameObjects.Player;
import gameObjects.entityState.PlayerAttackState;
import gameObjects.entityState.PlayerCrouchState;
import gameObjects.entityState.PlayerRunState;

public class CrouchPlayerCommand extends PlayerCommand{
    
    public CrouchPlayerCommand(Player player) {
        super(player);
    }
    
    /**
     * Execute the attack command.
     */
    @Override
    public void execute(){
        if(player.getState() instanceof PlayerRunState || player.getState() instanceof PlayerCrouchState)
            player.setState(new PlayerCrouchState(player));
    }
    
}