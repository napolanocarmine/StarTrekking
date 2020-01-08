/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import gamestate.GameState;
import gamestate.GameStateManager;
import java.io.IOException;
import javax.swing.JPanel;
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
public class GameFrameTest {
    private GameFrame frame;
    private GameStateManager gms;
    public GameFrameTest() throws IOException {
        frame = new GameFrame();
        gms = new GameStateManager();
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
     * Test of stateChanged method, of class GameFrame.
     */
    @Test
    public void testStateChanged() throws IOException {
        System.out.println("stateChanged");
        GameState s = gms.getMms();
        
        frame.setVisible(true);
        assertNotNull(frame);
        frame.stateChanged(s);
        assertEquals(s.getPanel(), frame.getGamePanel());
    }
    
}
