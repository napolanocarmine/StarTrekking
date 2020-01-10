package tiles;

import util.graphics.TileSet;
import java.awt.Graphics2D;
import java.util.HashMap;
import tiles.blocks.Block;
import tiles.blocks.ObsBlock;
import tiles.blocks.ObjBlock;
import util.Position;

/**
 * Class which creates the solid layer of the map
 * @author Star trekking
 */
public class SolidLayer extends Layer {

    private static HashMap<String, Block> tmobj_blocks;
    private static int width;
    private static int height;

    public SolidLayer(String data, TileSet ts, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        tmobj_blocks = new HashMap<>(); // Initialization of the obstacles blocks structure

        String[] block = data.split(",");   

        // Iteration over all the string of the layer matrix
        for (int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
            
            // if a tile exist create a Block object and add it to the solid blocks structure
            if (temp != 0) {
                Block tempBlock = new ObjBlock(ts.getTile((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)),
                        new Position((int) (i % width) * tileWidth, (int) (i / width) * tileHeight), tileWidth, tileHeight);
                tmobj_blocks.put(String.valueOf((int) (i % width)) + "," + String.valueOf((int) (i / width)), tempBlock);
            }
        }
    }
    
    /**
     * Return the solid blocks structure
     * @return the solid blocks structure
     */
    public static HashMap<String, Block> getTmobj_blocks(){
        return tmobj_blocks;
    }

    @Override
    public void render(Graphics2D g) {
        for (Block block : tmobj_blocks.values()) {
            block.render(g);
        }
    }
}