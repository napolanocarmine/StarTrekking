/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startrekking;

import entity.Player;
import graphics.Sprite;
import java.awt.*;
import java.awt.image.*;
import javax.swing.JPanel;
import tiles.TileFacade;
import util.KeyHandler;
import util.Position;

/**
 *
 * @author Star Trekking
 */
public class GamePanel extends JPanel implements Runnable{


   
    
    public static int oldFrameCount;
    private static Position map;

    private Thread thread;
    private boolean running = false;

    private BufferedImage img;
    private Graphics2D g;

    private TileFacade tf;
    private Player player;
    private Sprite font;
    private KeyHandler key;
    private int width,height;
    
   
    public GamePanel(int WIDTH, int HEIGHT){
        this.width = WIDTH;
        this.height = HEIGHT;
        setPreferredSize(new Dimension(width,height));
        setFocusable(true);
        requestFocus();
        startThread();
    }
    
    
    public final void startThread() {
        //super.addNotify();
        if (thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    public void init() {

        running = true;

        img = new BufferedImage(this.width,this.height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();

        map = new Position(0, 0);
        Position.setWorldVar(map.getX(), map.getY());

        key = new KeyHandler();
        this.addKeyListener(key);

        tf = new TileFacade("tiles/Level1.xml");

        player = new Player(new Sprite("entity/mage.png", 64, 64), new Position(0 + 32, 0 + (this.height) - 130), 96, key);

        key.addObserver(player);

        font = new Sprite("font/Font.png", 10, 10);
    }

    @Override
    public void run() {

        init();

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
                } catch (InterruptedException e) {
                    System.out.println("ERROR: yielding thread");
                }
                now = System.nanoTime();
            }
        }

    }

    public Color color;

    public void update() {
        Position.setWorldVar(map.getX(), map.getY());
        player.updateGame();
        //color = frame.getBackground();
    }

    public boolean test;

    public void render(){
        if(g != null){
//            g.setColor(new Color(66, 134, 244));
//            g.fillRect(0, 0, width, height);
            tf.render(g);
            player.render(g);
            Sprite.drawArray(g, font, "FPS: " + GamePanel.oldFrameCount, new Position(this.width - (8 * 40), 40), 40, 40, 32, 0);            //gsm.render(g);
        }
    }
//    @Override
//    public void paint(Graphics g) {
//        /*
//        g.setColor(Color.RED);
//        g.fillRect(100, 100, 64, 64);
//         */
//        Graphics2D g2d = (Graphics2D) g;
//        if (g2d != null) {
//            test = true;
//            g2d.setColor(new Color(66, 134, 244));
//            g2d.fillRect(0, 0, width, height);
//            tf.render(g2d);
//            player.render(g2d);
//            Sprite.drawArray(g2d, font, "FPS: " + GamePanel.oldFrameCount, new Position(GamePanel.width - (8 * 40), 40), 40, 40, 32, 0);
//        }
//        //Sprite.drawArray(g, font, "FPS: " + GamePanel.oldFrameCount , new Vector2f(GamePanel.width - (8 * 40) , 10), 40, 40, 32, 0);    
//    }
    
    

    public boolean drawImage;

    public void draw() {
        Graphics g2 = (Graphics) this.getGraphics();
        drawImage = g2.drawImage(img, 0, 0, this.width, this.height, null);
        g2.dispose();
    }

    public static Position getMapPos() {
        return map;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

//    public static void main(String[] args) {
//        GamePanel gamePanel = new GamePanel();
//    }
//        public Position getMapPos() {
//            return map;
//        }
    
}
