package minigame.trees;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Lucas Namba
 * This class serves as the parent class of the tools in the minigame (shovel, time, seeds, and water)
 */
public class Tools extends Rectangle{

    BufferedImage image;

    /**
     * The contructor uses the x and y value to move the object around and check if they are colliding with anything
     * @param x
     * @param y
     * @param imageName is used to find the right image in the game's file
     */
    public Tools(int x, int y, String imageName){
        this.x = x;
        this.y = y;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/trees/" + imageName + ".png"));
            this.width = image.getWidth();
            this.height = image.getHeight();
        } catch (IOException e) {
            System.err.println("Tile image not found: /images/trees" + imageName + ".png");
        }
    }

	/**
     * if a mouse is pressed on top of the tool it will centralize the tool to the position of the mouse
     * @param e for mouse location
     */
    public void mousePressed(MouseEvent e) {
        if(e.getX() <= this.x + 59 && e.getX() >= this.x && e.getY() <= this.y + 59 && e.getY() >= this.y){
            this.x = e.getX() - 30;
            this.y = e.getY() - 30;
        }
    }

    /**
     * if a mouse is released on top of the tool it will centralize the tool to the position of the mouse
     * @param e for mouse location
     */
    public void mouseDragged(MouseEvent e){
        if(e.getX() <= this.x + 59 && e.getX() >= this.x && e.getY() <= this.y + 59 && e.getY() >= this.y){
            this.x = e.getX() - 30;
            this.y = e.getY() - 30;
        }
    }

    /**
     * if a mouse is dragged on top of the tool it will centralize the tool to the position of the mouse
     * @param e for mouse location
     */
    public void mouseReleased(MouseEvent e){
        if(e.getX() <= this.x + 59 && e.getX() >= this.x && e.getY() <= this.y + 59 && e.getY() >= this.y){
            this.x = e.getX() - 30;
            this.y = e.getY() - 30;
        }
    }

    /**
     * this method is used to print the tool in the panel
     * @param g to draw the image
     */
    public void draw(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(image, x, y, null);
    }
}
