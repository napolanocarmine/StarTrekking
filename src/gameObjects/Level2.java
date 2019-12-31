package gameObjects;

import tiles.LayerFacade;
import util.EntityBox;

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
        enemies = (EnemiesLevel)ef.build(2);
        addObj(enemies);
    }
}
