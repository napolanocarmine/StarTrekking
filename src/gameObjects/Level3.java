package gameObjects;

import tiles.LayerFacade;

public class Level3 extends Level{
    
    /**
     * Cosutructor of Level three
     */
    public Level3() {
        super();
        init();
    }
    
    /**
     * This method initialize the Map and the GameObjects of the level three
     */
    @Override
    public void init(){
        super.init();
        //player.setPos(new Position(0, 0 + groundY-100));
        player.setDist(300);
        player.setH(150);
        tf = new LayerFacade("tiles/LevelThree.xml");
        enemies = (EnemiesLevel)ef.build(3);
        addObj(enemies);
    }
}
