/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamefactory;

import gameObjects.EnemiesLevel1;
import gameObjects.EnemiesLevel2;
import gameObjects.EnemiesLevel3;
import gameObjects.GameObject;

/**
 *
 * @author Star Trekking
 */
public class EnemiesFactory extends GameObjectsFactory{

     @Override
    protected GameObject selectGameObject(int code) throws IllegalArgumentException{
        switch (code) {
            case 1:
                return new EnemiesLevel1();
            case 2:
                return new EnemiesLevel2();
            case 3:
                return new EnemiesLevel3();
            default:
                throw new IllegalArgumentException("Wrong parameter");
        }
    }
    
}
