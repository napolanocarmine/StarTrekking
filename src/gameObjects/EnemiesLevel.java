/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import graphics.EntitySprite;
import java.util.ArrayList;
import panels.GamePanel;
import util.Position;

/**
 * Abstract class that represents the Enemies in the generic Level.
 * @author Star Trekking
 */
public abstract class  EnemiesLevel extends Assembly{
    /**
    * Array represented enemy positions insiede the level.
    */
    protected ArrayList<Position> positions; 
    
    /**
     * Represents the level where is placed the ground.
     */
    protected float groundY = (GamePanel.HEIGHT) - 162;
    /**
     * Represents the sprite to assign to the enemies.
     */
    protected EntitySprite sprite;
    /**
     * Produces the position of the enemies.
     */
    protected abstract void initPositionArray();
    /**
     * Produces the enemies.
     */
    protected abstract void createEnemies();
    
}
