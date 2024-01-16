package tile;
import java.awt.Graphics2D;
import main.Map;

public class TileManager {
	Map m;
	private MapLayer baseLayer;
    private MapLayer middleLayer;
    private MapLayer topLayer;
    
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

        // Initialize renderers
        this.baseRenderer = new LayerRenderer(baseLayer, tileLoader, tileSize);
        this.middleRenderer = new LayerRenderer(middleLayer, tileLoader, tileSize);
        this.topRenderer = new LayerRenderer(topLayer, tileLoader, tileSize);
    }
    
    public void draw(Graphics2D g2) {
        baseRenderer.drawLayer(g2, m.screenCol, m.screenRow);
        middleRenderer.drawLayer(g2, m.screenCol, m.screenRow);
        topRenderer.drawLayer(g2, m.screenCol, m.screenRow);
    }
}
