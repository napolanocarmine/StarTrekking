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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class GameOverPanelTest {
    
    GameStateManager gsm;
    GameOverPanel gop;
    public GameOverPanelTest() throws IOException {
        gsm = new GameStateManager();
        gop = (GameOverPanel) (gsm.getGos().getPanel());
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
     * Test of initIcon method, of class GameOverPanel.
     */
    @Test
    public void testInitIcon() throws IOException {
        System.out.println("initIcon");
        assertNotNull(gop);
        gop.initIcon();
        
        assertNotNull(gop.getGameOverIcon_black());
        assertNotNull(gop.getMenuIcon_black());
        assertNotNull(gop.getMenuIcon_yellow());
        assertNotNull(gop.getRestartIcon_black());
        assertNotNull(gop.getRestartIcon_yellow());
        assertNotNull(gop.getScreen());
        
        JButton b = (JButton) TestUtils.getChildNamed(gop, "restartButton");
        assertNotNull(b);
        
        assertEquals(b.getBorder(), null);
        b = (JButton) TestUtils.getChildNamed(gop, "mainMenuButton");
        assertNotNull(b);
        
        assertEquals(b.getBorder(), null);
    }

    /**
     * Test of paintComponent method, of class GameOverPanel.
     */
    
    @Test
    public void testPaintComponent() {
        System.out.println("paintComponent");
        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        gop.paintComponent(g);
        gop.setVisible(true);
        assertEquals(3, gop.getComponentCount());
    }
    
    /**
     * Test the restartButtonActionPerformed method, of class GameOverPanel
     */
    @Test
    public void testRestartButtonActionPerformed(){
        System.out.println("restartButtonActionPerformed");
        assertNotNull(gop);
        JButton b = (JButton) TestUtils.getChildNamed(gop, "restartButton");
        assertNotNull(b);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                b.doClick();
                assertEquals(gsm.getSps(),gsm.getCurrentState());
            }
        });
        
    }
    
    /**
     * Test the mainMenuButtonActionPerformed method, of class GameOverPanel
     */
    @Test
    public void testMainMenuButtonActionPerformed(){
        System.out.println("mainMenuButtonActionPerformed");
        assertNotNull(gop);
        JButton b = (JButton) TestUtils.getChildNamed(gop, "mainMenuButton");
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
