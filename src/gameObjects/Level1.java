package gameObjects;

import tiles.LayerFacade;

public class Level1 extends Level{
    
    public Level1() {
        super();
        init();
    }
    
    @Override
    public void init(){
        super.init();
        tf = new LayerFacade("tiles/LevelOne.xml");
        enemies = (EnemiesLevel)ef.build(1);
        addObj(enemies);
    }
    
}
