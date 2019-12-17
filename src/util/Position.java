/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author emiso
 */
public class Position {

    private float x, y;
    public static float worldX;
    public static float worldY;

    public Position() {
        x = 0;
        y = 0;
    }

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void setPos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;   
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void addX(float x) {
        this.x += x;
    }

    public void addY(float y) {
        this.y += y;
    }

    public static void setWorldVar(float x, float y) {
        worldX = x;
        worldY = y;
    }

    public Position getWorldVar() {
        return new Position(x - worldX, y - worldY);
    }
}
