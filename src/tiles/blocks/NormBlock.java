package tiles.blocks;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import util.EntityBox;
import util.Position;

/**
 * Class which represents a normal block
 * @author Star Trekking
 */
public class NormBlock extends Block {

    /**
     * Constractor of a ObsBlock object
     * @param img image of the tile
     * @param pos position of the block
     * @param w width of the block
     * @param h height of the block
     */
    public NormBlock(BufferedImage img, Position pos, int w, int h) {
        super(img, pos, w, h);
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
    }

//    @Override
//    public boolean isInside(EntityBox p) {
//        return false;
//    }

}
