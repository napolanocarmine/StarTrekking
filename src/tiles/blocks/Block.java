package tiles.blocks;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import util.Position;

/**
 * Abstract class which represents a block
 * @author Star Trekking
 */
public abstract class Block {

    /**
     * width of the block
     */
    protected int w;
    
    /**
     * height of the block
     */
    protected int h;

    /**
     * tile image of the block
     */
    protected BufferedImage img;
    
    /**
     * position of the block
     */
    protected Position pos;

    /**
     * Constractor of a Block object
     * @param img image of the tile
     * @param pos position of the block
     * @param w width of the block
     * @param h height of the block
     */
    public Block(BufferedImage img, Position pos, int w, int h) {
        this.img = img;
        this.pos = pos;
        this.w = w;
        this.h = h;
    }

    /**
     * Get the position of a block
     * @return position of a block
     */
    public Position getPos() {
        return pos;
    }

    /**
     * return a boolean
     * @return boolean
     */
    public abstract boolean update();
    
    /**
     * Get the width of the block
     * @return width of the block
     */
    public int getWidth() {
        return w;
    }
    
    /**
     * Get the height of the block
     * @return height of the block
     */
    public int getHeight() {
        return h;
    }

    /**
     * Render a block
     * @param g Graphic2D object
     */
    public void render(Graphics2D g) {
        g.drawImage(img, (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), w, h, null);
    }
}
