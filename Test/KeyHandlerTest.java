
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Star Trekking
 */
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import org.junit.*;
import static org.junit.Assert.*;
import util.KeyHandler;

/**
 * KeyHandler Test
 */
public class KeyHandlerTest {

    /**
     * KeyHandler object, used for catching the KeyEvent
     */
    public KeyHandler keyHdl;
    /**
     * KeyEvent object, used for simulating the human action of pressing the key
     */
    public KeyEvent keyEvnt;
    /**
     * JFrame object, used as source for the key event
     */
    public JFrame frame;
    /**
     * ObserverTest object for simulating the keyhandler's observer
     */
    public ObserverTest observer;

    /**
     * Simulate the observer's behavior
     */
    public class ObserverTest implements Observer {

        private KeyHandler keyHandlerObs;
        private boolean jump, shoot, slide;

        public ObserverTest(KeyHandler obs) {
            this.keyHandlerObs = obs;
            this.jump = false;
            this.shoot = false;
            this.slide = false;
        }

        /**
         * Override of the update method. Select the action related to the key
         * pressed
         *
         * @param o1
         */
        @Override
        public void update(Observable o, Object o1) {
            if (this.keyHandlerObs == o) {
                if (keyHandlerObs.getValue() <= 3 && keyHandlerObs.getValue() >= 1 && !keyHandlerObs.isPressed()) {
                    switch (keyHandlerObs.getValue()) {
                        case 1:
                            System.out.println("I'm jumping");
                            this.jump = true;
                            break;
                        case 2:
                            System.err.println("I'm sliding");
                            this.slide = true;
                            break;
                        case 3:
                            System.err.println("I'm shooting");
                            this.shoot = true;
                            break;
                    }
                }
                if (keyHandlerObs.getValue() == 2 && keyHandlerObs.isPressed()) {
                    System.err.println("My sliding is finished");
                    slide = false;
                }
            }
        }

        /**
         * Return true if the jump key is pressed, otherwise false
         *
         * @return
         */
        public boolean getJump() {
            return this.jump;
        }

        /**
         * Return true if the Shoot key is pressed, otherwise false
         *
         * @return
         */
        public boolean getShoot() {
            return this.shoot;
        }

        /**
         * Return true if the Slide key is pressed, otherwise false
         *
         * @return
         */
        public boolean getSlide() {
            return this.slide;
        }
    }

    /**
     * Instantiate the object that are going to be used in the test methods
     */
    @Before
    public void instantiation_element() {
        keyHdl = new KeyHandler();
        frame = new JFrame();
        keyEvnt = new KeyEvent(frame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 1, 32, ' ');
        observer = new ObserverTest(keyHdl);
        keyHdl.addObserver(observer);
    }

    /**
     * Test for checking the Space key pressing
     */
    @Test
    public void testKeyPressedSpace() {
        keyHdl.keyPressed(keyEvnt);
        assertEquals(1, keyHdl.getValue());
        assertEquals(true, observer.getJump());
    }

    /**
     * Test for checking the Ctrl key pressing
     */
    @Test
    public void testKeyPressedCtrl() {
        keyEvnt.setKeyCode(KeyEvent.VK_CONTROL);
        keyHdl.keyPressed(keyEvnt);
        assertEquals(2, keyHdl.getValue());
        assertEquals(true, observer.getSlide());
        keyHdl.keyReleased(keyEvnt);
        assertEquals(false, observer.getSlide());
    }

    /**
     * Test for checking the X key pressing
     */
    @Test
    public void testKeyPressedX() {
        keyEvnt.setKeyCode(KeyEvent.VK_X);
        keyHdl.keyPressed(keyEvnt);
        assertEquals(3, keyHdl.getValue());
        assertEquals(true, observer.getShoot());
    }

    /**
     * Test for checking that if a no-mapped key has been pressed then the
     * expected value must be different from the enumeration value
     */
    @Test
    public void testKeyPressedNotequal() {
        keyEvnt.setKeyCode(KeyEvent.VK_A);
        keyHdl.keyPressed(keyEvnt);
        assertNotEquals(KeyEvent.VK_A, keyHdl.getValue());
    }

}
