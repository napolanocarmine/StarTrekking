/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import panels.GamePanel;
import tiles.LayerFacade;

/**
 *
 * @author Star Trekking
 */
public class Level3 extends Level{
     
    public Level3() {
        super();
        init();
    }
    
    @Override
    public void init(){
        super.init();
        tf = new LayerFacade("tiles/LevelTwo.xml");
        
        /*
            
        */
        enemies = (EnemiesLevel)ef.build(2);
        addObj(enemies);

    }
}