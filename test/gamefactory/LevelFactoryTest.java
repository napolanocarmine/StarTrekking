/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamefactory;

import gameObjects.Level;
import gameObjects.Level1;
import gameObjects.Level2;
import gameObjects.Level3;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author startrekking
 */
public class LevelFactoryTest {
    private Level level;
    private LevelFactory lv;
    
    public LevelFactoryTest() {
    }
    
    @BeforeClass
    public static void initClass() {
    System.out.println("initClass()");
    }
    @AfterClass
    public static void endClass() {
    System.out.println("endClass()");
    }
    @Before
    public void initMethod() {
    System.out.println("initMethod()");
    }
    @After
    public void endMethod() {
    System.out.println("end Method");
    }
    /**
     * test method for testing level1 correct creation
     */
    @Test
    public void loadLevel1Test(){
    System.out.println("loading Level1");
    lv = new LevelFactory();
    level = (Level)lv.build(1);
    assertTrue(level instanceof Level1);
    }
     /**
     * test method for testing level2 correct creation
     */
    @Test
    public void loadLevel2Test(){
    System.out.println("loading Level2");
    lv = new LevelFactory();
    level = (Level)lv.build(2);
    assertTrue(level instanceof Level2);
    }
     /**
     * test method for testing level3 correct creation
     */
    @Test
    public void loadLevel3Test(){
    System.out.println("loading Level3");
    lv = new LevelFactory();
    level = (Level)lv.build(3);
    assertTrue(level instanceof Level3);
    }
}
