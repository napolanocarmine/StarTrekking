package tiles.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import util.EntityBox;
import util.Position;

/**
 * Class which represents a solid block
 * @author Star Trekking
 */
public class ObjBlock extends Block {

    /**
     * Constractor of a ObjBlock object
     * @param img image of the tile
     * @param pos position of the block
     * @param w width of the block
     * @param h height of the block
     */
    public ObjBlock(BufferedImage img, Position pos, int w, int h) {
        super(img, pos, w, h);
    }

    @Override
    public boolean update() {
        return true;
    }

//    @Override
//    public boolean isInside(EntityBox p) {
//        return false;
//    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
//        g.setColor(Color.WHITE);
//        g.drawRect((int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), w, h);
    }
}
