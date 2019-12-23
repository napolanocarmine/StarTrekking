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
 */
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
        System.out.println("Jumping");
//        player.setState(jump);
    }
    
}
