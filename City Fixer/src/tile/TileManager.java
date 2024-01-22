package tile;
import java.awt.Graphics2D;
import java.util.Arrays;

import main.Map;

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
    
    public TileManager(Map m) {
    	this.m = m;
        this.tileSize = m.tileSize;
        this.tileLoader = new TileLoader();
        
        // Initialize layers
        this.baseLayer = new MapLayer("/maps/lowLayer.txt", m.screenCol, m.screenRow);
        this.middleLayer = new MapLayer("/maps/midLayer.txt", m.screenCol, m.screenRow);
        this.topLayer = new MapLayer("/maps/topLayer.txt", m.screenCol, m.screenRow);
        this.fixedLayer = new MapLayer("/maps/fixedLayer.txt", m.screenCol, m.screenRow);

        // Initialize renderers
        this.baseRenderer = new LayerRenderer(baseLayer, tileLoader, tileSize);
        this.middleRenderer = new LayerRenderer(middleLayer, tileLoader, tileSize);
        this.topRenderer = new LayerRenderer(topLayer, tileLoader, tileSize);
    }

    public MapLayer getTopLayer(){
        return topLayer;
    }
    
    public void fixHouses() {
    	
        int rowStart = 1, rowEnd = 2;
        int colStart = 6, colEnd = 16;
        
        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = colStart; j <= colEnd; j++) {
                topLayer.replaceTile(topLayer.getTileType(j, i), fixedLayer.getTileType(j, i));
            }
        }
 
    }
    
    public void cleanWater() {
    	topLayer.replaceTile(256, -1);
    }
    
    public void plantTrees() {
        int rowStart = 15, rowEnd = 20;
        int colStart = 6, colEnd = 12;
        
        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = colStart; j <= colEnd; j++) {
                topLayer.setTile(j, i, fixedLayer.getTileType(j, i));
            }
        }
    }
    
    public void rehabilitateFactory() {
        int rowStart = 6, rowEnd = 11;
        int colStart = 7, colEnd = 18;
        
        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = colStart; j <= colEnd; j++) {
                topLayer.setTile(j, i, fixedLayer.getTileType(j, i));
            }
        }
    }
    
    public void repairCellTower() {
    	
    }
    
    public void draw(Graphics2D g2) {
        baseRenderer.drawLayer(g2, m.screenCol, m.screenRow);
        middleRenderer.drawLayer(g2, m.screenCol, m.screenRow);
        topRenderer.drawLayer(g2, m.screenCol, m.screenRow);
    }
}
