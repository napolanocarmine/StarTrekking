package gameObjects;

import tiles.LayerFacade;
import util.Position;

public class Level3 extends Level{
     
    public Level3() {
        super();
        init();
    }
    
    @Override
    public void init(){
        super.init();
        //player.setPos(new Position(0, 0 + groundY-100));
        tf = new LayerFacade("tiles/LevelThree.xml");
        enemies = (EnemiesLevel)ef.build(2);
        addObj(enemies);
    }
}