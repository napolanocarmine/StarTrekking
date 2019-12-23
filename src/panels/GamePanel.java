package panels;

import gameObjects.GroundEnemy;
import gameObjects.Level;
import gamestate.State;
import gamestate.StoryPlayState;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import util.KeyHandler;

public class GamePanel extends JPanel implements Runnable {

    /*
    Responsabilità:
    aprire e disegnare il frame, thread di aggiornamento
    
    Operazioni:
    1- update ciclico di Map e Entity
    2- Riferimenti di oggetti Map, Entity, KeyHandler, TileFacade
     */
    //dimensione finestra
    
    public static float unitTime = 700000000;

    public static final int WIDTH = GameFrame.WIDTH;
    public static final int HEIGHT = GameFrame.HEIGHT;

    //nome JFrame
    public static final String NAME = "STAR TREKKING";
    
    public static int oldFrameCount;

    private Thread thread;
    private boolean running = false;

    private BufferedImage img;
    private Graphics2D g;

    
    private ArrayList<GroundEnemy> goblins; 
    private StoryPlayState sps;
    private Level level;
    

    public GamePanel(StoryPlayState sps) {
        this.goblins = new ArrayList<>();
        this.sps = sps;
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        startThread();

    }

    public final void startThread() {
        if (thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    public final void stopThread() {
        if (thread != null) {
            thread.stop();
        }
    }

    public void init() {
        running = true;

        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();
        
        KeyHandler k = setKeyH();
        
        this.level = new Level(k,this);
        
    }
    
    private KeyHandler setKeyH(){
        setFocusable(true);
        requestFocusInWindow();
        KeyHandler key = new KeyHandler();
        addKeyListener(key);
        return key;
    }

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
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        g.dispose();
    }

    public static int getWIDTH() { return WIDTH; }
    public static int getHEIGHT() { return HEIGHT; }
    public Thread getThread(){ return thread; }
    public void setState(int code) { sps.handleNext(code); }
    
//
//    public static void main(String[] args) {
//        GamePanel gamePanel = new GamePanel();
//    }


}


