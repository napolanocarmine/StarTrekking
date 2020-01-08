/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import KeyHandler.KeyHandler;
import gameObjects.Level;
import gamestate.GameStateManager;
import java.awt.Graphics;
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
public class GamePanelTest {
    private GameStateManager gsm;
    private GamePanel gp;
    public GamePanelTest() throws IOException {
        gsm = new GameStateManager();
        gp = (GamePanel)(gsm.getSps().getPanel());
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
     * Test of startThread method, of class GamePanel.
     */
    @Test
    public void testStartThread() {
        System.out.println("startThread");
        gp.startThread();
        assertNotNull(gp.getThread());
    }

    /**
     * Test of stopThread method, of class GamePanel.
     */
    @Test
    public void testStopThread() {
        System.out.println("stopThread");
        gp.stopThread();
        assertEquals(gp.getThread(), null);
    }

    /**
     * Test of init method, of class GamePanel.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        gp.init();
        assertEquals(true, gp.isRunning());
        assertNotNull(gp.getImg());
    }

    /**
     * Test of setLevel method, of class GamePanel.
     */
    @Test
    public void testSetLevel() {
        System.out.println("setLevel");
        int code = 1;
        Level level = null;
        gp.setLevel(code);
        assertNotNull(gp.getLevel());
        code = 2;
        gp.setLevel(code);
        assertNotEquals(gp.getLevel(), level);
        code = 3;
        gp.setLevel(code);
        assertNotEquals(gp.getLevel(), level);
    }

    /**
     * Test of getKeyH method, of class GamePanel.
     */
    @Test
    public void testGetKeyH() {
        System.out.println("getKeyH");
        KeyHandler expResult = gp.getKeyH();
        KeyHandler result = gp.getKeyH();
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class GamePanel.
     */
    /*@Test
    public void testRun() {
        System.out.println("run");
        GamePanel instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class GamePanel.
     */
    /*@Test
    public void testUpdate() {
        System.out.println("update");
        GamePanel instance = null;
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of render method, of class GamePanel.
     */
    /*@Test
    public void testRender() {
        System.out.println("render");
        GamePanel instance = null;
        instance.render();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWIDTH method, of class GamePanel.
     */
    @Test
    public void testGetWIDTH() {
        System.out.println("getWIDTH");
        int expResult = 960;
        int result = GamePanel.getWIDTH();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHEIGHT method, of class GamePanel.
     */
    @Test
    public void testGetHEIGHT() {
        System.out.println("getHEIGHT");
        int expResult = 560;
        int result = GamePanel.getHEIGHT();
        assertEquals(expResult, result);
    }

    /**
     * Test of getThread method, of class GamePanel.
     */
    @Test
    public void testGetThread() {
        System.out.println("getThread");
        Thread expResult = gp.getThread();
        Thread result = gp.getThread();
        assertEquals(expResult, result);
    }

    /**
     * Test of setState method, of class GamePanel.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        int code = 1;
        gp.setState(code);
        assertEquals(gsm.getGos(), gsm.getCurrentState());
        code = 2;
        gp.setState(code);
        assertEquals(gsm.getVs(), gsm.getCurrentState());
        
    }

    /**
     * Test of setPause method, of class GamePanel.
     */
    @Test
    public void testSetPause() {
        System.out.println("setPause");
        boolean pause = false;
        gp.setPause(pause);
        assertEquals(false, gp.getPause());
        pause = true;
        gp.setPause(pause);
        assertEquals(true, gp.getPause());
    }
    
}
