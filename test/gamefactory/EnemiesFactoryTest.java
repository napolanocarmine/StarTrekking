/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamefactory;

import gameObjects.*;
import util.graphics.EntitySprite;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.Position;

/**
 *
 * @author startrekking
 * 
 * Class for testing EnemiesFactory class
 */


public class EnemiesFactoryTest {
        private EnemiesFactory ef;
        private  EnemiesLevel enemies;
        private EntitySprite sprite;
        private Enemy enemy;
        private ArrayList<GameObject>  enemiesList;
    public EnemiesFactoryTest() {
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
    @Test
    public void loadEnemiesLevel1Test(){
        System.out.println("loading EnemiesLevel1");
        sprite = new EntitySprite("entity/goblin", 64, 64);
        enemy = new GroundEnemy(sprite, new Position(0, 0), 0);
        ef = new EnemiesFactory();
        enemies = (EnemiesLevel)ef.build(1);
        enemiesList = enemies.getObjs();
        assertTrue(!enemiesList.isEmpty());
    }
    @Test
    public void loadEnemiesLevel2Test(){
        System.out.println("loading EnemiesLevel2");
        sprite = new EntitySprite("entity/Skeleton", 64, 64);
        enemy = new GroundEnemy(sprite, new Position(0, 0), 0);
        ef = new EnemiesFactory();
        enemies = (EnemiesLevel)ef.build(2);
        enemiesList = enemies.getObjs();
        assertTrue(!enemiesList.isEmpty());
    }
    @Test
    public void loadEnemiesLevel3Test(){
        System.out.println("loading EnemiesLevel3");
        sprite = new EntitySprite("entity/dragon", 64, 64);
        enemy = new FlyingEnemy(sprite, new Position(0, 0), 0);
        ef = new EnemiesFactory();
        enemies = (EnemiesLevel)ef.build(3);
        enemiesList = enemies.getObjs();
        assertTrue(!enemiesList.isEmpty());
    }
}
