/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import State.State;
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
public class StateTest {
    
    public StateTest() {
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
     * Test of nextState method, of class State.
     */
    @Test
    public void testNextState() {
        System.out.println("nextState");
        State state = null;
        State instance = new StateImpl();
        instance.nextState(state);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateGame method, of class State.
     */
    @Test
    public void testUpdateGame() {
        System.out.println("updateGame");
        State instance = new StateImpl();
        instance.updateGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class State.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        State instance = new StateImpl();
        instance.set();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class StateImpl implements State {

        public void nextState(State state) {
        }

        public void updateGame() {
        }

        public void set() {
        }
    }
    
}
