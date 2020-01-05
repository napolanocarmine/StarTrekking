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
public class PauseStateTest {
    private GameStateManager gsm;
    private PauseState ps;
    
    public PauseStateTest() throws IOException {
        gsm = new GameStateManager();
        ps = (PauseState) gsm.getPs();
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
     * Test of setResume method, of class PauseState.
     */
    @Test
    public void testSetResume() {
        System.out.println("setResume");
        boolean resume = false;
        ps.setResume(resume);
        assertEquals(resume, ps.isResume());
        
        resume = true;
        ps.setResume(resume);
        assertEquals(resume, ps.isResume());
    }

    /**
     * Test of nextState method, of class PauseState.
     */
    @Test
    public void testNextState() {
        System.out.println("nextState");
        State state = gsm.getSps();
        ps.setResume(true);
        ps.nextState(state);
        assertEquals(state, gsm.getCurrentState());
        
        state = gsm.getMms();
        ps.nextState(state);
        assertEquals(state, gsm.getCurrentState());
    }


    
}
