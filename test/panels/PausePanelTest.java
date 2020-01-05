/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import gamestate.GameStateManager;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static panels.GamePanel.HEIGHT;
import static panels.GamePanel.WIDTH;

/**
 *
 * @author Gianluca
 */
public class PausePanelTest {
    
    private GameStateManager gsm;
    private PausePanel pp;
    public PausePanelTest() throws IOException {
        gsm = new GameStateManager();
        pp = (PausePanel) (gsm.getPs().getPanel());
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
     * Test of initIcon method, of class PausePanel.
     */
    @Test
    public void testInitIcon() throws IOException {
        System.out.println("initIcon");
        assertNotNull(pp);
        pp.initIcon();
        assertNotNull(pp.getScreen());
        assertNotNull(pp.getRestartIcon_black());
        assertNotNull(pp.getRestartIcon_yellow());
        assertNotNull(pp.getResumeIcon_black());
        assertNotNull(pp.getResumeIcon_yellow());
        assertNotNull(pp.getMainMenuIcon_black());
        assertNotNull(pp.getMainMenuIcon_yellow());
        
        JButton b = (JButton) TestUtils.getChildNamed(pp, "resumeButton");
        assertNotNull(b);
        assertEquals(b.getBorder(), null);
        b = (JButton) TestUtils.getChildNamed(pp, "restartButton");
        assertNotNull(b);
        assertEquals(b.getBorder(), null);
        b = (JButton) TestUtils.getChildNamed(pp, "mainMenuButton");
        assertNotNull(b);
        assertEquals(b.getBorder(), null);
    }

    
    /**
     * Test of paintComponent method, of class PausePanel.
     */
    @Test
    public void testPaintComponent() {
        System.out.println("paintComponent");
        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        pp.paintComponent(g);
        pp.setVisible(true);
        assertEquals(5, pp.getComponentCount());
    }
    
    @Test
    public void testResumeButtonActionPerformed(){
        System.out.println("resumeButtonActionPerformed");
        assertNotNull(pp);
        JButton b = (JButton) TestUtils.getChildNamed(pp, "resumeButton");
        assertNotNull(b);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                b.doClick();
                assertEquals(gsm.getSps(),gsm.getCurrentState());
            }
        });
    }
    
    @Test 
    public void testMainMenuButtonActionPerformed(){
        System.out.println("mainMenuButtonActionPerformed");
        assertNotNull(pp);
        JButton b = (JButton) TestUtils.getChildNamed(pp, "mainMenuButton");
        assertNotNull(b);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                b.doClick();
                assertEquals(gsm.getMms(),gsm.getCurrentState());
            }
        });
    }
    
    @Test
    public void testRestartButtonActionPerformed(){
        System.out.println("restartButtonActionPerformed");
        assertNotNull(pp);
        JButton b = (JButton) TestUtils.getChildNamed(pp, "restartButton");
        assertNotNull(b);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                b.doClick();
                assertEquals(gsm.getSps(),gsm.getCurrentState());
            }
        });
    }
}
