package gameObjects;

import java.awt.Graphics2D;

/**
 *
 * @author StarTrekking
 *
 * Interface GameObject to define methods about rendering objects
 */
public interface GameObject {

    public void updateGame();

    public void render(Graphics2D g);
}
