/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import java.io.IOException;
import music.MusicGame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import panels.GameFrame;
import panels.GsmListener;

/**
 *
 * @author Gianluca
 */
public class GameStateManagerTest {
    private GameStateManager gsm;
    
    public GameStateManagerTest() throws IOException {
    
        gsm = new GameStateManager();
        
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
     * Test of getMms method, of class GameStateManager.
     */
    @Test
    public void testGetMms() {
        System.out.println("getMms");
        GameState expResult = gsm.getMms();
        GameState result = gsm.getMms();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSps method, of class GameStateManager.
     */
    @Test
    public void testGetSps() {
        System.out.println("getSps");
        StoryPlayState expResult = (StoryPlayState) gsm.getSps();
        StoryPlayState result = (StoryPlayState) gsm.getSps();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGos method, of class GameStateManager.
     */
    @Test
    public void testGetGos() {
        System.out.println("getGos");
        GameState expResult = gsm.getGos();
        GameState result = gsm.getGos();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPs method, of class GameStateManager.
     */
    @Test
    public void testGetPs() {
        System.out.println("getPs");
        GameState expResult = gsm.getPs();
        GameState result = gsm.getPs();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVs method, of class GameStateManager.
     */
    @Test
    public void testGetVs() {
        System.out.println("getVs");
        GameState expResult = gsm.getVs();
        GameState result = gsm.getVs();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSls method, of class GameStateManager.
     */
    @Test
    public void testGetSls() {
        System.out.println("getSls");
        GameState expResult = gsm.getSls();
        GameState result = gsm.getSls();
        assertEquals(expResult, result);
    }

    /**
     * Test of getListener method, of class GameStateManager.
     */
    /*@Test
    public void testGetListener() {
        System.out.println("getListener");
        GsmListener expResult = null;
        GsmListener result = instance.getListener();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setState method, of class GameStateManager.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        
        GameState gameState = gsm.getGos();
        gsm.setState(gameState);
        assertEquals(gameState, gsm.getCurrentState());
        
        gameState = gsm.getVs();
        gsm.setState(gameState);
        assertEquals(gameState, gsm.getCurrentState());
        
        gameState = gsm.getSps();
        for (int i = 1; i< 4; i++){
            ((StoryPlayState) gsm.getSps()).setLevel(i);
            gsm.getSps().set();
            gsm.setState(gameState);
            assertEquals(gameState, gsm.getCurrentState());
        }
        gameState = gsm.getPs();
        gsm.setState(gameState);
        assertEquals(gameState, gsm.getCurrentState());
        
        gameState = gsm.getSls();
        gsm.setState(gameState);
        assertEquals(gameState, gsm.getCurrentState());
        
    }

    /**
     * Test of resumeState method, of class GameStateManager.
     */
    @Test
    public void testResumeState() {
        System.out.println("resumeState");
        GameState gameState = gsm.getSps();
        gsm.resumeState(gameState);
        assertEquals(gameState, gsm.getCurrentState());
    }

    /**
     * Test of exit method, of class GameStateManager.
     */
    @Test
    public void testExit() throws IOException {
        System.out.println("exit");
        gsm.setListener(new GameFrame());
        gsm.exit();
        assertNotNull(gsm.getListener());
    }

    /**
     * Test of setListener method, of class GameStateManager.
     */
    @Test
    public void testSetListener() {
        System.out.println("setListener");
        GsmListener l = null;
        gsm.setListener(l);
        assertEquals(l, gsm.getListener());
    }

    /**
     * Test of getCurrentState method, of class GameStateManager.
     */
    @Test
    public void testGetCurrentState() {
        System.out.println("getCurrentState");
        GameState expResult = gsm.getCurrentState();
        GameState result = gsm.getCurrentState();
        assertEquals(expResult, result);
    }

}
