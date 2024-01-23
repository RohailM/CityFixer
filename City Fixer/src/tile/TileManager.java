package tile;
import java.awt.Graphics2D;
import java.util.Arrays;

import main.Map;

/**
 * The TileManager class is responsible for managing the tiles that constitute the game map
 * It is the master class that handles the loading, drawing, and manipulation of tiles and tile layers
 * @author Rohail Memon
 */
public class TileManager {
	Map m;
	private MapLayer baseLayer;
    private MapLayer middleLayer;
    private MapLayer topLayer;
    private MapLayer fixedLayer;
    
    private LayerRenderer baseRenderer;
    private LayerRenderer middleRenderer;
    private LayerRenderer topRenderer;
    
    private TileLoader tileLoader;
    private int tileSize;
    
    /**
     * Constructs a TileManager for a given map
     * It initializes all the layers and renderers used in the game map
     * @param m The map that this TileManager belongs to
     * @param savedMapPath The path to the saved map file (if it exists)
     */
    public TileManager(Map m, String savedMapPath) {
    	this.m = m;
        this.tileSize = m.tileSize;
        this.tileLoader = new TileLoader();
        
        // Initialize layers
        this.baseLayer = new MapLayer("res/maps/lowLayer.txt", m.screenCol, m.screenRow);
        this.middleLayer = new MapLayer("res/maps/midLayer.txt", m.screenCol, m.screenRow);
        
        // Load the top layer from the saved map path (if it exists), otherwise load the default map
        if (savedMapPath != null) {
            this.topLayer = new MapLayer(savedMapPath, m.screenCol, m.screenRow); // Load the saved map
        } else {
            this.topLayer = new MapLayer("res/maps/topLayer.txt", m.screenCol, m.screenRow); // Load the default map
        }
        
        this.fixedLayer = new MapLayer("res/maps/fixedLayer.txt", m.screenCol, m.screenRow);

        // Initialize renderers for each layer
        this.baseRenderer = new LayerRenderer(baseLayer, tileLoader, tileSize);
        this.middleRenderer = new LayerRenderer(middleLayer, tileLoader, tileSize);
        this.topRenderer = new LayerRenderer(topLayer, tileLoader, tileSize);
    }

    /**
     * Retrieves the top layer of the map
     * @return The top layer of the map
     */
    public MapLayer getTopLayer(){
        return topLayer;
    }
    
    /**
     * Adds solar panels to the houses by replacing the tiles in the house region of the map to those with solar panels
     */
    public void fixHouses() {
    	
        int rowStart = 1, rowEnd = 2;
        int colStart = 6, colEnd = 16;
        
        // Change tiles in specific region from their unfixed state --> fixed state
        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = colStart; j <= colEnd; j++) {
                topLayer.replaceTile(topLayer.getTileType(j, i), fixedLayer.getTileType(j, i));
            }
        }
 
    }
    
    /**
     * Cleans the water by removing the dirty layer on top of the water
     */
    public void cleanWater() {
    	topLayer.replaceTile(256, -1);
    }
    
    /**
     * Plants trees by setting the tree tiles in the tree region to their fixed version
     */
    public void plantTrees() {
        int rowStart = 15, rowEnd = 20;
        int colStart = 6, colEnd = 12;
        
        // Change tiles in specific region from their unfixed state --> fixed state
        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = colStart; j <= colEnd; j++) {
                topLayer.setTile(j, i, fixedLayer.getTileType(j, i));
            }
        }
    }
    
    /**
     * Rehabilitates the factory by replacing the factory tiles with their fixed counterpart.
     */
    public void rehabilitateFactory() {
        int rowStart = 6, rowEnd = 11;
        int colStart = 7, colEnd = 18;
        
        // Change tiles in specific region from their unfixed state --> fixed state
        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = colStart; j <= colEnd; j++) {
                topLayer.setTile(j, i, fixedLayer.getTileType(j, i));
            }
        }
    }
    
    public void repairCellTower() {
    	
    }
    
    /**
     * Draws each layer of the map using the corresponding layer renderer
     * @param g2 The Graphics2D instance used for drawing
     */
    public void draw(Graphics2D g2) {
        baseRenderer.drawLayer(g2, m.screenCol, m.screenRow);
        middleRenderer.drawLayer(g2, m.screenCol, m.screenRow);
        topRenderer.drawLayer(g2, m.screenCol, m.screenRow);
    }
}
