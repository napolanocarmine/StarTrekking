package tiles;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import tiles.blocks.Block;

public abstract class Layer {

    public static HashMap<String, Block> tmobj_blocks;
    public static HashMap<String, Block> tmobs_blocks;
    protected ArrayList<Block> blocks;
    
    public abstract void render(Graphics2D g);

}
