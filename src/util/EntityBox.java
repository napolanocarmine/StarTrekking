package util;

/**
 * Class that manages the collision between entities
 * @author Star Trekking
 */
public class EntityBox {

    private Position pos;
    private float xOffset = 0;
    private float yOffset = 0;
    private float w;
    private float h;

    /**
     * Constructor of an EntityBox object
     * @param pos position of the entity box
     * @param w width of the entity box
     * @param h height of the entity box
     */
    public EntityBox(Position pos, int w, int h) {
        this.pos = pos;
        this.w = w;
        this.h = h;
    }

    /**
     * Constructor of an EntityBox object
     * @param pos position of the entity box
     * @param w width of the entity box
     * @param h height of the entity box
     * @param xOffset offset on the x axis of the entity box
     * @param yOffset offset on the y axis of the entity box
     */
    public EntityBox(Position pos, int w, int h, int xOffset, int yOffset) {
        this.pos = pos;
        this.w = w;
        this.h = h;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    /**
     * get the entity box position
     * @return entity box position
     */
    public Position getPos() {
        return pos;
    }

    /**
     * get the entity box width
     * @return entity box width
     */
    public float getWidth() {
        return w;
    }

    /**
     * get the entity box height
     * @return entity box height
     */
    public float getHeight() {
        return h;
    }

    /**
     * get the entity box offset on x axis
     * @return entity box offset on x axis
     */
    public float getXOffset() {
        return xOffset;
    }

    
    /**
     * get the entity box offset on y axis
     * @return entity box offset on y axis
     */
    public float getYOffset() {
        return yOffset;
    }

    /**
     * Set the entity box dimensions
     * @param w width of the entity box
     * @param h height of the entity box
     * @param xOffset offset on the x axis of the entity box
     * @param yOffset offset on the y axis of the entity box
     */
    public void setBox(int w, int h, int xOffset, int yOffset) {
        this.w = w;
        this.h = h;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
    /**
     * set the entity box width
     * @param f width
     */
    public void setWidth(float f){ w = f; }
    
    /**
     * set the entity box height
     * @param f height
     */
    public void setHeight(float f){ h = f; }
   
    /**
     * set the entity box offset on x axis
     * @param f offset on x axis
     */
    public void setXOffset(float f){ xOffset = f; }

    /**
     * set the entity box offset on y axis
     * @param f offset on y axis
     */
    public void setYOffset(float f){ yOffset = f; }
    
    /**
     * Return true if the current entity box collides with the given bBox, false otherwise
     * @param bBox given entity box
     * @return true if the current entity box collides with the given bBox, false otherwise 
     */
    public boolean collides(EntityBox bBox){
        float ax = ((pos.getWorldVar().getX() + (xOffset/2))+(w/2));    // compute the position of this entity box on x axis
        float ay = ((pos.getWorldVar().getY() + (yOffset/2))+(h/2));    // compute the position of this entity box on y axis
        //System.err.println("ax: " + ax + " ay: " + ay);
        float bx = ((bBox.pos.getWorldVar().getX() + (bBox.xOffset/2))+(w/2));  // compute the position of the given entity box on x axis
        float by = ((bBox.pos.getWorldVar().getY() + (bBox.yOffset/2))+(h/2));  // compute the position of the given entity box on y axis
        //System.err.println("bx: " + bx + " by: " + by);

        // if the two enityt box collides the method return true, otherwise false
        if(Math.abs(ax-bx)+7 < (this.w / 2) + (bBox.w / 2)){  
            if(Math.abs(ay-by)-2 < (this.h / 2) + (bBox.h / 2)){
                return true;
            }
        }
        return false;
    }

}
