package graphics;

import util.graphics.EntitySprite;
import util.graphics.Animation;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.EntityEnum;

public class AnimationTest {
    
//    private final int WIDTH = 100;
//    private final int HEIGHT = 100;
//    private BufferedImage img;
//    private Graphics2D g;
//    
//    private JFrame f;
//    private JPanel p;
    
    Animation ani;
    EntitySprite playerSprite;
    
    public AnimationTest(){
        //initTestingFrame();
    } 
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        playerSprite = new EntitySprite("entity/wizard", 64, 64);
        ani = new Animation(playerSprite.getSprite(EntityEnum.RUN));
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testAnimationSet(){
        assertNotNull(ani);
    }
    
    @Test
    public void testAnimationUpdateCount(){
        int expectedCount = ani.getCount();
        ani.updateGame();
        assertEquals(expectedCount+1, ani.getCount());
    }
    
    @Test
    public void testAnimationUpdateFrame(){
        int delay = 3;
        ani.setDelay(delay);
        ani.setCount(delay-1);
        int previousFrame = ani.getFrame();
        ani.updateGame();
        int expectedFrame = previousFrame+1;
        assertEquals("The updateGame() method correctly update the frame when "
                + "the counter reach the set counter", expectedFrame, ani.getFrame());
    }
    
    @Test
    public void testAnimationRestartFrame(){
        
        int numFrames = playerSprite.getSprite(EntityEnum.RUN).length;
        int delay = 3;
        ani.setDelay(delay);
        for(int i=0; i<=(delay*numFrames);i++){
            ani.updateGame();
        }
        assertTrue(ani.hasPlayedOnce());
    }
    

//    private void initTestingFrame() {
//        f = new JFrame();
//        f.setTitle("Testing frame");
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setResizable(false);
//        f.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        f.setIgnoreRepaint(true);
//        f.setVisible(true);
//        p.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
//        g = (Graphics2D) img.getGraphics();
//        this.setContentPane(gsm.getCurrentState().getPanel());
//        f.pack();
//        f.setLocationRelativeTo(null);
//    }
}
