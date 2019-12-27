package gameObjects;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author StarTrekking
 *
 * Class to keep references to game objects
 */
public class Assembly implements GameObject {

    protected ArrayList<GameObject> objs;

    public Assembly() {
        objs = new ArrayList<>();
    }

    @Override
    public void updateGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics2D g) {
        for (GameObject obj : objs) {
            obj.render(g);
        }
    }

    /**
     *
     * @param obj that must be added
     */
    public void addObj(GameObject obj) {
        objs.add(obj);
    }

    /**
     *
     * @param obj that must be deleted
     */
    public void removeObj(GameObject obj) {
        objs.remove(obj);
    }

    /**
     *
     * @return an ArrayList of all game objects
     */
    public ArrayList<GameObject> getObjs() {
        return objs;
    }

}
