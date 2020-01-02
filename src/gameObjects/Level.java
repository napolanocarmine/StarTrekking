package gameObjects;

import gameObjects.entityState.GroundEnemyDeadState;
import gameObjects.entityState.PlayerDeadState;
import gamestate.StoryPlayState;
import graphics.EntitySprite;
import graphics.Sprite;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.ListIterator;
import panels.GamePanel;
import static panels.GamePanel.unitTime;
import tiles.TileFacade;
import util.AABB;
import util.KeyHandler;
import util.Position;

public class Level extends Assembly{

    private int level;
    private static Position map;
    private TileFacade tf;
    private Player player;
    private Assembly groundEnemies;
    private Sprite hpimg;
    private Sprite font;
    private float previousTickHitted = 0;
    private GamePanel gp;
    private float groundY = (GamePanel.HEIGHT) - 162;
    
    public Level(GamePanel gp){
        this.groundEnemies = new Assembly();
        this.level = StoryPlayState.level;
        this.gp = gp;
        init();
    }
    
    private void init(){
        
        map = new Position(0, 0);
        Position.setWorldVar(map.getX(), map.getY());
        
        EntitySprite playerSprite = new EntitySprite("entity/wizard", 64, 64);
        EntitySprite enemieSprite;
        
        player = new Player(playerSprite, new Position(0, 0 + groundY), 96);
        KeyHandler key = gp.getKeyH();
        key.setPlayer(player);
        
        switch (level) {
            case 1:
                tf = new TileFacade("tiles/LevelOne.xml");
                enemieSprite = new EntitySprite("entity/goblin", 64, 64);
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(1000, groundY) , 96));
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(1500, groundY) , 96));
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(2100, groundY) , 96));
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(3080, groundY) , 96));
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(4700, groundY) , 96));
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(5500, groundY) , 96));
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(7400, groundY) , 96));
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(7400, 220) , 96));
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(9500, groundY) , 96));
                break;
            case 2:
                player.setStandBounds(new AABB(player.getPos(), 16, 24, 40, 32));
                player.setCrouchBounds(new AABB(player.getPos(), 16, 12, 40, 44));
                player.setBounds(player.getStandBounds());
                tf = new TileFacade("tiles/LevelTwo.xml");
                enemieSprite = new EntitySprite("entity/skeleton", 64, 64);
                int enemieSize = 96;
                groundY += 10;
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(1000, groundY) , enemieSize));
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(1700, 340) , enemieSize));
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(3200, groundY) , enemieSize));
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(3600, 210) , enemieSize));
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(5000, 220) , enemieSize));
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(6000, groundY) , enemieSize));
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(7200, groundY) , enemieSize));
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(8000, groundY) , enemieSize));
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(10000, groundY) , enemieSize));
                groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(12000, groundY) , enemieSize));
                break;
            default:
                System.err.println("[map index: "+level+"] map file error.");
                break;
        }
        
        
//        groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(1000, groundY) , 96));
//        groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(1500, groundY) , 96));
//        groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(2100, groundY) , 96));
//        groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(3080, groundY) , 96));
//        groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(4700, groundY) , 96));
        addObj(player);
        addObj(groundEnemies);
        hpimg = new Sprite("entity/heart.png", 32,32);
        font = new Sprite("font/Font.png", 10, 10);
    }
    
    @Override
    public void updateGame() {
        Position.setWorldVar(map.getX(), map.getY());
        player.updateGame();
        
        for(GameObject leaf : groundEnemies.getObjs()){
            GroundEnemy enemy = (GroundEnemy) leaf; 
            if(enemy.getPos().getWorldVar().getX() < GamePanel.WIDTH+100) enemy.updateGame();
        }
        if(player.getDeadAniEnded()) {
            gp.setState(1);
            gp.reset();
        }
        if(player.getVictory()){
            gp.setPause(true);
            gp.setState(3);
            System.out.println("cambio stato");
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
        
        /*
        check if a shot hit a monster
        */
        while(shotListIter.hasNext()){
            Shot s = shotListIter.next();
            for(GameObject leaf : groundEnemies.getObjs()){
                GroundEnemy enemy = (GroundEnemy) leaf; 
                if(!(enemy.getState() instanceof PlayerDeadState) && s.getBounds().collides(enemy.getBounds())){
                    System.err.println("Nemico colpito");
                    shotListIter.remove();
                    enemy.setState(new GroundEnemyDeadState(enemy));
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
                for(GameObject leaf : groundEnemies.getObjs()){
                    GroundEnemy enemy = (GroundEnemy) leaf; 
                    if(!(enemy.getState() instanceof PlayerDeadState) && enemy.getBounds().collides(player.getBounds())){
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
        ListIterator<GameObject> groundEnemiesLi = groundEnemies.getObjs().listIterator();
        
        while(groundEnemiesLi.hasNext()){
            GroundEnemy cEnemy = (GroundEnemy)(groundEnemiesLi.next());
            if(cEnemy.getDeadAniEnded()){
                groundEnemiesLi.remove();
            }
        }
    }
    
    public void resetLevel(){
        this.init();
    }
    
}
