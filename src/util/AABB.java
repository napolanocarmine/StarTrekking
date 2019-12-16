package util;

import entity.Entity;

public class AABB {
    
    private Position pos;
    private float xOffset = 0;
    private float yOffset = 0;
    private float w;
    private float h;
    private int size;
    
    public AABB(Position pos, int w, int h){
        this.pos = pos;
        this.w = w;
        this.h = h;
        
        size = Math.max(w, h);
    }
    
    public AABB(Position pos, int w, int h, int xOffset, int yOffset){
        this.pos = pos;
        this.w = w;
        this.h = h;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        
        size = Math.max(w, h);
    }
    
    public Position getPos(){ return pos; }
    
    public float getWidth(){ return w; }
    public float getHeight(){ return h; }
    public float getXOffset(){ return xOffset; }
    public float getYOffset(){ return yOffset; }
    
    public void setBox(Position pos, int w , int h){
        this.pos = pos;
        this.w = w;
        this.h = h;
        
        size = Math.max(h, w);
    }
    
    public void setWidth(float f){ w = f; }
    public void setHeight(float f){ h = f; }
    
    public void setXOffset(float f){ xOffset = f; }
    public void setYOffset(float f){ yOffset = f; }
    
    public boolean collides(AABB bBox){
        float ax = ((pos.getWorldVar().getX() + (xOffset/2))+(w/2));
        float ay = ((pos.getWorldVar().getY() + (yOffset/2))+(h/2));
        //System.err.println("ax: " + ax + " ay: " + ay);
        float bx = ((bBox.pos.getWorldVar().getX() + (bBox.xOffset/2))+(w/2));
        float by = ((bBox.pos.getWorldVar().getY() + (bBox.yOffset/2))+(h/2));
        //System.err.println("bx: " + bx + " by: " + by);
        
        if(Math.abs(ax-bx)+7 < (this.w / 2) + (bBox.w / 2)){
            if(Math.abs(ay-by)-2 < (this.h / 2) + (bBox.h / 2)){
                return true;
            }
        }
        return false;
    }
    
}
