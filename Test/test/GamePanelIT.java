/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import javax.swing.JFrame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import startrekking.GamePanel;
import tiles.TileFacade;

/**
 *
 * @author CARMINE
 */
public class GamePanelIT {
    
    public GamePanelIT() {
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
     * Test of main method, of class GamePanel.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        TileFacade tf = new TileFacade();
        JFrame panel = new JFrame();
        panel.setName("STAR TREKKING");
        GamePanel test = new GamePanel();
        
        //check window's dimension
        assertEquals(1600, GamePanel.getWIDTH());
        System.out.println("larghezza");
        assertEquals(528, GamePanel.getHEIGHT());
        System.out.println("altezza");
        
        //check window's name
        assertEquals(panel.getName(), GamePanel.NAME);
        System.out.println("nome");
        
        //check update window
        panel.setBackground(Color.YELLOW);
        assertEquals(panel.getBackground(), test.color);
        System.out.println("colore");
        
        //check graphics object is not null
        assertEquals(true, test.test);
        System.out.println("oggetto grafico");
        
        //check graphics object is drawed
        assertEquals(true, test.drawImage);
        System.out.println("disegnato");

    }
    
}
