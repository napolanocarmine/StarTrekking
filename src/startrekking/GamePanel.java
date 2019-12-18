package startrekking;

import entity.Player;
import entity.Shot;
import graphics.Sprite;
import graphics.EntitySprite;
import tiles.TileFacade;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import util.KeyHandler;
import util.Position;

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

    public static final int WIDTH = 1600;
    public static final int HEIGHT = 528;

    //nome JFrame
    public static final String NAME = "STAR TREKKING";

    public static int oldFrameCount;
    private static Position map;

    private JFrame frame;
    private Thread thread;
    private boolean running = false;

    private BufferedImage img;
    private Graphics2D g;

    private TileFacade tf;
    private Player player;
    private Player player2;
    private Player player3;
    private Player player4;
    private Sprite hpimg;
    private Sprite font;
    private KeyHandler key;

    private float previousTickHitted = 0;
    private float TickHitted = 0;

    public GamePanel() {

        frame = new JFrame(NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.setIgnoreRepaint(true);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setBackground(Color.YELLOW);
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

    public void init() {

        running = true;

        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();

        map = new Position(0, 0);
        Position.setWorldVar(map.getX(), map.getY());

        key = new KeyHandler();
        addKeyListener(key);

        tf = new TileFacade("tiles/Level1.xml");

        EntitySprite playerSprite = new EntitySprite("entity/wizard", 64, 64);
        player = new Player(playerSprite, new Position(0, 0 + (GamePanel.HEIGHT) - 130), 96, key);

        player2 = new Player(playerSprite, new Position(0 + 2000, 0 + (GamePanel.HEIGHT) - 130), 96, key);
        player3 = new Player(playerSprite, new Position(0 + 1000, 0 + (GamePanel.HEIGHT) - 130), 96, key);
        player4 = new Player(playerSprite, new Position(0 + 1060, 0 + (GamePanel.HEIGHT) - 130), 96, key);
        hpimg = new Sprite("entity/heart.png", 32, 32);
        key.addObserver(player);
        font = new Sprite("font/Font.png", 10, 10);
    }

    @Override
    public void run() {

        init();

        final double GAME_HERTZ = 600.0;  //standard is 600
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

            render(g);
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
        color = frame.getBackground();
        checkCollision();
    }

    public void checkCollision() {
        ArrayList<Shot> shots = player.getShots();
        ListIterator<Shot> li = shots.listIterator();
        while (li.hasNext()) {
            if (li.next().getBounds().collides(player2.getBounds())) {
                li.remove();
            }
        }

        if (player.getBounds().collides(player2.getBounds())) {
            if (System.nanoTime() - previousTickHitted > unitTime) {
                System.err.println("Collisione player");
                player.hitted();
            }
            previousTickHitted = System.nanoTime();

        }
        if (player.getBounds().collides(player3.getBounds())) {
            if (System.nanoTime() - previousTickHitted > unitTime) {
                System.err.println("Collisione player");
                player.hitted();
            }
            previousTickHitted = System.nanoTime();

        }
        if (player.getBounds().collides(player4.getBounds())) {
            if (System.nanoTime() - previousTickHitted > unitTime) {
                System.err.println("Collisione player");
                player.hitted();
            }
            previousTickHitted = System.nanoTime();

        }
    }

    public boolean test;

    public void render(Graphics2D g) {
        if (g != null) {
            test = true;
            g.setColor(new Color(66, 134, 244));
            g.fillRect(0, 0, WIDTH, HEIGHT);
            tf.render(g);
            player.render(g);
            player2.render(g);
            player3.render(g);
            player4.render(g);
            Sprite.drawArray(g, font, "FPS: " + GamePanel.oldFrameCount, new Position(GamePanel.WIDTH - (8 * 40), 10), 40, 40, 32, 0);
            int space = 0;
            for (int i = 0; i < player.getHP(); i++) {
                Sprite.drawArray(g, hpimg.getSprite(0, 0), new Position(space, 10), 90, 90);
                space += 60;
            }
        }
    }

    public boolean drawImage;

    public void draw() {
        Graphics g2 = (Graphics) this.getGraphics();
        drawImage = g2.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        g2.dispose();
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
    }

    public static Position getMapPos() {
        return map;
    }

}
