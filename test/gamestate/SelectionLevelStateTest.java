/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import State.State;
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
public class SelectionLevelStateTest {
    private GameStateManager gsm;
    private SelectionLevelState sls;
    
    public SelectionLevelStateTest() throws IOException {
        gsm = new GameStateManager();
        sls = (SelectionLevelState) gsm.getSls();
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
     * Test of nextState method, of class SelectionLevelState.
     */
    @Test
    public void testNextState() {
        System.out.println("nextState");
        State state = gsm.getSps();
        gsm.getSps().setLevel(1);
        gsm.getSps().set();
        sls.nextState(state);
        assertEquals(state, gsm.getCurrentState());
        
        gsm.getSps().setLevel(2);
        gsm.getSps().set();
        sls.nextState(state);
        assertEquals(state, gsm.getCurrentState());
        
        gsm.getSps().setLevel(3);
        gsm.getSps().set();
        sls.nextState(state);
        assertEquals(state, gsm.getCurrentState());
        
        state = gsm.getMms();
        sls.nextState(state);
        assertEquals(state, gsm.getCurrentState());
    }

    
}
