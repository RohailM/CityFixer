package tile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The TileLoader class is responsible for loading tile images from resources and handling the different types of tiles
 * @author Rohail Memon
 */
public class TileLoader {
    private static final int TOTAL_TILES = 266; // Total number of distinct tiles
    private Tile[] tiles; // Array to hold all the tile types

    /**
     * Constructs a TileLoader and initializes the tiles by loading them from the resources
     */
    public TileLoader() {
        tiles = new Tile[TOTAL_TILES];
        loadTileImages();
    }

    /**
     * Loads tile images from the resources
     * Each tile image is expected to be named with its index in the '/tiles/' directory
     */
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

    /**
     * Retrieves a tile based on its index
     * @param index The index of the tile to retrieve
     * @return The Tile object at the specified index, or null if the index is out of bounds
     */
    public Tile getTile(int index) {
        if (index >= 0 && index < tiles.length) {
            return tiles[index];
        }
        return null;
    }
}
