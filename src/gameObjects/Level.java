package gameObjects;

import frames.GameFrame;
import frames.GamePanel;
import static frames.GamePanel.unitTime;
import gamestate.StoryPlayState;
import graphics.EntitySprite;
import graphics.Sprite;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.ListIterator;
import tiles.TileFacade;
import util.EntityState;
import util.KeyHandler;
import util.Position;

public class Level extends Assembly{

    private int level;
    private static Position map;
    private KeyHandler key;
    private TileFacade tf;
    private Player player;
    private Assembly groundEnemies;
    private Sprite hpimg;
    private Sprite font;
    private float previousTickHitted = 0;
    private GamePanel gp;
    private float groundY = (GamePanel.HEIGHT) - 162;
    
    public Level(KeyHandler key, GamePanel gp){
        this.groundEnemies = new Assembly();
        this.level = StoryPlayState.level;
        this.key = key;
        this.gp = gp;
        init();
    }
    
    private void init(){
        
        map = new Position(0, 0);
        Position.setWorldVar(map.getX(), map.getY());
        
        EntitySprite playerSprite = new EntitySprite("entity/wizard", 64, 64);
        EntitySprite goblinSprite = new EntitySprite("entity/goblin", 64, 64);
        EntitySprite skeletonSprite = new EntitySprite("entity/skeleton", 64, 64);
        
        System.out.println("gameObjects.Level.init() -> level: " + level);
        if(level == 1){
            tf = new TileFacade("tiles/LevelOne.xml");
            groundEnemies.addObj(new GroundEnemy(goblinSprite, new Position(1000, groundY) , 96));
            groundEnemies.addObj(new GroundEnemy(goblinSprite, new Position(1500, groundY) , 96));
            groundEnemies.addObj(new GroundEnemy(goblinSprite, new Position(2100, groundY) , 96));
            groundEnemies.addObj(new GroundEnemy(goblinSprite, new Position(3080, groundY) , 96));
            groundEnemies.addObj(new GroundEnemy(goblinSprite, new Position(4700, groundY) , 96));
            groundEnemies.addObj(new GroundEnemy(goblinSprite, new Position(5500, groundY) , 96));
            groundEnemies.addObj(new GroundEnemy(goblinSprite, new Position(7400, groundY) , 96));
            groundEnemies.addObj(new GroundEnemy(goblinSprite, new Position(7400, 220) , 96));
            groundEnemies.addObj(new GroundEnemy(goblinSprite, new Position(9500, groundY) , 96));
            player = new Player(playerSprite, new Position(0, 0 + groundY), 96, key);
        }
        if(level == 2){
            tf = new TileFacade("tiles/LevelTwo.xml");
            groundEnemies.addObj(new GroundEnemy(skeletonSprite, new Position(1000, groundY) , 105));
            groundEnemies.addObj(new GroundEnemy(skeletonSprite, new Position(1700, 340) , 105));
            groundEnemies.addObj(new GroundEnemy(skeletonSprite, new Position(3200, groundY) , 105));
            groundEnemies.addObj(new GroundEnemy(skeletonSprite, new Position(3600, 210) , 105));
            groundEnemies.addObj(new GroundEnemy(skeletonSprite, new Position(5000, 220) , 105));
            groundEnemies.addObj(new GroundEnemy(skeletonSprite, new Position(6000, groundY) , 105));
            groundEnemies.addObj(new GroundEnemy(skeletonSprite, new Position(7200, groundY) , 105));
            groundEnemies.addObj(new GroundEnemy(skeletonSprite, new Position(8000, groundY) , 105));
            groundEnemies.addObj(new GroundEnemy(skeletonSprite, new Position(10000, groundY) , 105));
            groundEnemies.addObj(new GroundEnemy(skeletonSprite, new Position(12000, groundY) , 105));
            player = new Player(playerSprite, new Position(0, 0 + groundY), 105, key);
        }
        
        addObj(player);
        addObj(groundEnemies);
        hpimg = new Sprite("entity/heart.png", 32,32);
        key.addObserver(player);
        font = new Sprite("font/Font.png", 10, 10);
    }
    
    @Override
    public void updateGame() {
        Position.setWorldVar(map.getX(), map.getY());
        player.updateGame();
        
        for(GameObject leaf : groundEnemies.getObjs()){
            GroundEnemy enemy = (GroundEnemy) leaf; 
            if(enemy.getPos().getWorldVar().getX() < GameFrame.WIDTH+100) enemy.updateGame();
        }
        
        if(player.getDead()) {
            gp.getState().handleNext(1);
            gp.getThread().stop();
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
        
        Sprite.drawArray(g, font, "FPS: " + GamePanel.oldFrameCount, new Position(GamePanel.WIDTH - (8 * 40), 10), 40, 40, 32, 0);
        int space = 0;
        for (int i = 0; i < player.getHP(); i++) {
            Sprite.drawArray(g, hpimg.getSprite(0, 0), new Position(space, 10), 90, 90);
            space += 60;
        }
    }
    
    public static Position getMapPos() {
        return map;
    }
    
    public void checkCollision() {
        ArrayList<Shot> shots = player.getShots();
        ListIterator<Shot> shotListIter = shots.listIterator();
        
        while(shotListIter.hasNext()){
            Shot s = shotListIter.next();
            for(GameObject leaf : groundEnemies.getObjs()){
                GroundEnemy enemy = (GroundEnemy) leaf; 
                if(enemy.getState() != EntityState.DEAD && s.getBounds().collides(enemy.getBounds())){
                    System.err.println("Nemico colpito");
                    shotListIter.remove();
                    enemy.isDead();
                }
            }
        }
        
        for(GameObject leaf : groundEnemies.getObjs()){
            GroundEnemy enemy = (GroundEnemy) leaf; 
            if(enemy.getState() != EntityState.DEAD && enemy.getBounds().collides(player.getBounds())){
                if(System.nanoTime() - previousTickHitted > unitTime){
                    System.err.println("Collisione player");
                    player.hitted();    
                }
                previousTickHitted = System.nanoTime();
            }
        }
    }
    
    
    private void removeEnemies(){
        ListIterator<GameObject> groundEnemiesLi = groundEnemies.getObjs().listIterator();
        
        while(groundEnemiesLi.hasNext()){
            GroundEnemy cEnemy = (GroundEnemy)(groundEnemiesLi.next());
            if(cEnemy.getDead()){
                groundEnemiesLi.remove();
            }
        }
    }
    
}
