/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import State.State;
import javax.swing.JPanel;
import music.MusicGame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gianluca
 */
public class GameStateTest {
    
    public GameStateTest() {
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
     * Test of getPanel method, of class GameState.
     */
    @Test
    public void testGetPanel() {
        System.out.println("getPanel");
        GameState instance = new GameStateImpl();
        JPanel expResult = null;
        JPanel result = instance.getPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setJPanel method, of class GameState.
     */
    @Test
    public void testSetJPanel() {
        System.out.println("setJPanel");
        JPanel panel = null;
        GameState instance = new GameStateImpl();
        instance.setJPanel(panel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGSM method, of class GameState.
     */
    @Test
    public void testSetGSM() {
        System.out.println("setGSM");
        GameStateManager gsm = null;
        GameState instance = new GameStateImpl();
        instance.setGSM(gsm);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGSM method, of class GameState.
     */
    @Test
    public void testGetGSM() {
        System.out.println("getGSM");
        GameState instance = new GameStateImpl();
        GameStateManager expResult = null;
        GameStateManager result = instance.getGSM();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resume method, of class GameState.
     */
    @Test
    public void testResume() {
        System.out.println("resume");
        GameState instance = new GameStateImpl();
        instance.resume();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startMusic method, of class GameState.
     */
    @Test
    public void testStartMusic() {
        System.out.println("startMusic");
        GameState instance = new GameStateImpl();
        instance.startMusic();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stopMusic method, of class GameState.
     */
    @Test
    public void testStopMusic() {
        System.out.println("stopMusic");
        GameState instance = new GameStateImpl();
        instance.stopMusic();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loopMusic method, of class GameState.
     */
    @Test
    public void testLoopMusic() {
        System.out.println("loopMusic");
        GameState instance = new GameStateImpl();
        instance.loopMusic();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMusicGame method, of class GameState.
     */
    @Test
    public void testGetMusicGame() {
        System.out.println("getMusicGame");
        GameState instance = new GameStateImpl();
        MusicGame expResult = null;
        MusicGame result = instance.getMusicGame();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class GameStateImpl extends GameState {

        @Override
        public void nextState(State state) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void updateGame() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void set() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
