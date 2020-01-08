/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import gameObjects.entityState.PlayerDeadState;
import gameObjects.entityState.PlayerState;
import java.awt.Graphics2D;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.EntityBox;
import util.Position;
import util.TileCollision;
import util.graphics.EntitySprite;
import util.music.MusicGame;

/**
 *
 * @author vince
 */
public class PlayerTest {
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of horizontalMove method, of class Player.
     */
    @Test(expected = NullPointerException.class)
    public void testHorizontalMove() {
        System.out.println("horizontalMove");
        EntitySprite playerSprite = new EntitySprite("entity/wizard", 64, 64);
          
        Player instance = new Player(playerSprite, new Position(0, 0), 96);
        float dx=(float) (instance.getDx()+0.30001003);
        assertEquals(0.30001003, dx, 0.0005);
        boolean expResult = false;
        boolean result = instance.horizontalMove();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verticalMove method, of class Player.
     */
    @Test(expected = NullPointerException.class)
    public void testVerticalMove() {
        System.out.println("verticalMove");
        EntitySprite playerSprite = new EntitySprite("entity/wizard", 64, 64);
        Player instance = new Player(playerSprite, new Position(0, 0), 96);
        float dy=(float) (instance.getDy()+398);
        assertEquals(398.005, dy, 0.005);
        boolean expResult = false;
        boolean result = instance.verticalMove();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of hitted method, of class Player.
     */
    @Test
    public void testHitted() {
        System.out.println("hitted");
        EntitySprite playerSprite = new EntitySprite("entity/wizard", 64, 64);
        Player instance = new Player(playerSprite, new Position(0, 0), 96);
        instance.hitted();
        instance.hitted();
        instance.hitted();
        assertEquals(instance.getPlayerDeadState(), instance.getState());
        System.out.println(instance.getHP());
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

   
}
