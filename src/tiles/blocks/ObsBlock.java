package tiles.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import util.EntityBox;
import util.Position;

/**
 * Class which represents a obstacles block
 * @author Star Trekking
 */
public class ObsBlock extends Block {

    /**
     * Constractor of a ObsBlock object
     * @param img image of the tile
     * @param pos position of the block
     * @param w width of the block
     * @param h height of the block
     */
    public ObsBlock(BufferedImage img, Position pos, int w, int h) {
        super(img, pos, w, h);
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
//        g.setColor(Color.GREEN);
//        g.drawRect((int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), w, h);
    }
}
