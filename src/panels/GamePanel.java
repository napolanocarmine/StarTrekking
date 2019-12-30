package panels;

import gameObjects.Enemy;
import gameObjects.Level;
import gamefactory.LevelFactory;
import gamestate.StoryPlayState;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import util.KeyHandler;

/**
 *
 * @author StarTrekking
 *
 * Class responsible to open and draw the frame and manage the updating thread
 * of the User Interface
 *
 * Operations: - update in loop of the Map and Entity; - manage the refers to
 * Map, Entity, KeyHandler and TileFacade objects
 */
public class GamePanel extends JPanel implements Runnable {

    //window dimensions    
    public static float unitTime = 700000000;

    public static final int WIDTH = GameFrame.WIDTH;
    public static final int HEIGHT = GameFrame.HEIGHT;

    //JFrame name
    public static final String NAME = "STAR TREKKING";

    public static int oldFrameCount;

    private Thread thread;
    private boolean running;

    private BufferedImage img;
    private Graphics2D g;

    private StoryPlayState sps;
    private Level level;
    private KeyHandler key;
    private boolean pause;
    private LevelFactory levelFactory;
    

    /**
     *
     * @param sps is the state of the game for a story mode This method
     * initializes an array to keep refers to enemies
     */
    public GamePanel(StoryPlayState sps) {
        this.sps = sps;
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.running = false;
        this.pause = false;
        setFocusable(true);
        requestFocus();
        key = new KeyHandler(sps);
        levelFactory = new LevelFactory(); 
//        startThread();
    }

    /**
     * Method to start a new thread that manages the behavior of the game
     */
    public final void startThread() {
        if (thread == null) {
            System.err.println("Nuovo Thread");
            thread = new Thread(this, "GameThread");
            thread.setDaemon(true);
            thread.start();
        }
    }

    public final void restart() {
        if (thread != null) {
            running = false;
            thread = null;
//            init();
//            setPause(true);
        }
    }

    /**
     * Method to initialize the image shown as background
     */
    public void init() {
        running = true;

        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();

        setKeyH();
       
    }

    
    public void setLevel(int code){
        switch(code){
            case 1:
                System.err.println("Carico il livello 1");
                level = (Level)levelFactory.build(1);
                break;
            case 2:
               System.err.println("Carico il livello 2");
               level = (Level)levelFactory.build(2);
                break;
            case 3:
                System.err.println("Carico il livello 3");
                level = (Level)levelFactory.build(3);
                break;
        }
        level.setPanel(this);
    }
    
    /**
     * Method to add a KeyListner object to capture pressed keys
     */
    private void setKeyH() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setFocusable(true);
                requestFocusInWindow();
            }
        });
        addKeyListener(key);
    }
    
    public KeyHandler getKeyH() {
        return key;
    }

    /**
     * Method that updates the UI for each frame
     */
    @Override
    public void run() {

        init();

        final double GAME_HERTZ = 600.0;  //standard is 600
        //final double TBU = 1000000000 / GAME_HERTZ;
        final double TBU = 1000000000 / GAME_HERTZ;

        final int MUBR = 80;

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60;
        final double TTBR = 1000000000 / TARGET_FPS;

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        oldFrameCount = 0;

        while (running) {
            //System.out.println("VEDO SE MI INTRAPPOLO");
            isInPause();
            //System.out.println("PROCEDO");
            double now = System.nanoTime();
            int updateCount = 0;
            while (((now - lastUpdateTime) > TBU) && (updateCount < MUBR)) {
                update();
                lastUpdateTime += TBU;
                updateCount++;
            }

            if (now - lastUpdateTime > TBU) {
                lastUpdateTime = now - TBU;
            }

//            render(g);
//            draw();
            render();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    repaint();
                }
            });

            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime) {
                if (frameCount != oldFrameCount) {
                    System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while (now - lastRenderTime < TTBR && now - lastUpdateTime < TBU) {
                Thread.yield();

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("ERROR: yielding thread");
                }
                now = System.nanoTime();
            }
        }

    }

    public Color color;

    public void update() {
        level.updateGame();
    }

    public void render() {
        if (g != null) {
            level.render(g);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        g.dispose();
    }

    /**
     *
     * @return the width of the frame
     */
    public static int getWIDTH() {
        return WIDTH;
    }

    /**
     *
     * @return the height of the frame
     */
    public static int getHEIGHT() {
        return HEIGHT;
    }

    /**
     *
     * @return the refers to the thread
     */
    public Thread getThread() {
        return thread;
    }

    /**
     *
     * @param code about which is the next state
     */
    public void setState(int code) {
        sps.handleNext(code);
    }
    
    private synchronized void isInPause(){
        while(pause){
            try {
                System.out.println("ENTRO IN PAUSA, ADDIO");
                wait();
                System.out.println("PIPPO");
            } catch (InterruptedException ex) {
                System.out.println("INTERRUPTED EXCEPTION");
            }
        }
        setKeyH();   
    }
    
    
    public synchronized void setPause(boolean pause){
        this.pause = pause;
        notifyAll();
    }
    
}
