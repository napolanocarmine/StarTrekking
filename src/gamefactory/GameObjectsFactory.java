/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamefactory;
import gameObjects.GameObject;

/**
 *
 * @author Star Trekking
 */
public abstract class GameObjectsFactory {
    
    public GameObject build(int code){
        return selectGameObject(code);
    }
    protected abstract GameObject selectGameObject(int code);

}
