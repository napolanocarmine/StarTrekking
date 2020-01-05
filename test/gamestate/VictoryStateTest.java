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

/**
 *
 * @author Gianluca
 */
public class VictoryStateTest {
    private GameStateManager gsm;
    private VictoryState vs;
    
    public VictoryStateTest() throws IOException {
        
        gsm = new GameStateManager();
        vs = (VictoryState) gsm.getVs();
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
     * Test of setLevel method, of class VictoryState.
     */
    @Test
    public void testSetLevel() {
        System.out.println("setLevel");
        int level = 1;
        vs.setLevel(level);
        assertEquals(1, vs.getLevel());
    }

    /**
     * Test of getLevel method, of class VictoryState.
     */
    /*@Test
    public void testGetLevel() {
        System.out.println("getLevel");
        VictoryState instance = null;
        int expResult = 0;
        int result = instance.getLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of nextState method, of class VictoryState.
     */
    @Test
    public void testNextState() {
        System.out.println("nextState");
        State state = gsm.getSps();
        vs.setLevel(1);
        gsm.getSps().setLevel(1);
        gsm.getSps().set();
        vs.nextState(state);
        assertEquals(state, gsm.getCurrentState());
        
        vs.setLevel(2);
        gsm.getSps().setLevel(2);
        gsm.getSps().set();
        vs.nextState(state);
        assertEquals(state, gsm.getCurrentState());
        
    }


    
}
