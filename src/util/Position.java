/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 * Class which represents the position of an entity inside the game world
 * @author Star Trekking
 */
public class Position {

    private float x, y;
    private static float worldX;
    private static float worldY;

    /**
     * Constuctor of Position objects
     */
    public Position() {
        x = 0;
        y = 0;
    }

    /**
     * Constuctor of Position objects
     * @param x position on the x axis
     * @param y position on the y axis
     */
    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set the position values
     * @param x position on the x axis
     * @param y position on the y axis
     */
    public void setPos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x
     * @return x
     */
    public float getX() {
        return x;
    }

    /**
     * Get the y
     * @return y
     */
    public float getY() {
        return y;
    }

    /**
     * Set the x
     * @param x position on the x axis
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Set the y
     * @param y position on the y axis
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Sum a value to x
     * @param x value to add to x
     */
    public void addX(float x) {
        this.x += x;
    }

    /**
     * Sum a value to y
     * @param y value to add to y
     */
    public void addY(float y) {
        this.y += y;
    }
    
    /**
     * Set the origin of the world map
     * @param x x origin of the world map
     * @param y y origin of the world map
     */
    public static void setWorldVar(float x, float y) {
        worldX = x;
        worldY = y;
    }
    
    /**
     * Return the position relative to the origin of the world
     * @return position relative to the origin of the world
     */
    public Position getWorldVar() {
        return new Position(x - worldX, y - worldY);
    }
}
