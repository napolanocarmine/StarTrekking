package tiles;

import java.awt.Graphics2D;
import java.util.HashMap;
import tiles.blocks.Block;

public abstract class TileMap {

    public static HashMap<String, Block> tmobj_blocks = new HashMap<>();
    public static HashMap<String, Block> tmobs_blocks = new HashMap<>();
    
    public abstract void render(Graphics2D g);

}
