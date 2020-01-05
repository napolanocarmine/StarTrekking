/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import gamestate.GameStateManager;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
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
public class VictoryPanelTest {
    
    private GameStateManager gsm;
    private VictoryPanel vp;
    public VictoryPanelTest() throws IOException {
        gsm = new GameStateManager();
        vp = (VictoryPanel) (gsm.getVs().getPanel());
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
     * Test of paintComponent method, of class VictoryPanel.
     */
    /*@Test
    public void testPaintComponent() {
        System.out.println("paintComponent");
        Graphics g = null;
        VictoryPanel instance = null;
        instance.paintComponent(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
    @Test
    public void testInitIcon() throws IOException{
        System.out.println("initIcon");
        assertNotNull(vp);
        
        vp.initIcon();
        assertNotNull(vp.getMainMenuIcon_black());
        assertNotNull(vp.getMainMenuIcon_yellow());
        assertNotNull(vp.getTitleIcon());
        assertNotNull(vp.getNextLevelIcon_black());
        assertNotNull(vp.getNextLevelIcon_yellow());
        assertNotNull(vp.getRestartIcon_black());
        assertNotNull(vp.getRestartIcon_yellow());
        assertNotNull(vp.getScreen());
        
        JButton b = (JButton) TestUtils.getChildNamed(vp, "nextLevelButton");
        assertNotNull(b);
        assertEquals(b.getBorder(), null);
        
        b = (JButton) TestUtils.getChildNamed(vp, "restartButton");
        assertNotNull(b);
        assertEquals(b.getBorder(), null);
        
        b = (JButton) TestUtils.getChildNamed(vp, "mainMenuButton");
        assertNotNull(b);
        assertEquals(b.getBorder(), null);
    }
    
    @Test
    public void testNextLevelButtonActionPerformed(){
        System.out.println("nextLevelButtonActionPerformed");
        assertNotNull(vp);
        JButton b = (JButton) TestUtils.getChildNamed(vp, "nextLevelButton");
        assertNotNull(b);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                b.doClick();
                assertEquals(gsm.getSps(),gsm.getCurrentState());
                assertEquals(2, gsm.getSps().getLevel());
                b.doClick();
                assertEquals(gsm.getSps(),gsm.getCurrentState());
                assertEquals(2, gsm.getSps().getLevel());
                b.doClick();
                assertEquals(gsm.getSps(),gsm.getCurrentState());
                assertEquals(3, gsm.getSps().getLevel());
                b.doClick();
                assertEquals(gsm.getSps(),gsm.getCurrentState());
                assertEquals(3, gsm.getSps().getLevel());
            }
        });
    }
    
    @Test
    public void testRestartButtonActionPerformed(){
        System.out.println("restartButtonActionPerformed");
        assertNotNull(vp);
        JButton b = (JButton) TestUtils.getChildNamed(vp, "restartButton");
        assertNotNull(b);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                b.doClick();
                assertEquals(gsm.getSps(),gsm.getCurrentState());
                assertEquals(1, gsm.getSps().getLevel());
            }
        });
    }
    
    @Test
    public void testMainMenuButtonActionPerformed(){
        System.out.println("mainMenuButtonActionPerformed");
        assertNotNull(vp);
        JButton b = (JButton) TestUtils.getChildNamed(vp, "mainMenuButton");
        assertNotNull(b);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                b.doClick();
                assertEquals(gsm.getMms(),gsm.getCurrentState());
            }
        });
    }
}
