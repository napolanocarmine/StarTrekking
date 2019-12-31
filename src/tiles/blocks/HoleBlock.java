package tiles.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import util.EntityBox;
import util.Position;

public class HoleBlock extends Block {

    public HoleBlock(BufferedImage img, Position pos, int w, int h) {
        super(img, pos, w, h);
    }

    @Override
    public boolean update() {
        return false;
    }

//    @Override
//    public boolean isInside(EntityBox p) {
//        if (p.getPos().getX() + p.getXOffset() < pos.getX()) {
//            return false; //player right bound smaller than the left tile bound
//        }
//        if (p.getPos().getY() + p.getYOffset() < pos.getY()) {
//            return false;
//        }
//        if (w + pos.getX() < (p.getPos().getX() + p.getXOffset())) {
//            return false;  //right side of the tile smaller than the left side of the player bounds
//        }
//        if (h + pos.getY() < (p.getPos().getY() + p.getYOffset())) {
//            return false;
//        }
//
//        return true;
//    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
//        g.setColor(Color.GREEN);
//        g.drawRect((int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), w, h);
    }
}
