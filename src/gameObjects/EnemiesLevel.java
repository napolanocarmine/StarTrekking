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
 *
 * @author Star Trekking
 */
public abstract class  EnemiesLevel extends Assembly{
    protected ArrayList<Position> positions; 
    protected float groundY = (GamePanel.HEIGHT) - 162;
    protected EntitySprite sprite;
    
    protected abstract void initPositionArray();
    protected abstract void createEnemies();
    
}
