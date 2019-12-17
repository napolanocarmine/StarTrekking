package startrekking;

import entity.Player;
import graphics.Sprite;
import tiles.TileFacade;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import util.KeyHandler;
import util.Position;

public final class GameFrame extends JFrame{

    /*
    Responsabilità:
    aprire e disegnare il frame, thread di aggiornamento
    
    Operazioni:
    1- update ciclico di Map e Entity
    2- Riferimenti di oggetti Map, Entity, KeyHandler, TileFacade
     */
    //dimensione finestra
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 528;

    //nome JFrame
    public static final String NAME = "STAR TREKKING";

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
    private GamePanel gamepanel;
    
    public GameFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamepanel = new GamePanel();
        this.add(gamepanel);
        //this.setContentPane(gamepanel);
        this.setIgnoreRepaint(false);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setBackground(Color.YELLOW);
        setFocusable(true);
        requestFocus();
        gamepanel.startThread();
    }
    
    public static Position getMapPos() {
        return map;
    }

   private class GamePanel extends JPanel implements Runnable{
       
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
           this.addKeyListener(key);

           tf = new TileFacade("tiles/Level1.xml");

           player = new Player(new Sprite("entity/mage.png", 64, 64), new Position(0 + 32, 0 + (GameFrame.HEIGHT) - 130), 96, key);

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

       public Color color;

       public void update() {
           Position.setWorldVar(map.getX(), map.getY());
           player.updateGame();
           //color = frame.getBackground();
       }

       public boolean test;

      
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
               Sprite.drawArray(g2d, font, "FPS: " + GameFrame.oldFrameCount, new Position(GameFrame.WIDTH - (8 * 40), 40), 40, 40, 32, 0);
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

//    public static void main(String[] args) {
//        GamePanel gamePanel = new GamePanel();
//    }
//        public Position getMapPos() {
//            return map;
//        }
   }

}
    
