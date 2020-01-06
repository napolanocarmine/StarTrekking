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
import panels.GsmListener;

/**
 *
 * @author Gianluca
 */
public class MainMenuStateTest {
    
    private GameStateManager gsm;
    private MainMenuState mms;
    
    public MainMenuStateTest() throws IOException {
        gsm = new GameStateManager();
        mms = (MainMenuState) gsm.getMms();
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
     * Test of nextState method, of class MainMenuState.
     */
    @Test
    public void testNextState() {
        System.out.println("nextState");
        State state = gsm.getSls();
        mms.nextState(state);
        assertEquals(state, gsm.getCurrentState());
        
    }
    
    
  
    
}
