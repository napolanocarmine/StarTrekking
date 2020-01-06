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
 * 
 * Abstract class useful for factory design pattern
 */
public abstract class GameObjectsFactory {
    /**
     * Returns a GameObject instance related to
     * input param
     * 
     * @param code
     * @return GameObject
     */
    public GameObject build(int code){
        return selectGameObject(code);
    }
    protected abstract GameObject selectGameObject(int code);

}
