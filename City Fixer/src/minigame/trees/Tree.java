package minigame.trees;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Lucas Namba
 * This class does not inherit the same functions of the tools, therefore it is a class of itself
 * This class manages the data and changes done to the tree in the minigame
 */
public class Tree extends Rectangle{
    BufferedImage image;
    int stage = -1;
    //0 stump
    //1 hole
    //2 sapling
    //3 tree

    /**
     * the x and y values are used to know if a tool is intersecting with the tree
     * @param x
     * @param y
     * 
     * changestage() is called so the object can get the image required at the start of the minigame
     */
    public Tree(int x, int y){
        this.x = x;
        this.y = y;
        changeStage();
    }

    /**
     * This class is used to change the image of the tree everytime the right tool is released in it
     */
    public void changeStage(){
        stage++;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/trees/" + loadImage() + ".png"));
            this.width = image.getWidth();
            this.height = image.getHeight();
        } catch (IOException e) {
            System.err.println("Tile image not found: /images/trees/" + loadImage() + ".png");
        }
    }

    /**
     * This method is used to check which image should be used for the current stage of the tree
     * (with small ajustments to the image's dimension)
     * @return
     */
    public String loadImage(){
        if(stage ==0)
            return "stump";
        else if(stage == 1)
            return "ground_hole";
        else if(stage == 2){
            this.x -= 5;
            this.y -= 30;
            return "sapling";
        }else if(stage == 3){
            this.x += 5;
            this.y -= 30;
            return "small_tree";
        }
        this.x -= 40;
        this.y -= 30;
        return "tree";
    }

    /**
     * this method return which stage the tree is at
     * it is only used to know if the game has been completed (tree reached stage 4)
     * @return
     */
    public int getTreeStage(){
        return stage;
    }

    /**
     * this method is used to draw the tree
     * @param g to draw the image
     */
    public void draw(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(image, x, y, null);
    }
}
