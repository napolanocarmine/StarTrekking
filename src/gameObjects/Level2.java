/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import panels.GamePanel;
import tiles.LayerFacade;
import util.EntityBox;

/**
 *
 * @author Star Trekking
 */
public class Level2 extends Level{
     
    public Level2() {
        init();
    }
    
    @Override
    public void init(){
        super.init();
        tf = new LayerFacade("tiles/LevelTwo.xml");
        player.setStandBounds(new EntityBox(player.getPos(), 16, 24, 40, 32));
        player.setCrouchBounds(new EntityBox(player.getPos(), 16, 12, 40, 44));
        player.setBounds(player.getStandBounds());
        /*
            
        */
        enemies = (EnemiesLevel)ef.build(2);
        addObj(enemies);
//        enemieSprite = new EntitySprite("entity/goblin", 64, 64);
//        groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(1000, groundY), 96));
//        groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(1500, groundY), 96));
//        groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(2100, groundY), 96));
//        groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(3080, groundY), 96));
//        groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(4700, groundY), 96));
//        groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(5500, groundY), 96));
//        groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(7400, groundY), 96));
//        groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(7400, 220), 96));
//        groundEnemies.addObj(new GroundEnemy(enemieSprite, new Position(9500, groundY), 96));
//    

    }
}
