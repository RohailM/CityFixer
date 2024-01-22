package tile;

import java.awt.Graphics2D;

/**
 * The LayerRenderer class is responsible for rendering a map layer
 * It draws the tiles of the layer on the screen based on the tile data provided by a TileLoader
 * @author Rohail Memon
 */
public class LayerRenderer {
    private MapLayer layer;
    private TileLoader tileLoader;
    private int tileSize;

    /**
     * Constructs a LayerRenderer for a specific map layer
     * @param layer The map layer to render
     * @param tileLoader The TileLoader to use for retrieving tile images
     * @param tileSize The size of each tile
     */
    public LayerRenderer(MapLayer layer, TileLoader tileLoader, int tileSize) {
        this.layer = layer;
        this.tileLoader = tileLoader;
        this.tileSize = tileSize;
    }

    /**
     * Draws the layer
     * @param g2 The Graphics2D context to draw on
     * @param cols The number of columns in the layer
     * @param rows The number of rows in the layer
     */
    public void drawLayer(Graphics2D g2, int cols, int rows) {
        for (int row = 0; row < rows; row++) {
            int x = 0;
            for (int col = 0; col < cols; col++) {
                Tile tile = tileLoader.getTile(layer.getTileType(col, row));
                if (tile != null && tile.getImage() != null) {
                    g2.drawImage(tile.getImage(), x - 1, (row * tileSize) - 20, tileSize, tileSize, null);
                }
                x += tileSize;
            }
        }
    }
}
