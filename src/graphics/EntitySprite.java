package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import util.EntityEnum;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class EntitySprite {

    public HashMap<EntityEnum, BufferedImage[]> spriteMap;

    public int w;
    public int h;

    public EntitySprite(String file, int w, int h) {
        this.w = w;
        this.h = h;

        loadSprites(file);
    }

    private void loadSprites(String entity) {

        BufferedImage sprite;
        String file;
        spriteMap = new HashMap<>();

        for (EntityEnum state : EntityEnum.values()) {
            file = entity + "_" + state.name() + ".png";
            try {
                InputStream is = this.getClass().getClassLoader().getResourceAsStream(file);
                sprite = ImageIO.read(is);
                int wSprite = sprite.getWidth() / w;
                BufferedImage[] spriteArray = getSpriteArray(sprite, wSprite);
                spriteMap.put(state, spriteArray);
            } catch (IOException e) {
                System.out.println("ERROR: could not load file: " + file);
            } catch (IllegalArgumentException e) {
                
            }
        }
    }

    private BufferedImage[] getSpriteArray(BufferedImage sprite, int wSprite) {
        BufferedImage[] spriteArray = new BufferedImage[wSprite];

        for (int x = 0; x < wSprite; x++) {
            spriteArray[x] = sprite.getSubimage(x * w, 0, w, h);
        }
        return spriteArray;
    }

    public BufferedImage[] getSprite(EntityEnum state) {
        return spriteMap.get(state);
    }

}
