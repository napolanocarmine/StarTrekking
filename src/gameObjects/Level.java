package gameObjects;

import gameObjects.entityState.EnemyDeadState;
import gamefactory.EnemiesFactory;
import util.graphics.EntitySprite;
import util.graphics.Sprite;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.SwingUtilities;
import panels.GameFrame;
import panels.GamePanel;
import static panels.GamePanel.unitTime;
import tiles.LayerFacade;
import KeyHandler.KeyHandler;
import entitycommand.PlayerCommandInvoker;
import util.Position;

/**
 * Abstract Class to keep references to Assembly
 * @author Star Trekking
 */
public abstract class Level extends Assembly{

    private static Position map;
    protected LayerFacade tf;
    protected EnemiesFactory ef;
    protected Player player;
    protected EnemiesLevel enemies;
    private Sprite hpImg;
    private Sprite energyImg;
    private Sprite font;
    private float previousTickHitted = 0;
    private GamePanel gp;
    protected float groundY = (GamePanel.HEIGHT) - 162;
    protected PlayerCommandInvoker inv;
    
    /**
     * Costructor of the Level
     */
    public Level(){
        this.ef = new EnemiesFactory();
    }
    
    /**
     * Setter of the GamePanel of Level
     * @param gp 
     */
    public void setPanel(GamePanel gp){
        this.gp = gp;
        KeyHandler key = gp.getKeyH();
        key.setListener(inv);
    }
    
    /**
     * This method initialize all the GameObject of the Level
     */
    protected void init(){
        
        map = new Position(0, 0);
        Position.setWorldVar(map.getX(), map.getY());
        
        EntitySprite playerSprite = new EntitySprite("entity/wizard", 64, 64);
        
        player = new Player(playerSprite, new Position(0, 0 + groundY), 96);
        
        inv = new PlayerCommandInvoker(player);
        
        hpImg = new Sprite("entity/heart.png", 32,32);
        energyImg = new Sprite("entity/energy.png", 96, 96);
        font = new Sprite("font/Font.png", 10, 10);
        addObj(player);
      
    }
    
    /**
     * Performs the update operation above all the GameObjects.
     */
    @Override
    public void updateGame() {
        super.updateGame();
        Position.setWorldVar(map.getX(), map.getY());
        
        if(player.getDeadAniEnded()) {
            gp.stopThread(); //cancelliamo il thread. 
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    gp.setState(1);
                }
            }); 
        }
        
        if(player.isWinner()){
            gp.stopThread(); //cancelliamo il thread. 
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    gp.setState(2);
                }
            }); 
        }
        checkCollision();
        removeEnemies();
    }

    /**
     * Perform the render operation above all the GameObjects.
     * @param g Graphics2D object.
     */
    @Override
    public void render(Graphics2D g) {
        tf.render(g);
        
        for(GameObject gobj: objs){
            gobj.render(g);
        }
        
        //Sprite.drawArray(g, font, "FPS: " + GamePanel.oldFrameCount, new Position(GamePanel.WIDTH - (8 * 40), 10), 40, 40, 32, 0);
        int space = 0;
        for (int i = 0; i < player.getHP(); i++) {
            Sprite.drawArray(g, hpImg.getSprite(0, 0), new Position(space, 10), 90, 90);
            space += 60;
        }
        
        
        space = GameFrame.WIDTH - energyImg.w;
        for (int i = 0; i < player.getMana(); i++) {
            Sprite.drawArray(g, energyImg.getSprite(0, 0), new Position(space, 10), energyImg.w, energyImg.w);
            space -= 75;
        }
    }
    
    /**
     * Getter of the Position of the Map
     * @return Map's position
     */
    public static Position getMapPos() {
        return map;
    }
    
    /**
     * This method check the collision among all the entity of the level
     */
    public void checkCollision() {
        ArrayList<Shot> shots = player.getShots();
        ListIterator<Shot> shotListIter = shots.listIterator();
        
        /*
        check if a shot hit a monster
        */
        while(shotListIter.hasNext()){
            Shot s = shotListIter.next();
            for(GameObject leaf : enemies.getObjs()){
                Enemy enemy = (Enemy) leaf; 
                if(!(enemy.getState() instanceof EnemyDeadState) && s.getBounds().collides(enemy.getBounds())){
                    System.err.println("Nemico colpito");
                    shotListIter.remove();
                    enemy.setState(new EnemyDeadState(enemy));
                }
            }
        }
        
        /*
        if the time that has passed from the last time the player have been hitted is longer than unitTime (invincible time)
            check if the player hit an obstacle or a monster, in which case call the hitted method of player.
        */
        if(System.nanoTime() - previousTickHitted > unitTime){
            if(player.hitObs()){
                player.hitted();
                previousTickHitted = System.nanoTime();
            }else{
                for(GameObject leaf : enemies.getObjs()){
                    Enemy enemy = (Enemy) leaf; 
                    if(!(enemy.getState() instanceof EnemyDeadState) && enemy.getBounds().collides(player.getBounds())){
                            System.err.println("Collisione player");
                            player.hitted();
                            previousTickHitted = System.nanoTime();
                    }
                }
            }
        } 
    }
    
    /**
     * This method remove a enemy if die, he must be removed from the array of the enemies
     */
    private void removeEnemies(){
        ListIterator<GameObject> groundEnemiesLi = enemies.getObjs().listIterator();
        while(groundEnemiesLi.hasNext()){
            Enemy cEnemy = (Enemy)(groundEnemiesLi.next());
            if(cEnemy.getDeadAniEnded()){
                groundEnemiesLi.remove();
            }
        }
    }
    
    /**
     * This method return the player of the level
     * @return player of the level
     */
    public Player getPlayer(){
        return player;
    }
}
