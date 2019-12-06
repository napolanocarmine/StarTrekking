package graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import util.Position;

public class Sprite {

    private BufferedImage spriteMatrix;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 32;
    public int w;
    public int h;
    public int wSprite;
    public int hSprite;
    
    public Sprite(String file){
        w = TILE_SIZE;
        h = TILE_SIZE;
        
        spriteMatrix = loadSprite(file);
        
        wSprite = spriteMatrix.getWidth() / w;
        hSprite = spriteMatrix.getHeight() / h;
        loadSpriteArray();
    }
    
    public Sprite(String file, int w, int h){
        this.w = w;
        this.h = h;
        
        System.out.println("Loading: " + file + "...");
        spriteMatrix = loadSprite(file);
        
        wSprite = spriteMatrix.getWidth() / w;
        hSprite = spriteMatrix.getHeight() / h;
        loadSpriteArray();
    }
    
    public void setSize(int width, int height){
        setWidth(width);
        setHeight(height);
    }

    private void setHeight(int i) {
        w = i;
        wSprite = spriteMatrix.getWidth() / w;
    }

    private void setWidth(int i) {
        h = i;
        hSprite = spriteMatrix.getHeight()/ h;
    }
    
    public int getWidth(){return w;}
    public int getHeight(){return h;}
    
    private BufferedImage loadSprite(String file){
        BufferedImage sprite = null;
        try{
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        }catch(Exception e){
            System.out.println("ERROR: could nor load file: " + file);
        }
        
        return sprite;
    }
    
    public void loadSpriteArray(){
        spriteArray = new BufferedImage[hSprite][wSprite];
        
        for(int y=0; y<hSprite; y++){
            for(int x=0; x<wSprite; x++){
                spriteArray[y][x] = getSprite(x, y);
            }
        }
    }
    
    public BufferedImage getSpriteSheet(){return spriteMatrix;}
    
    public BufferedImage getSprite(int x, int y){
        return spriteMatrix.getSubimage(x*w, y*h, w, h);
    }
    
    public BufferedImage[] getSpriteArray(int i){
        return spriteArray[i]; 
    }
    
    public BufferedImage[][] getSpriteArray2(int i){
        return spriteArray; 
    }
    
    public BufferedImage getLetter(char letter){
        int value = letter;
        
        int x = value % wSprite;
        int y = value / wSprite;
        
        return getSprite(x, y);
    }
    
    public static void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Position pos, int width, int height, int xOffset, int yOffset){
        float x = pos.getX();
        float y = pos.getY();
        
        for(int i=0; i<img.size(); i++){
            if(img.get(i) != null){
                g.drawImage(img.get(i), (int) x, (int) y, width, height, null);
            }
            
            x += xOffset;
            y += yOffset;
        }
    }
    
    public static void drawArray(Graphics2D g, Sprite letter, String word, Position pos, int width, int height, int xOffset, int yOffset){
        float x = pos.getX();
        float y = pos.getY();
        
        for(int i=0; i<word.length(); i++){
            if(word.charAt(i) != 32){
                g.drawImage(letter.getLetter(word.charAt(i)), (int) x, (int) y, width, height, null);
            }
            
            x += xOffset;
            y += yOffset;
        }
    }
    
}
