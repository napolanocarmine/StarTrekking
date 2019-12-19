package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import util.EntityState;

public class EntitySprite {

    public HashMap<EntityState, BufferedImage[]> spriteMap;

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

        System.out.println(entity);
        for (EntityState state : EntityState.values()) {
            file = entity + "_" + state.name() + ".png";
            //System.out.println(file);
            try {
//                if( entity.equals("Entity/shot")){
//                    file = entity + "_" + "ATTACK" + ".png";
//                    System.err.println(file);
//                }
                
                InputStream is = this.getClass().getClassLoader().getResourceAsStream(file);
                //sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
                //if( is != null){
                    
                    sprite = ImageIO.read(is);
                    System.out.println(file);
                    int wSprite = sprite.getWidth() / w;
                    BufferedImage[] spriteArray = getSpriteArray(sprite, wSprite);
                    spriteMap.put(state, spriteArray);
                //}
            } catch (IOException e) {
                System.out.println("ERROR: could not load file: " + file);
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: could not load file: " + file);
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

    public BufferedImage[] getSprite(EntityState state) {
        return spriteMap.get(state);
    }

}
