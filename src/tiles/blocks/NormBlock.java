package tiles.blocks;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import util.EntityBox;
import util.Position;

public class NormBlock extends Block {

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
