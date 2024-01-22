package minigame.trees;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import minigame.MinigameManager;

/**
 * @author Lucas Namba
 * This class is what loads the logistic and images of the minigame
 */
public class TreePanel extends JPanel implements MouseListener, MouseMotionListener{

    TreeMinigame frame;
    BufferedImage image;

    MinigameManager miniM;

    Tree tree;

    Tools[] tools = new Tools[4];
    //0 shovel
    //1 seeds
    //2 water
    //3 time

    /**
     * The contructor creates all the objects necessary for the game
     * including: tools, tree, and mouseListener and MouseMotionListener
     * 
     * @param frame So that when the minigame is complete it can close the window
     * @param miniM So that when the minigame is complete its state is stored as complete
     */
    public TreePanel(TreeMinigame frame, MinigameManager miniM){
        tree = new Tree(200,200);
        tools[0] = new Shovel(502, 365, "shovel");
        tools[1] = new Seeds(502, 265, "acorn");
        tools[2] = new Water(502, 42, "watering_can");
        tools[3] = new Time(502, 145, "time");
        this.frame = frame;
        this.miniM = miniM;
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /**
     * This Method checks if the right tools are touching the tree at the right stages
     * it also deletes any tool that has been already used
     * @return
     */
    public boolean isColliding(){
        if(tools[tree.stage] != null){
            if(tree.intersects(tools[tree.stage])){
                tools[tree.stage] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to draw the stage the minigame is played and the objects the minigame uses
     * @param g so it can draw the images
     */
    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(new Color(0,204,0));
        g2D.fillRect(0, 0, 600, 500);

        g2D.setColor(Color.black);
        g2D.fillRect(475, 0, 125, 500);

        for(int i = 0; i <4 ; i++){
            int y = 30 + i * 110;
            g2D.setColor(new Color(64,64,64));
            g2D.fillRoundRect(497, y, 70, 70, 32,32);

            g2D.setColor(Color.white);
            g2D.setStroke(new BasicStroke(5));
            g2D.drawRoundRect(497, y, 70, 70, 32,32);
        }
        
        tree.draw(g);
        for(int i = 0; i < 4; i++){
            if(tools[i] != null)
                tools[i].draw(g);
        }
    }

    /**
     * This method is used to see if a tool has been pressed or not
     * if it has been pressed it will repaint the tool with its new location
     * @param e so it can have the mouse location
     */
    @Override
    public void mousePressed(MouseEvent e) {
        for(int i = 0; i < 4; i++){
            if(tools[i] != null)
                tools[i].mousePressed(e);
        }
        repaint();
    }

    /**
     * This method is used to see if a tool has been released or not
     * if it has been released it will repaint the tool with it's new location
     * if the right tool is colliding with the tree that means that the tree can go to its next stage
     * 
     * Since the tree can only be changed if the tool is released in it that means that the game can only be completed after the final tool has been released
     * thanks to this it is only necessary to check if the game has been completed after a mouse release
     * If the game has, yes, been completed then run the "credits", close the window, and set the minigame as complete
     * 
     * @param e so it can have the mouse location
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        for(int i = 0; i < 4; i++){
            if(tools[i] != null)
                tools[i].mouseReleased(e);
        }

        if(isColliding())
            tree.changeStage();

        repaint();
        
        if(tree.getTreeStage() == 4){
            JOptionPane.showMessageDialog(frame, "Congratulations! You have revived the forest!\n" + 
                                                "Fun fact: Vegetation increases air quality, energy efficiency\n" +
                                                "and stormwater management, while also bringing life to\n" + 
                                                "neighbourhoods and commercial buildings");
            frame.dispose(); // Exit the game after cleaning the lake
            miniM.setMinigameComplete(2, true);
        }
        
    }

    /**
     * This method is used to see if a tool has been dragged or not
     * if it has been dragged it will repaint the tool with its new location
     * @param e so it can have the mouse location
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        for(int i = 0; i < 4; i++){
            if(tools[i] != null)
                tools[i].mouseDragged(e);;
        }
        repaint();
    }

    /**
     * Unused
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Unused
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Unused
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Unused
     */
    @Override
    public void mouseMoved(MouseEvent e) {
    }
}