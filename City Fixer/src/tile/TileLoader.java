package tile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TileLoader {
    private static final int TOTAL_TILES = 266; // Total number of tiles
    private Tile[] tiles;

    public TileLoader() {
        tiles = new Tile[TOTAL_TILES];
        loadTileImages();
    }

    private void loadTileImages() {
        for (int i = 0; i < tiles.length; i++) {
            try {
                BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + i + ".png"));
                tiles[i] = new Tile(image);
            } catch (IOException e) {
                System.err.println("Tile image not found: /tiles/" + i + ".png");
            }
        }
    }

    public Tile getTile(int index) {
        if (index >= 0 && index < tiles.length) {
            return tiles[index];
        }
        return null;
    }
}
