package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
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
        
        BufferedImage sprite = null;
        String file;
        spriteMap = new HashMap<>();
        
        for (EntityState state : EntityState.values()) {
            file = (entity + "_" + state.name()+".png");
            try {
                sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
                int wSprite = sprite.getWidth()/w;
                BufferedImage[] spriteArray = getSpriteArray(sprite, wSprite);
                spriteMap.put(state, spriteArray);
            } catch (IOException e) {
                System.out.println("ERROR: could not load file: " + file);
            } catch(IllegalArgumentException e){
                //System.out.println("ERROR: could not find file: " + file);
            }
        }   
    }
    
    private BufferedImage[] getSpriteArray(BufferedImage sprite, int wSprite){
        BufferedImage[] spriteArray = new BufferedImage[wSprite];

        for (int x = 0; x < wSprite; x++) {
            spriteArray[x] = sprite.getSubimage(x * w, 0, w, h);
        }
        return spriteArray;
    }

    public BufferedImage[] getSprite(EntityState state){
        return spriteMap.get(state);
    }
//    
//    public void printStates(){
//        for(EntityState state: spriteMap.keySet()){
//            System.out.println("STATE: " + state);
//        }
//    }
    
}
