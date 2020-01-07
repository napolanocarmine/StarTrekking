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
public class SelectionLevelPanelTest {
    private GameStateManager gsm;
    private SelectionLevelPanel slp;
    public SelectionLevelPanelTest() throws IOException {
        gsm = new GameStateManager();
        slp = (SelectionLevelPanel) (gsm.getSls().getPanel());
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
     * Test of initIcon method, of class SelectionLevelPanel.
     * @throws java.io.IOException
     */
    @Test
    public void testInitIcon() throws IOException {
        System.out.println("initIcon");
        assertNotNull(slp);
        slp.initIcon();
        
        assertNotNull(slp.getBackMenuIcon_black());
        assertNotNull(slp.getBackMenuIcon_yellow());
        assertNotNull(slp.getLevelIcon());
        assertNotNull(slp.getLevelOneIcon_black());
        assertNotNull(slp.getLevelOneIcon_yellow());
        assertNotNull(slp.getLevelTwoIcon_black());
        assertNotNull(slp.getLevelTwoIcon_yellow()); 
        assertNotNull(slp.getLevelThreeIcon_black());
        assertNotNull(slp.getLevelThreeIcon_yellow());
        assertNotNull(slp.getScreen());
        
        JButton b = (JButton) TestUtils.getChildNamed(slp, "levelOneButton");
        assertNotNull(b);
        assertEquals(b.getBorder(), null);
        
        b = (JButton) TestUtils.getChildNamed(slp, "levelTwoButton");
        assertNotNull(b);
        assertEquals(b.getBorder(), null);
        
        b = (JButton) TestUtils.getChildNamed(slp, "levelThreeButton");
        assertNotNull(b);
        assertEquals(b.getBorder(), null);

        b = (JButton) TestUtils.getChildNamed(slp, "backMainMenuButton");
        assertNotNull(b);
        assertEquals(b.getBorder(), null);
        
    }
    
    @Test
    public void testLevelOneButtonActionPerformed(){
        System.out.println("levelOneButtonActionPerformed");
        assertNotNull(slp);
        JButton b = (JButton) TestUtils.getChildNamed(slp, "levelOneButton");
        assertNotNull(b);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                b.doClick();
                assertEquals(gsm.getSps(), gsm.getCurrentState());
            }
        });
    }
    
    @Test
    public void testLevelTwoButtonActionPerformed(){
        System.out.println("levelTwoButtonActionPerformed");
        assertNotNull(slp);
        JButton b = (JButton) TestUtils.getChildNamed(slp, "levelTwoButton");
        assertNotNull(b);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                b.doClick();
                assertEquals(gsm.getSps(), gsm.getCurrentState());
            }
        });
    }
    
    @Test
    public void testLevelThreeButtonActionPerformed(){
        System.out.println("levelThreeButtonActionPerformed");
        assertNotNull(slp);
        JButton b = (JButton) TestUtils.getChildNamed(slp, "levelThreeButton");
        assertNotNull(b);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                b.doClick();
                assertEquals(gsm.getSps(), gsm.getCurrentState());
            }
        });
    }
    
}
