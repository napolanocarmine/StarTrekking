package util.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import util.Position;

public class TileSet {

    private BufferedImage tileMatrix;
    private int w;
    private int h;
    private int numTileCols;
    private int numTileRows;

    /**
     * Constructor of TileSet objects
     * @param file path of the tileset image
     * @param w widht of a single tile of the tile set
     * @param h height of a single tile of the tile set
     */
    public TileSet(String file, int w, int h) {
        this.w = w;
        this.h = h;

        System.out.println("Loading: " + file + "...");
        tileMatrix = loadTileSet(file);

        numTileCols = tileMatrix.getWidth() / w;
        numTileRows = tileMatrix.getHeight() / h;
    }

    private BufferedImage loadTileSet(String file) {
        BufferedImage tileSet = null;
        try {
            tileSet = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));  // load the tile set
        } catch (IOException e) {
            System.out.println("ERROR: could not load file: " + file);
        }

        return tileSet; // return the tile set
    }

    /**
     * Get a single tile of the tileset based on its the column and row index
     * @param x column index
     * @param y row index
     * @return sub image of the tile set which represents a single tile 
     */
    public BufferedImage getTile(int x, int y) {
        return tileMatrix.getSubimage(x * w, y * h, w, h);
    }

    /**
     * Get the tile set image
     * @return the tile set image
     */
    public BufferedImage getMatrix() {
        return tileMatrix;
    }

    /**
     * Get the width of a tile
     * @return width of a tile
     */
    public int getTileWidth(){
        return w;
    }
}
