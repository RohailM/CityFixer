package minigame.factory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

/**
 * The FactoryImage class represents an image used in the Sustainability Factory Restoration Game.
 * @author Wahab Sattar
 */
public class FactoryImage {
    private Image image;

    /**
     * Constructor for the FactoryImage class.
     *
     * @param imagePath The path to the image file.
     */
    public FactoryImage(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Draw the factory image on the specified graphics context.
     *
     * @param g The graphics context on which to draw the image.
     */
    /**
 * Draw the factory image on the specified graphics context.
 *
 * @param g The graphics context on which to draw the image.
 * @param width The width of the area to draw the image.
 * @param height The height of the area to draw the image.
 */
public void draw(Graphics g, int width, int height) {
    if (image != null) {
        g.drawImage(image, 0, 0, width, height, null);
    }
}

}

