package util.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import util.EntityEnum;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class EntitySprite {

    private HashMap<EntityEnum, BufferedImage[]> spriteMap;

    private int w;
    private int h;

    /**
     * Constructor of an object EntitySprite.
     * @param path path of the folder in which there are the image files realted with the entity animation.
     * @param w width of the entity srite.
     * @param h height of the entity sprite.
     */
    public EntitySprite(String path, int w, int h) {
        this.w = w;
        this.h = h;

        loadSprites(path);
    }

    private void loadSprites(String entity) {

        BufferedImage spritesImage;  // image which will contains all the sprite of the entity realted to a certain state.
        String path;  // path of the folder in which there are the entity sprites images.
        spriteMap = new HashMap<>();  // map that will contains the sprites related to the different states of the entity.

        for (EntityEnum state : EntityEnum.values()) {  // iterate over all the possible states of an entity
            path = entity + "_" + state.name() + ".png";  // path of the file related to the considered state
            try {
                InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);  // returns an input stream for reading the specified resource.
                spritesImage = ImageIO.read(is);  // load the sprites Image
                int numSprite = spritesImage.getWidth() / w;  // number of sprites in the image
                BufferedImage[] spriteArray = getSpriteArray(spritesImage, numSprite);  // array of sprites
                spriteMap.put(state, spriteArray);  // insertion of the sprites array inside the map
            } catch (IOException e) {
                System.out.println("ERROR: could not load file: " + path);
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: could not load file: " + path);
            }
        }
    }

    private BufferedImage[] getSpriteArray(BufferedImage sprite, int numSprite) {
        BufferedImage[] spriteArray = new BufferedImage[numSprite];

        for (int x = 0; x < numSprite; x++) {
            spriteArray[x] = sprite.getSubimage(x * w, 0, w, h);
        }
        return spriteArray;
    }

    public BufferedImage[] getSprite(EntityEnum state) {
        return spriteMap.get(state);
    }

}
