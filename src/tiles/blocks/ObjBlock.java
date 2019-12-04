package tiles.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import util.Position;

public class ObjBlock extends Block{
    
    public ObjBlock(BufferedImage img, Position pos, int w, int h){
        super(img, pos, w, h);
    }
    
    public boolean update(){
        return true;
    }
    
    public void render(Graphics2D g){
        super.render(g);
        g.setColor(Color.WHITE);
        g.drawRect((int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), w, h);
    }
}
