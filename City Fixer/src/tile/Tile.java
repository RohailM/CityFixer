package tile;

import java.awt.image.BufferedImage;

/**
 * Represents a single tile in the game
 * @author Rohail Memon and Brianna and Wahab designed the map
 */
public class Tile {
	// Image that represents the visual appearance of the tile
	public BufferedImage image;
	
	/**
	 * Constructs a new Tile with the specified image
	 * @param image The image to be used for the tile
	 */
	public Tile(BufferedImage image) {
		this.image = image;
	}
	
	/**
	 * Retrieves the image of the tile
	 * @return The image of the tile
	 */
    public BufferedImage getImage() {
        return image;
    }
}
