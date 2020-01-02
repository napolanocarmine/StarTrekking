package gameObjects;

import java.awt.Graphics2D;

/**
 *
 * @author StarTrekking
 *
 * Interface GameObject to define methods about rendering objects
 */
public interface GameObject {
    /**
     * Method that must implement the logic behavior.
     */
    public void updateGame();
    
    /**
     * Method that must implement graphics behavior.
     * @param g Graphics2D object.
     */
    public void render(Graphics2D g);
}
