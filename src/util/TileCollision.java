package util;

import gameObjects.Entity;
import tiles.ObstaclesLayer;
import tiles.SolidLayer;
import tiles.blocks.Block;

/**
 * Class that manages the collision between an entity and the tiles of the map
 * @author Star Trekking
 */
public class TileCollision {

    private Entity e;

    /**
     * Constructor of TileCollision objects
     * @param e entity whose collisions are to be detected 
     */
    public TileCollision(Entity e) {
        this.e = e;
    }

    /**
     * Check the collision of an entity and the solid tiles of the map based on the shift on both the axis.
     * The collision are checked with respect to all the four corners of the entity box.
     * @param ax x axis shift
     * @param ay y axis shift
     * @return true if the player hit a tile of the map, false otherwise
     */
    public boolean collisionTileObj(float ax, float ay) {
        for (int c = 0; c < 4; c++) { //loop on each corner of the block (tile)
            EntityBox eBounds = e.getBounds();  //gets the entity box of the entity
            int xt = (int) ((eBounds.getPos().getX() + ax) + (c % 2) * eBounds.getWidth() + eBounds.getXOffset()) / 16; // compute the x position of the corner adding the shift
            int yt = (int) ((eBounds.getPos().getY() + ay) + ((int) (c / 2)) * eBounds.getHeight() + eBounds.getYOffset()) / 16;    // compute the y position of the corner adding the shift

            // if the position of the corner correspond to the position of a tile inside the solid layer returns true, false otherwise
            if (SolidLayer.getTmobj_blocks().containsKey(String.valueOf(xt) + "," + String.valueOf(yt))) {
                Block block = SolidLayer.getTmobj_blocks().get(String.valueOf(xt) + "," + String.valueOf(yt));
                return block.update();
            }
        }
        return false;
    }
    
    /**
     * Check the collision of an entity and the obstacles tiles of the map based on the shift on both the axis.
     * The collision are checked with respect to all the four corners of the entity box.
     * @param ax x axis shift
     * @param ay y axis shift
     * @return true if the player hit a tile of the map, false otherwise
     */
    public boolean collisionTileObs(float ax, float ay) {
        for (int c = 0; c < 4; c++) { //loop on each corner of the block (tile)
            EntityBox eBounds = e.getBounds();
            int xt = (int) ((eBounds.getPos().getX() + ax) + (c % 2) * eBounds.getWidth() + eBounds.getXOffset()) / 16; // compute the x position of the corner adding the shift
            int yt = (int) ((eBounds.getPos().getY() + ay) + ((int) (c / 2)) * eBounds.getHeight() + eBounds.getYOffset()) / 16;    // compute the y position of the corner adding the shift

            // if the position of the corner correspond to the position of a tile inside the obstacles layer returns true, false otherwise
            if (ObstaclesLayer.getTmobs_blocks().containsKey(String.valueOf(xt) + "," + String.valueOf(yt))) {
                return true;
            }
        }
        return false;
    }

    
    /**
     * Check the collision of an entity and the solid tiles of the map based on the shift on both the axis.
     * The collision are checked with respect to all the upper corners of the entity box.
     * @param ax x axis shift
     * @param ay y axis shift
     * @return true if the player hit a tile of the map, false otherwise
     */
    public boolean collisionTileUp(float ax, float ay) {
        for (int c = 0; c < 2; c++) { //loop on each corner of the block (tile)
            EntityBox eBounds = e.getBounds();
            int xt = (int) ((eBounds.getPos().getX() + ax) + (c % 2) * eBounds.getWidth() + eBounds.getXOffset()) / 16; // compute the x position of the corner adding the shift
            int yt = (int) ((eBounds.getPos().getY() + ay) + ((int) (c / 2)) * eBounds.getHeight() + eBounds.getYOffset()) / 16;    // compute the y position of the corner adding the shift

            // if the position of the corner correspond to the position of a tile inside the solid layer returns true, false otherwise
            if (SolidLayer.getTmobj_blocks().containsKey(String.valueOf(xt) + "," + String.valueOf(yt))) {
                Block block = SolidLayer.getTmobj_blocks().get(String.valueOf(xt) + "," + String.valueOf(yt));
                return block.update();
            }
        }
        return false;
    }

    
    /**
     * Check the collision of an entity and the soldi tiles of the map based on the shift on both the axis.
     * The collision are checked with respect to all the lower corners of the entity box.
     * @param ax x axis shift
     * @param ay y axis shift
     * @return true if the player hit a tile of the map, false otherwise
     */
    public boolean collisionTileDown(float ax, float ay) {
        for (int c = 2; c < 4; c++) { //loop on each corner of the block (tile)
            EntityBox eBounds = e.getBounds();
            int xt = (int) ((eBounds.getPos().getX() + ax) + (c % 2) * eBounds.getWidth() + eBounds.getXOffset()) / 16; // compute the x position of the corner adding the shift
            int yt = (int) ((eBounds.getPos().getY() + ay) + ((int) (c / 2)) * eBounds.getHeight() + eBounds.getYOffset()) / 16;    // compute the y position of the corner adding the shift
            
            // if the position of the corner correspond to the position of a tile inside the solid layer returns true, false otherwise
            if (SolidLayer.getTmobj_blocks().containsKey(String.valueOf(xt) + "," + String.valueOf(yt))) {
                Block block = SolidLayer.getTmobj_blocks().get(String.valueOf(xt) + "," + String.valueOf(yt));
                return block.update();
            }
        }
        return false;
    }
}
