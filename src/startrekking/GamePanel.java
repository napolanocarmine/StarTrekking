package startrekking;

import entity.Player;
import graphics.Sprite;
import tiles.TileFacade;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import util.KeyHandler;
import util.Position;

public class GamePanel extends JPanel implements Runnable{
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
    public static Position map;
    
    private JFrame frame;
    private Thread thread;
    private boolean running = false;
    
    private BufferedImage img;
    private Graphics2D g;
    
    private TileFacade tf;
    private Player player;
    private Sprite font;
    private KeyHandler key;
    

    public GamePanel() {
        //settiamo le dimensioni della finestra
//        setMinimumSize(new Dimension(WIDTH, HEIGHT));
//        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        frame = new JFrame(NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.setIgnoreRepaint(true);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        setFocusable(true);
        requestFocus();
        startThread();
        
        
    }
    
    public void startThread(){
        //super.addNotify();
        if(thread == null){
            System.out.println("check");
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }
    
    public void init(){
        
        running = true;
        
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();
        
        map = new Position(0, 0);
        Position.setWorldVar(map.getX(), map.getY());
        
        key = new KeyHandler();
        addKeyListener(key);
        
        tf = new TileFacade("tiles/LevelOne.xml");
        player = new Player(new Sprite("entity/mage.png", 64, 64), new Position(0 + 32, 0 + (GamePanel.HEIGHT) - 126), 96, key);
        font = new Sprite("font/Font.png", 10, 10);
        
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
        
        while(running){
            
            double now = System.nanoTime();
            int updateCount = 0;
            while(((now - lastUpdateTime) > TBU) && (updateCount < MUBR)){
                update();
                lastUpdateTime += TBU;
                updateCount++;
            }
            
            if(now - lastUpdateTime > TBU){
                lastUpdateTime = now - TBU;
            }
            
            render(g);
            draw();
            
            lastRenderTime = now;
            frameCount++;
            
            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if(thisSecond > lastSecondTime){
                if(frameCount != oldFrameCount){
                    System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }
            
            while(now - lastRenderTime < TTBR && now - lastUpdateTime < TBU){
                Thread.yield();
                
                try{
                    Thread.sleep(1);
                }catch(Exception e){
                    System.out.println("ERROR: yielding thread");
                }
                now = System.nanoTime();
            }
        }
        
    }
    
    public void update(){
        Position.setWorldVar(map.getX(), map.getY());
        player.updateGame();
    }
    
    public void render(Graphics2D g){
        /*
        g.setColor(Color.RED);
        g.fillRect(100, 100, 64, 64);
        */
        if(g != null){
            g.setColor(new Color(66, 134, 244));
            g.fillRect(0, 0, WIDTH, HEIGHT);
            tf.render(g);
            player.render(g); 
            Sprite.drawArray(g, font, "FPS: " + GamePanel.oldFrameCount , new Position(GamePanel.WIDTH - (8 * 40) , 10), 40, 40, 32, 0);
        }
        //Sprite.drawArray(g, font, "FPS: " + GamePanel.oldFrameCount , new Vector2f(GamePanel.width - (8 * 40) , 10), 40, 40, 32, 0);    
    }
    
     public void draw(){
        Graphics g2 = (Graphics) this.getGraphics();
        g2.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        g2.dispose();
    }
    
    public static void main(String[] args){
        new GamePanel();
    }
    
    
}
