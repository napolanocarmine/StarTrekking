package gameObjects;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author StarTrekking
 *
 * Abstract Class to keep references to game objects.
 */
public abstract class Assembly implements GameObject {

    protected ArrayList<GameObject> objs;
    
    /**
     * Create an empty Assembly objcets.
     */
    public Assembly() {
        objs = new ArrayList<>();
    }
    
    @Override
    public void updateGame() {
        for(GameObject obj: objs){
            obj.updateGame();
        }
    }
    
    /**
     * Perform the render operation above all the GameObjects.
     * @param g Graphics2D object.
     */
    @Override
    public void render(Graphics2D g) {
        for (GameObject obj : objs) {
            obj.render(g);
        }
    }

    /**
     * Add a new GameObject to the Assembly.
     * @param obj object that must be added
     */
    public void addObj(GameObject obj) {
        objs.add(obj);
    }

    /**
     * Remove the given GameObject from the Assembly.
     * @param obj object that must be deleted
     */
    public void removeObj(GameObject obj) {
        objs.remove(obj);
    }

    /**
     *  Return the current Assembly.
     * @return an ArrayList of all game objects
     */
    public ArrayList<GameObject> getObjs() {
        return objs;
    }

}
