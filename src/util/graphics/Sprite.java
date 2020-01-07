package util.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import util.Position;

public class Sprite {

    private BufferedImage spriteMatrix;
    private final int TILE_SIZE = 32;
    public int w;
    public int h;
    public int wSprite;
    public int hSprite;

    public Sprite(String file) {
        w = TILE_SIZE;
        h = TILE_SIZE;

        spriteMatrix = loadSprite(file);

        wSprite = spriteMatrix.getWidth() / w;
        hSprite = spriteMatrix.getHeight() / h;
    }

    public Sprite(String file, int w, int h) {
        this.w = w;
        this.h = h;

        System.out.println("Loading: " + file + "...");
        spriteMatrix = loadSprite(file);

        wSprite = spriteMatrix.getWidth() / w;
        hSprite = spriteMatrix.getHeight() / h;
    }

    private BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch (IOException e) {
            System.out.println("ERROR: could not load file: " + file);
        }

        return sprite;
    }

    public BufferedImage getSprite(int x, int y) {
        return spriteMatrix.getSubimage(x * w, y * h, w, h);
    }

    public BufferedImage getLetter(char letter) {
        int value = letter;

        int x = value % wSprite;
        int y = value / wSprite;

        return getSprite(x, y);
    }

    public BufferedImage getMatrix() {
        return spriteMatrix;
    }

    public static void drawArray(Graphics2D g, Sprite letter, String word, Position pos, int width, int height, int xOffset, int yOffset) {
        float x = pos.getX();
        float y = pos.getY();

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != 32) {
                g.drawImage(letter.getLetter(word.charAt(i)), (int) x, (int) y, width, height, null);
            }

            x += xOffset;
            y += yOffset;
        }
    }

    public static void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Position pos, int width, int height, int xOffset, int yOffset) {
        float x = pos.getX();
        float y = pos.getY();

        for (int i = 0; i < img.size(); i++) {
            if (img.get(i) != null) {
                g.drawImage(img.get(i), (int) x, (int) y, width, height, null);
            }

            x += xOffset;
            y += yOffset;
        }
    }

    public static void drawArray(Graphics2D g, BufferedImage img, Position pos, int width, int height) {
        float x = pos.getX();
        float y = pos.getY();

        g.drawImage(img, (int) x, (int) y, width, height, null);
    }

}
