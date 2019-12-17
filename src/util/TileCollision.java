package util;

import entity.Entity;
import graphics.Sprite;
import tiles.TileMapObj;
import tiles.blocks.Block;
import tiles.blocks.HoleBlock;

public class TileCollision {
    
    private Entity e;
    
    public TileCollision(Entity e){
        this.e = e;
    }
    
    public boolean collisionTile(float ax, float ay){
        for(int c = 0; c<4; c++){ //loop on each corner of the block (tile)
            AABB eBounds = e.getBounds();
            int xt=(int)((eBounds.getPos().getX() + ax)+(c % 2) * eBounds.getWidth() + eBounds.getXOffset()) / 16;
            int yt=(int)((eBounds.getPos().getY() + ay)+((int)(c / 2)) * eBounds.getHeight()+ eBounds.getYOffset()) / 16;
            
            if(TileMapObj.tmo_blocks.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))){
                Block block = TileMapObj.tmo_blocks.get(String.valueOf(xt) + "," + String.valueOf(yt));
                if(block instanceof HoleBlock){
                    //System.out.println("HoleBlock");
                    return collisionHole(ax, ay, xt, yt, block);
                }
                return block.update();
            }
        }
        return false;
    }
   
    private boolean collisionHole(float ax, float ay, int xt, int yt, Block block) {
        AABB eBounds = e.getBounds();
        int nextXt = (int)((((eBounds.getPos().getX()+ax)+eBounds.getXOffset())/16)+eBounds.getWidth()/32);
        int nextYt = (int)((((eBounds.getPos().getY()+ay)+eBounds.getYOffset())/16)+eBounds.getHeight()/32);
        
        if(block.isInside(eBounds)){
            //e.setFallen(true);
            System.out.println("MORTO");
            return false;
        }
        else if(nextXt==yt+1 || nextXt==xt+1 || nextYt==yt-1 || nextXt==xt-1){
            if(TileMapObj.tmo_blocks.containsKey(String.valueOf(nextXt) + "," + String.valueOf(nextYt))){
                Block nextblock = TileMapObj.tmo_blocks.get(String.valueOf(nextXt) + "," + String.valueOf(nextYt));
                
                if(eBounds.getPos().getX() + e.getBounds().getXOffset() > block.getPos().getX() 
                && eBounds.getPos().getY() + e.getBounds().getYOffset() > block.getPos().getY()
                && nextblock.getWidth() + nextblock.getPos().getX() > e.getBounds().getWidth() + (eBounds.getPos().getX() + e.getBounds().getXOffset())
                && nextblock.getHeight() + nextblock.getPos().getY() > e.getBounds().getHeight() + (eBounds.getPos().getY() + e.getBounds().getYOffset())) {
                    //e.setFallen(true);
                    System.out.println("MORTO");
                }
                return false;   
            }
        }
        //e.setFallen(false);
        System.out.println("VIVO");
        return false;
    }
    
    public boolean collisionTileUp(float ax, float ay){
        for(int c = 0; c<2; c++){ //loop on each corner of the block (tile)
                AABB eBounds = e.getBounds();
                int xt=(int)((eBounds.getPos().getX() + ax)+(c % 2) * eBounds.getWidth() + eBounds.getXOffset()) / 16;
                int yt=(int)((eBounds.getPos().getY() + ay)+((int)(c / 2)) * eBounds.getHeight()+ eBounds.getYOffset()) / 16;

                if(TileMapObj.tmo_blocks.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))){
                    Block block = TileMapObj.tmo_blocks.get(String.valueOf(xt) + "," + String.valueOf(yt));
                    if(block instanceof HoleBlock){
                        //System.out.println("HoleBlock");
                        return collisionHole(ax, ay, xt, yt, block);
                    }
                    return block.update();
                }
            }
        return false;
    }
    
    public boolean collisionTileDown(float ax, float ay){
        for(int c = 2; c<4; c++){ //loop on each corner of the block (tile)
            AABB eBounds = e.getBounds();
            int xt=(int)((eBounds.getPos().getX() + ax)+(c % 2) * eBounds.getWidth() + eBounds.getXOffset()) / 16;
            int yt=(int)((eBounds.getPos().getY() + ay)+((int)(c / 2)) * eBounds.getHeight()+ eBounds.getYOffset()) / 16;
            
            if(TileMapObj.tmo_blocks.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))){
                Block block = TileMapObj.tmo_blocks.get(String.valueOf(xt) + "," + String.valueOf(yt));
                if(block instanceof HoleBlock){
                    //System.out.println("HoleBlock");
                    return collisionHole(ax, ay, xt, yt, block);
                }
                return block.update();
            }
        }
        return false;
    }
}
