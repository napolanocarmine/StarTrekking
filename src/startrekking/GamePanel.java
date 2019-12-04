package startrekking;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import util.KeyHandler;

/**
 *
 * @author CARMINE
 */
public class GamePanel extends Canvas implements Runnable {

    /*
    Goal:
    1. Open and draw the frame
    2. Updating thread
    
    Operations:
    1. Looping update of Map and Entity
    2. References to Map, Entity, KeyHandler and TileFacade objects
     */
    //window dimensions
    public static final int WIDTH = 300;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 3;

    //JFrame name
    public static final String NAME = "STAR TREKKING";

    public static int oldFrameCount;

    private JFrame frame;
    private Thread thread;
    private boolean running = false;

    private BufferedImage img;
    private Graphics2D g;

    private TileFacade tf;

    public KeyHandler key;

    public GamePanel() {
        //setting window dimensions
        setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame = new JFrame(NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    public void init() {

        running = true;

        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();

        key = new KeyHandler(this);

        tf = new TileFacade();

    }

    @Override
    public void run() {

        init();

        final double GAME_HERTZ = 60.0;
        final double TBU = 1000000000 / GAME_HERTZ;

        final int MUBR = 5;

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60;
        final double TTBR = 1000000000 / TARGET_FPS;

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        oldFrameCount = 0;

        while (running) {

            double now = System.nanoTime();
            int updateCount = 0;
            while (((now - lastUpdateTime) > TBU) && (updateCount < MUBR)) {
                update();
                input(key);
                lastUpdateTime += TBU;
                updateCount++;
            }

            if (now - lastUpdateTime > TBU) {
                lastUpdateTime = now - TBU;
            }

            input(key);

            render();
            draw();

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
                } catch (Exception e) {
                    System.out.println("ERROR: yielding thread");
                }
                now = System.nanoTime();
            }
        }

    }

    public void update() {
        tf.update();
    }

    public void render() {
        if (g != null) {
            g.setColor(new Color(66, 134, 244));
            g.fillRect(0, 0, WIDTH, HEIGHT);
            tf.render(g);
        }
    }

    public void draw() {
        Graphics g2 = (Graphics) this.getGraphics();
        g2.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        g2.dispose();
    }

    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
    }

    private void input(KeyHandler key) {
        tf.input(key);
    }

}
