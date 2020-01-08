package gameObjects;

import tiles.LayerFacade;

/**
 * Represents the Level one inside the game.
 * @author Star Trekking
 */
public class Level1 extends Level{
    
    /**
     * Cosutructor of Level one
     */
    public Level1() {
        super();
        init();
    }
    
    /**
     * This method initialize the Map and the GameObjects of the level one
     */
    @Override
    public void init(){
        super.init();
        tf = new LayerFacade("tiles/LevelOne.xml");
        enemies = (EnemiesLevel)ef.build(1);
        addObj(enemies);
    }
}
