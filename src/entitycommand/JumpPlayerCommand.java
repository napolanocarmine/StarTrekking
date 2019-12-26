package entitycommand;

import gameObjects.Player;
import gameObjects.entityState.PlayerJumpState;
import gameObjects.entityState.PlayerRunState;

public class JumpPlayerCommand extends PlayerCommand{
    
//    private JumpPlayerState jump; 
    
    public JumpPlayerCommand(Player player) {
        super(player);
//        this.jump = new JumpPlayerState();
    }
    
    /**
     * Execute the jump command.
     */
    @Override
    public void execute(){
        if(player.getState() instanceof PlayerRunState && player.getTc().collisionTileDown(0, 1))
            player.setState(new PlayerJumpState(player));
    }
    
}
