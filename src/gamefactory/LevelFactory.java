/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamefactory;

import gameObjects.GameObject;
import gameObjects.Level;
import gameObjects.Level1;
import gameObjects.Level2;
import gameObjects.Level3;

/**
 *
 * @author Star Trekking
 */
public class LevelFactory  extends GameObjectsFactory{
   
    @Override
    protected GameObject selectGameObject(int code) throws IllegalArgumentException{
        switch (code) {
            case 1:
                return new Level1();
            case 2:
                return new Level2();
            case 3:
                return new Level3();
            default:
                throw new IllegalArgumentException("Wrong parameter");
        }
    }

}
