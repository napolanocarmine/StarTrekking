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
public class MainMenuPanelTest {
    
    GameStateManager gsm;
    MainMenuPanel mmp;
    public MainMenuPanelTest() throws IOException {
        gsm = new GameStateManager();
        mmp = (MainMenuPanel) (gsm.getMms().getPanel());
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
     * Test of setSound method, of class MainMenuPanel.
     */
    @Test
    public void testSetSound() {
        System.out.println("setSound");
        assertNotNull(mmp);
        mmp.setSound();
        
        JButton b = (JButton) TestUtils.getChildNamed(mmp, "soundButton");
        assertNotNull(b);
        assertEquals(b.getBorder(), null);
        
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                b.doClick();
                assertEquals(b.getBorder(), null);
            }
        });
    }
    
    @Test
    public void testStoryModeButtonActionPerformed(){
        System.out.println("storyModeButtonActionPerformed");
        assertNotNull(mmp);
        JButton b = (JButton) TestUtils.getChildNamed(mmp, "storyModeButton");
        assertNotNull(b);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                b.doClick();
                assertEquals(gsm.getSls(), gsm.getCurrentState());
            }
        });
    }
    
    @Test
    public void testInitIcon() throws IOException{
        System.out.println("initIcon");
        assertNotNull(mmp);
        
        mmp.initIcon();
        assertNotNull(mmp.getExitIcon());
        assertNotNull(mmp.getExitIconYellow());
        assertNotNull(mmp.getTitleIcon());
        assertNotNull(mmp.getStoryModeIcon());
        assertNotNull(mmp.getStoryModeIconYellow());
        assertNotNull(mmp.getScreen());
        
        JButton b = (JButton) TestUtils.getChildNamed(mmp, "storyModeButton");
        assertNotNull(b);
        assertEquals(b.getBorder(), null);
        
        b = (JButton) TestUtils.getChildNamed(mmp, "exitButton");
        assertNotNull(b);
        assertEquals(b.getBorder(), null);
    }
}
