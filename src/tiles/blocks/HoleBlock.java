package tiles.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import util.Position;

public class HoleBlock extends Block {
    
    public HoleBlock(BufferedImage img, Position pos, int w, int h){
        super(img, pos, w, h);
    }
    
    public boolean update(){
        return false;
    }
    
    public void render(Graphics2D g){
        super.render(g);
        g.setColor(Color.GREEN);
        g.drawRect((int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), w, h);
    }
}
