package tiles.blocks;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import util.Position;

public abstract class Block {
    protected int w;
    protected int h;
    
    protected BufferedImage img;
    protected Position pos;
    
    public Block(BufferedImage img, Position pos, int w, int h){
        this.img = img;
        this.pos = pos;
        this.w = w;
        this.h = h;
    }
    
    public abstract boolean update();
    
    public void render(Graphics2D g){
        g.drawImage(img, (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), w, h, null);
    }
}
