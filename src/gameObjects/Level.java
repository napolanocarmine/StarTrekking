package gameObjects;

import gameObjects.entityState.EnemyDeadState;
import gameObjects.entityState.PlayerDeadState;
import gamefactory.EnemiesFactory;
import gamestate.StoryPlayState;
import graphics.EntitySprite;
import graphics.Sprite;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import panels.GameFrame;
import panels.GamePanel;
import static panels.GamePanel.unitTime;
import startrekking.GameLauncher;
import tiles.LayerFacade;
import util.EntityBox;
import util.KeyHandler;
import util.Position;

public class Level extends Assembly{

//    private int level;
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
    
    public Level(){
        this.ef = new EnemiesFactory();
    }
    
    public void setPanel(GamePanel gp){
        this.gp = gp;
        KeyHandler key = gp.getKeyH();
        key.setPlayer(player);
    }
    
    protected void init(){
        
        map = new Position(0, 0);
        Position.setWorldVar(map.getX(), map.getY());
        
        EntitySprite playerSprite = new EntitySprite("entity/wizard", 64, 64);
        
        player = new Player(playerSprite, new Position(0, 0 + groundY), 96);
        
        hpImg = new Sprite("entity/heart.png", 32,32);
        energyImg = new Sprite("entity/energy.png", 96, 96);
        font = new Sprite("font/Font.png", 10, 10);
        addObj(player);
      
    }
    
    
    @Override
    public void updateGame() {
        Position.setWorldVar(map.getX(), map.getY());
        player.updateGame();
        
        for(GameObject leaf : enemies.getObjs()){
            Enemy enemy = (Enemy) leaf; 
            if(enemy.getPos().getWorldVar().getX() < GamePanel.WIDTH+100) enemy.updateGame();
        }
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
                    gp.setState(3);
                }
            }); 
        }
        checkCollision();
        removeEnemies();
    }

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
    
    public static Position getMapPos() {
        return map;
    }
    
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
    
    /*
    if an enemy die, he must be removed from the array of the enemies
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
    
    
    public Player getPlayer(){
        return player;
    }
}
