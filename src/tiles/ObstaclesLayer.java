package tiles;

import util.graphics.TileSet;
import java.awt.Graphics2D;
import java.util.HashMap;
import tiles.blocks.Block;
import tiles.blocks.ObsBlock;
import util.Position;

/**
 * Class which creates the obstacles layer of the map
 * @author Star trekking
 */
public class ObstaclesLayer extends Layer {

    private static HashMap<String, Block> tmobs_blocks;
    private static int width;
    private static int height;
    
    /**
     * Constructor of a NormLayer object
     * @param data  string matrix which represent the layer
     * @param ts    tileset
     * @param width width of the map
     * @param height height of the map
     * @param tileWidth width of a tile
     * @param tileHeight height of a tile 
     * @param tileColumns columns of the tileset
     */
    public ObstaclesLayer(String data, TileSet ts, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        
        tmobs_blocks = new HashMap<>(); // Initialization of the obstacles blocks structure
        
        String[] block = data.split(",");

        // Iteration over all the string of the layer matrix
        for (int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
            // if a tile exist create a Block object and add it to the obstacles blocks structure
            if (temp != 0) {
                Block tempBlock = new ObsBlock(ts.getTile((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)),
                        new Position((int) (i % width) * tileWidth, (int) (i / width) * tileHeight), tileWidth, tileHeight);
                tmobs_blocks.put(String.valueOf((int) (i % width)) + "," + String.valueOf((int) (i / width)), tempBlock);
            }
        }
    }

    /**
     * Return the obstacles blocks structure
     * @return the obstacles blocks structure
     */
    public static HashMap<String, Block> getTmobs_blocks(){
        return tmobs_blocks;
    }
    
    @Override
    public void render(Graphics2D g) {
        for (Block block : tmobs_blocks.values()) {
            block.render(g);
        }
    }
}