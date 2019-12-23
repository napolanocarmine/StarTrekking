package tiles;

import graphics.Sprite;
import java.awt.Graphics2D;
import java.util.HashMap;
import tiles.blocks.Block;
import tiles.blocks.HoleBlock;
import tiles.blocks.ObjBlock;
import util.Position;

public class TileMapObs extends TileMap {

    public static int width;
    public static int height;

    public TileMapObs(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        Block tempBlock;
        String[] block = data.split(",");

        for (int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
            if (temp != 0) {
                tempBlock = new HoleBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)),
                        new Position((int) (i % width) * tileWidth, (int) (i / width) * tileHeight), tileWidth, tileHeight);
                tmobs_blocks.put(String.valueOf((int) (i % width)) + "," + String.valueOf((int) (i / width)), tempBlock);
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        for (Block block : tmobs_blocks.values()) {
            block.render(g);
        }
    }
}

/*if (temp == 172) {
                    tempBlock = new HoleBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)),
                            new Position((int) (i % width) * tileWidth, (int) (i / width) * tileHeight), tileWidth, tileHeight);
                }*/