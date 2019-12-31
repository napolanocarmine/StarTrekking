/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandTest;

import entitycommand.AttackPlayerCommand;
import entitycommand.CommandInvoker;
import entitycommand.CrouchPlayerCommand;
import entitycommand.JumpPlayerCommand;
import entitycommand.RunPlayerCommand;
import gameObjects.Level1;
import gameObjects.Player;
import gameObjects.entityState.PlayerAttackState;
import gameObjects.entityState.PlayerCrouchState;
import gameObjects.entityState.PlayerDeadState;
import gameObjects.entityState.PlayerJumpState;
import gameObjects.entityState.PlayerRunState;
import gameObjects.entityState.PlayerState;
import graphics.EntitySprite;
import util.Position;

import org.junit.*;
import static org.junit.Assert.*;


/**
 * Class for testing the command classes.
 * @author Star Trekking
 */
public class CommandTest {
    private Player player;
    private CommandInvoker invoker;
    private CrouchPlayerCommand crounch;
    private JumpPlayerCommand jump;
    private RunPlayerCommand run;
    private AttackPlayerCommand attack;
    private Level1 level1;
    @Before
    public void setUp(){
        this.level1 = new Level1();
        //this.player = new Player(new EntitySprite("entity/wizard", 64, 64), new Position(0,0), 0);
        this.attack = new AttackPlayerCommand(level1.getPlayer());
        this.crounch = new CrouchPlayerCommand(level1.getPlayer());
        this.jump = new JumpPlayerCommand(level1.getPlayer());
        this.run = new RunPlayerCommand(level1.getPlayer());
        this.invoker = new CommandInvoker();
    }
   
    @After
    public void clear(){
        invoker.setCommand(run);
    }
    @Test
    public void testRunCommand() {
        invoker.setCommand(run);
        assertTrue("The state set by the command is RunState", level1.getPlayer().getState() instanceof PlayerRunState);
    }
    
    @Test
    public void testAttackCommand(){
        invoker.setCommand(attack);
        assertTrue("The state set by the command is AttackState", level1.getPlayer().getState() instanceof PlayerAttackState);
    }
    
    @Test
    public void testJumpCommand_assertTrue(){
        invoker.setCommand(jump);
        assertTrue("The state set by the command is JumpState", level1.getPlayer().getState() instanceof PlayerJumpState);
    }
    
    @Test
    public void testCrounchCommand() {
        invoker.setCommand(crounch);
        assertTrue("The state set by the command is CrounchState", level1.getPlayer().getState() instanceof PlayerCrouchState);
    }
    
    @Test
    public void testCrounchWhileNotRun(){
        level1.getPlayer().setState(level1.getPlayer().getPlayerJumpState());
        invoker.setCommand(crounch);
        assertFalse("Crunch state has not been set", level1.getPlayer().getState() instanceof PlayerCrouchState);
        
        level1.getPlayer().setState(level1.getPlayer().getPlayerAttackState());
        invoker.setCommand(crounch);
        assertFalse("Crunch state has not been set", level1.getPlayer().getState() instanceof PlayerCrouchState);
    }
    
    @Test
    public void testAttackWhileNotRun() {
        level1.getPlayer().setState(level1.getPlayer().getPlayerJumpState());
        invoker.setCommand(attack);
        assertFalse("Attack state has not been set", level1.getPlayer().getState() instanceof PlayerAttackState);

        level1.getPlayer().setState(level1.getPlayer().getPlayerCrounchState());
        invoker.setCommand(attack);
        assertFalse("Attack state has not been set", level1.getPlayer().getState() instanceof  PlayerAttackState);
    }
}
