package tiles;

import util.graphics.TileSet;
import java.awt.Graphics2D;
import java.util.ArrayList;
import tiles.blocks.Block;
import tiles.blocks.NormBlock;
import util.Position;

/**
 * Class which creates a normal layer of the map
 * @author Star trekking
 */
public class NormLayer extends Layer {

    private ArrayList<Block> blocks;    
    
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
    public NormLayer(String data, TileSet ts, int width, int height, int tileWidth, int tileHeight, int tileColumns) {

        blocks = new ArrayList<>(); // Initialization of the normal blocks structure

        String[] block = data.split(",");
        // Iteration over all the string of the layer matrix
        for (int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
            // if a tile exist create a Block object and add it to the blocks structure
            if (temp != 0) {
                blocks.add(new NormBlock(ts.getTile((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)),
                        new Position((int) (i % width) * tileWidth, (int) (i / width) * tileHeight), tileWidth, tileHeight));
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).render(g);
        }
    }

}
