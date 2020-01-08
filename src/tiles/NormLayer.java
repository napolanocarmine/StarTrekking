package tiles;

import util.graphics.Sprite;
import java.awt.Graphics2D;
import java.util.ArrayList;
import tiles.blocks.Block;
import tiles.blocks.NormBlock;
import util.Position;

public class NormLayer extends Layer {

    public NormLayer(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {

        blocks = new ArrayList<>();

        String[] block = data.split(",");
        for (int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
            if (temp != 0) {
                blocks.add(new NormBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)),
                        new Position((int) (i % width) * tileWidth, (int) (i / width) * tileHeight), tileWidth, tileHeight));
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).render(g);
        }
    }

}
