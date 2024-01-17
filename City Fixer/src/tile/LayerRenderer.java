package tile;

import java.awt.Graphics2D;

public class LayerRenderer {
    private MapLayer layer;
    private TileLoader tileLoader;
    private int tileSize;

    public LayerRenderer(MapLayer layer, TileLoader tileLoader, int tileSize) {
        this.layer = layer;
        this.tileLoader = tileLoader;
        this.tileSize = tileSize;
    }

    public void drawLayer(Graphics2D g2, int cols, int rows) {
        for (int row = 0; row < rows; row++) {
            int x = 0;
            for (int col = 0; col < cols; col++) {
                Tile tile = tileLoader.getTile(layer.getTileIndex(col, row));
                if (tile != null && tile.getImage() != null) {
                    g2.drawImage(tile.getImage(), x, (row * tileSize) - 15, tileSize, tileSize, null);
                }
                x += tileSize;
            }
        }
    }
}
