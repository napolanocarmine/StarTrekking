/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music;

import util.music.MusicGame;
import javax.sound.sampled.Clip;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author CARMINE
 */
public class MusicGameIT {

    public MusicGameIT() {
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
     * Test of setMusic method, of class MusicGame.
     */
    @Test
    public void testSetMusic() {
        String nameMusic = "LevelOne";
        MusicGame mg = new MusicGame(nameMusic);
        assertEquals(nameMusic,mg.getName());
        System.out.println("setMusic1 ok");
        
        MusicGame mg2 = new MusicGame();
        nameMusic = "Shot";
        mg2.setMusic(nameMusic);
        assertNotNull(mg2);
        System.out.println("setMusic2 ok");
    }

    /**
     * Test of play method, of class MusicGame.
     */
    @Test
    public void testPlay() {
        MusicGame mg = new MusicGame("LevelTwo");
        mg.play();
        Clip clip = mg.getClip();
        assertTrue(clip.isOpen()  && !MusicGame.getMute());
        System.out.println("testPlay ok");
    }

    /**
     * Test of stop method, of class MusicGame.
     */
    @Test
    public void testStop() {
        MusicGame mg = new MusicGame("Jump");
        mg.play();
        mg.stop();
        Clip clip = mg.getClip();
        assertTrue(!clip.isRunning()  && !MusicGame.getMute());
        System.out.println("testStop ok");
    }

    /**
     * Test of loop method, of class MusicGame.
     */
    @Test
    public void testLoop() {
    }

    /**
     * Test of getMute method, of class MusicGame.
     */
    @Test
    public void testGetMute() {
    }

    /**
     * Test of toggleMute method, of class MusicGame.
     */
    @Test
    public void testToggleMute() {
    }

}
