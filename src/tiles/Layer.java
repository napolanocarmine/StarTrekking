package tiles;

import java.awt.Graphics2D;

/**
 * Abstract class which represents a Layer
 * @author Star Trekking
 */
public abstract class Layer {

    /**
     * Rendering method
     * @param g Graphics2D object
     */
    public abstract void render(Graphics2D g);

}
