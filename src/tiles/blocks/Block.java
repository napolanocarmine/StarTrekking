package tiles.blocks;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import util.EntityBox;
import util.Position;

public abstract class Block {

    protected int w;
    protected int h;

    protected BufferedImage img;
    protected Position pos;

    public Block(BufferedImage img, Position pos, int w, int h) {
        this.img = img;
        this.pos = pos;
        this.w = w;
        this.h = h;
    }

    public Position getPos() {
        return pos;
    }

    public abstract boolean update();
//    public abstract boolean isInside(EntityBox p);

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public void render(Graphics2D g) {
        g.drawImage(img, (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), w, h, null);
    }
}
