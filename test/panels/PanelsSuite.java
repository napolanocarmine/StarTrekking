/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Gianluca
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({panels.GameOverPanelTest.class, panels.GameFrameTest.class, panels.SelectionLevelPanelTest.class, panels.VictoryPanelTest.class, panels.MainMenuPanelTest.class, panels.PausePanelTest.class, panels.GamePanelTest.class})
public class PanelsSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
