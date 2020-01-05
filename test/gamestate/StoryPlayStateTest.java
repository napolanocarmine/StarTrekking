/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import panels.GamePanel;

/**
 *
 * @author Gianluca
 */
public class StoryPlayStateTest {

    private GameStateManager gsm;
    private StoryPlayState sps;

    public StoryPlayStateTest() throws IOException {

        gsm = new GameStateManager();
        sps = gsm.getSps();

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
     * Test of setLevel method, of class StoryPlayState.
     */
    /*@Test
    public void testSetLevel() {
        System.out.println("setLevel");
        int level = 0;
        StoryPlayState instance = null;
        instance.setLevel(level);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLevel method, of class StoryPlayState.
     */
 /*@Test
    public void testGetLevel() {
        System.out.println("getLevel");
        StoryPlayState instance = null;
        int expResult = 0;
        int result = instance.getLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pause method, of class StoryPlayState.
     */
    @Test
    public void testPause() {
        System.out.println("pause");
        sps.pause();
        assertEquals(true, ((GamePanel) sps.getPanel()).getPause());
        assertEquals(gsm.getPs(), gsm.getCurrentState());
    }

    /**
     * Test of resume method, of class StoryPlayState.
     */
    @Test
    public void testResume() {
        System.out.println("resume");
        sps.resume();
        assertEquals(false, ((GamePanel) sps.getPanel()).getPause());
    }

    /**
     * Test of set method, of class StoryPlayState.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        sps.setLevel(1);
        sps.set();
        assertEquals(false, ((GamePanel) gsm.getSps().getPanel()).getPause());
    }

    /**
     * Test of startMusic method, of class StoryPlayState.
     */
    /*@Test
    public void testStartMusic() {
        System.out.println("startMusic");
        StoryPlayState instance = null;
        instance.startMusic();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextState method, of class StoryPlayState.
     */
    @Test
    public void testNextState() {
        System.out.println("nextState");
        State state = gsm.getPs();
        sps.nextState(state);
        assertEquals(gsm.getPs(), gsm.getCurrentState());
        
        state = gsm.getVs();
        sps.nextState(state);
        assertEquals(gsm.getVs(), gsm.getCurrentState());
        
        state = gsm.getGos();
        sps.nextState(state);
        assertEquals(gsm.getGos(), gsm.getCurrentState());
    }

    /**
     * Test of updateGame method, of class StoryPlayState.
     */
    /*@Test
    public void testUpdateGame() {
        System.out.println("updateGame");
        StoryPlayState instance = null;
        instance.updateGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
}
