package tiles;

import util.graphics.TileSet;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * class which represents the map of a level as a set of layers
 * @author Star Trekking
 */
public class LayerFacade {

    private ArrayList<Layer> tm;
    private static int mapWidth;

    /**
     * Constructor of LayerFacade object 
     * @param path path of the xml file which represent the map
     */
    public LayerFacade(String path) {
        tm = new ArrayList<>();
        addTileMap(path, 16, 16);
    }

    private void addTileMap(String path, int blockWidth, int blockHeight) {
        
        String imagePath;   // path of the tile set image

        int width = 0;  // width of the map
        int height = 0; // height of the map
        int tileWidth;  // width of a single tile
        int tileHeight; // height of a single tile
        int tileColumns;    // columns of the tile set
        int layers = 0; // number of the layers of the map
        TileSet ts; // tile set

        String[] data = new String[10]; // Array of strings which contains the matrixs that represents the layers of the map

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);    // returns an input stream for reading the specified resource.
            Document doc = builder.parse(is);   // object used to read the tags content of the xml file
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("tileset");    // acquire the list of tileset elements of the map
            Node node = list.item(0);
            Element eElement = (Element) node;

            imagePath = eElement.getAttribute("name");  // acquire the tileset path
            tileWidth = Integer.parseInt(eElement.getAttribute("tilewidth"));   // acquire the width of a single tile
            tileHeight = Integer.parseInt(eElement.getAttribute("tileheight")); // acquire the height of a single tile
            tileColumns = Integer.parseInt(eElement.getAttribute("columns"));   // acquire the number of the columns of the tileset

            ts = new TileSet("tiles/" + imagePath + ".png", tileWidth, tileHeight); // tile set

            list = doc.getElementsByTagName("layer");   // acquire the list of the layers of the map
            layers = list.getLength();  // number of the layers

            // iterate over the layers elements in order to create the actual layers object
            for (int i = 0; i < layers; i++) {
                node = list.item(i);
                eElement = (Element) node;
                if (i <= 0) {
                    width = Integer.parseInt(eElement.getAttribute("width"));   // acquire the map width
                    mapWidth = width;
                    height = Integer.parseInt(eElement.getAttribute("height")); // acquire the map height
                }

                data[i] = eElement.getElementsByTagName("data").item(0).getTextContent();   // save the matrix of strings related to a singel layer

                // create the solid layer
                if(i == 1){
                    tm.add(new SolidLayer(data[i], ts, width, height, blockWidth, blockHeight, tileColumns));
                }
                // create the obstacles layer
                else if(i == 2){
                    tm.add(new ObstaclesLayer(data[i], ts, width, height, blockWidth, blockHeight, tileColumns));
                }
                // create the other normal layers
                else {
                    tm.add(new NormLayer(data[i], ts, width, height, blockWidth, blockHeight, tileColumns));
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR - TILEMANAGER: can not read tilemap:" + path);
            System.exit(0);
        }
    }
    
    /**
     * get the map width
     * @return map width
     */
    public static int getMapWidth(){ return mapWidth; }

    /**
     * Render the layers of the map
     * @param g Graphic 2d objects
     */
    public void render(Graphics2D g) {
        for (int i = 0; i < tm.size(); i++) {
            tm.get(i).render(g);
        }
    }
}
