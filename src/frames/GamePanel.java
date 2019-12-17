/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import entity.Player;
import gamestate.StoryPlayState;
import graphics.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import tiles.TileFacade;
import util.KeyHandler;
import util.Position;

/**
 *
 * @author Gianluca
 */
public class GamePanel extends JPanel implements Runnable {

    private Thread thread;
    private boolean running = false;

    private BufferedImage img;
    private Graphics2D g;

    private TileFacade tf;
    private Player player;
    private Sprite font;
    private KeyHandler key;
    private GameFrame gameFrame;
    public static int oldFrameCount;
    
    private static Position map;
    
    
    public GamePanel(GameFrame gameFrame){
        this.gameFrame = gameFrame;
        init();
        startThread();
    }

    public void startThread() {
        //super.addNotify();
        if (thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    public void init() {

        running = true;

        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();

        map = new Position(0, 0);
        Position.setWorldVar(map.getX(), map.getY());

        key = new KeyHandler();
        gameFrame.addKeyListener(key);

        tf = new TileFacade("tiles/Level1.xml");

        player = new Player(new Sprite("entity/mage.png", 64, 64), new Position(0 + 32, 0 + (GameFrame.HEIGHT) - 130), 96, key, (StoryPlayState) gameFrame.getFrameState());
        key.addObserver(player);

        font = new Sprite("font/Font.png", 10, 10);
    }

    @Override
    public void run() {
        
        final double GAME_HERTZ = 600.0;
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
            this.repaint();
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

            paint(g);
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
                } catch (InterruptedException e) {
                    System.out.println("ERROR: yielding thread");
                }
                now = System.nanoTime();
            }
            
        }
        
    }

    public void update() {
        Position.setWorldVar(map.getX(), map.getY());
        player.updateGame();
    }

    private boolean test;

    @Override
    public void paint(Graphics g) {
        /*
         g.setColor(Color.RED);
         g.fillRect(100, 100, 64, 64);
         */
        Graphics2D g2d = (Graphics2D) g;
        if (g2d != null) {
            test = true;
            g2d.setColor(new Color(66, 134, 244));
            g2d.fillRect(0, 0, WIDTH, HEIGHT);
            tf.render(g2d);
            player.render(g2d);
            Sprite.drawArray(g2d, font, "FPS: " + oldFrameCount, new Position(GamePanel.WIDTH - (8 * 40), 40), 40, 40, 32, 0);
        }
        //Sprite.drawArray(g, font, "FPS: " + GamePanel.oldFrameCount , new Vector2f(GamePanel.width - (8 * 40) , 10), 40, 40, 32, 0);    
    }
    public boolean drawImage;

    public void draw() {
        Graphics g2 = (Graphics) this.getGraphics();
        drawImage = g2.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        g2.dispose();
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }
    
    
    public static Position getMapPos() {
        return map;
    }


}
