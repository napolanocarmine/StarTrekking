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
import panels.GameOverPanel;
import panels.GamePanel;

/**
 *
 * @author Gianluca
 */
public class GameOverStateTest {
    
    private GameStateManager gsm;
    private GameOverState gos;
    
    public GameOverStateTest() throws IOException {
        gsm = new GameStateManager();
        gos = (GameOverState) gsm.getGos();
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
     * Test of set method, of class GameOverState.
     */
    @Test
    public void testSet() {
        
    }

    /**
     * Test of nextState method, of class GameOverState.
     */
    @Test
    public void testNextState() {
        System.out.println("nextState");
        State state = (MainMenuState) gsm.getMms();
        StoryPlayState sps = (StoryPlayState) gsm.getSps();
        ((GamePanel) sps.getPanel()).setLevel(1);
        gos.nextState(state);
        assertEquals(state, gsm.getCurrentState());
        
        state = (StoryPlayState) gsm.getSps();
        gos.nextState(state);
        assertEquals(state, gsm.getCurrentState());
    }

}
