/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandTest;

import entitycommand.AttackPlayerCommand;
import entitycommand.PlayerCommandInvoker;
import entitycommand.CrouchPlayerCommand;
import entitycommand.JumpPlayerCommand;
import entitycommand.RunPlayerCommand;
import gameObjects.Level1;
import gameObjects.Player;
import gameObjects.entityState.PlayerAttackState;
import gameObjects.entityState.PlayerCrouchState;
import gameObjects.entityState.PlayerJumpState;
import gameObjects.entityState.PlayerRunState;
import org.junit.*;
import static org.junit.Assert.*;


/**
 * Class for testing the command classes.
 * @author Star Trekking
 */
public class CommandTest {
    private PlayerCommandInvoker invoker;
    private Level1 level1;
    
    @Before
    public void setUp(){
       level1.getPlayer().setState((level1.getPlayer().getPlayerRunState()));
    }
   
    @After
    public void clear(){
    }
    /**
     * Test method for attack command
     */
    @Test
    public void testAttackCommand(){
        invoker.buttPressed(88);
        assertTrue("The state set by the command is AttackState", level1.getPlayer().getState() instanceof PlayerAttackState);
    }
    /**
     * Test method for jump command
     */
    @Test
    public void testJumpCommand(){
        invoker.buttPressed(32);
        assertTrue("The state set by the command is JumpState", level1.getPlayer().getState() instanceof PlayerJumpState);
    }
    /**
     * Test method for crouch command
     */
    @Test
    public void testCrounchCommand() {
        invoker.buttPressed(17);
        assertTrue("The state set by the command is CrounchState", level1.getPlayer().getState() instanceof PlayerCrouchState);
    }
    /**
     * method for testing if player crouches while jumping or attacking
     */
    @Test
    public void testCrounchWhileNotRun(){
        level1.getPlayer().setState(level1.getPlayer().getPlayerJumpState());
        invoker.buttPressed(17);
        assertFalse("Crunch state has not been set", level1.getPlayer().getState() instanceof PlayerCrouchState);
        
        level1.getPlayer().setState(level1.getPlayer().getPlayerAttackState());
        invoker.buttPressed(17);
        assertFalse("Crunch state has not been set", level1.getPlayer().getState() instanceof PlayerCrouchState);
    }
    /**
     * Test method for testing if player attacks while running or attacking crouching
     */
    @Test
    public void testAttackWhileNotRun() {
        level1.getPlayer().setState(level1.getPlayer().getPlayerJumpState());
        invoker.buttPressed(88);
        assertFalse("Attack state has not been set", level1.getPlayer().getState() instanceof PlayerAttackState);

        level1.getPlayer().setState(level1.getPlayer().getPlayerCrounchState());
        invoker.buttPressed(88);
        assertFalse("Attack state has not been set", level1.getPlayer().getState() instanceof  PlayerAttackState);
    }
}
