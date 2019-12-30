/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import gamefactory.EnemiesFactory;
import graphics.EntitySprite;
import panels.GamePanel;
import tiles.LayerFacade;
import util.Position;

/**
 *
 * @author Star Trekking
 */
public class Level1 extends Level{
    
    public Level1() {
        super();
        init();
    }
    
    @Override
    public void init(){
        super.init();
        tf = new LayerFacade("tiles/LevelOne.xml");
        
        /*
            
        */
        enemies = (EnemiesLevel)ef.build(1);
        addObj(enemies);

    }
    
}
