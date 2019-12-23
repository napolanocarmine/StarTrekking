package util;

import gameObjects.Entity;
import tiles.TileMapObj;
import tiles.blocks.Block;
import tiles.blocks.HoleBlock;

public class TileCollision {

    private Entity e;

    public TileCollision(Entity e) {
        this.e = e;
    }

    public boolean collisionTileObj(float ax, float ay) {
        for (int c = 0; c < 4; c++) { //loop on each corner of the block (tile)
            AABB eBounds = e.getBounds();
            int xt = (int) ((eBounds.getPos().getX() + ax) + (c % 2) * eBounds.getWidth() + eBounds.getXOffset()) / 16;
            int yt = (int) ((eBounds.getPos().getY() + ay) + ((int) (c / 2)) * eBounds.getHeight() + eBounds.getYOffset()) / 16;

            if (TileMapObj.tmobj_blocks.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))) {
                Block block = TileMapObj.tmobj_blocks.get(String.valueOf(xt) + "," + String.valueOf(yt));
                return block.update();
            }
        }
        return false;
    }
    
    public boolean collisionTileObs(float ax, float ay) {
        for (int c = 0; c < 4; c++) { //loop on each corner of the block (tile)
            AABB eBounds = e.getBounds();
            int xt = (int) ((eBounds.getPos().getX() + ax) + (c % 2) * eBounds.getWidth() + eBounds.getXOffset()) / 16;
            int yt = (int) ((eBounds.getPos().getY() + ay) + ((int) (c / 2)) * eBounds.getHeight() + eBounds.getYOffset()) / 16;

            if (TileMapObj.tmobs_blocks.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionTileUp(float ax, float ay) {
        for (int c = 0; c < 2; c++) { //loop on each corner of the block (tile)
            AABB eBounds = e.getBounds();
            int xt = (int) ((eBounds.getPos().getX() + ax) + (c % 2) * eBounds.getWidth() + eBounds.getXOffset()) / 16;
            int yt = (int) ((eBounds.getPos().getY() + ay) + ((int) (c / 2)) * eBounds.getHeight() + eBounds.getYOffset()) / 16;

            if (TileMapObj.tmobj_blocks.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))) {
                Block block = TileMapObj.tmobj_blocks.get(String.valueOf(xt) + "," + String.valueOf(yt));
                return block.update();
            }
        }
        return false;
    }

    public boolean collisionTileDown(float ax, float ay) {
        for (int c = 2; c < 4; c++) { //loop on each corner of the block (tile)
            AABB eBounds = e.getBounds();
            int xt = (int) ((eBounds.getPos().getX() + ax) + (c % 2) * eBounds.getWidth() + eBounds.getXOffset()) / 16;
            int yt = (int) ((eBounds.getPos().getY() + ay) + ((int) (c / 2)) * eBounds.getHeight() + eBounds.getYOffset()) / 16;

            if (TileMapObj.tmobj_blocks.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))) {
                Block block = TileMapObj.tmobj_blocks.get(String.valueOf(xt) + "," + String.valueOf(yt));
                return block.update();
            }
        }
        return false;
    }
}
