package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Map;
import tile.TileManager;
import minigame.MinigameManager;

/**
 * @author Lucas Namba
 * This class is used to handle mouse click in the main map
 * (since minigames hold different logic specific to them this class do not support their properties)
 */
public class Mouse implements MouseListener{

	Map map;
    TileManager tileM;
    MinigameManager miniM;
    int minigame;

    /**
     * @param tileManager to check the index of a tile
     * @param miniM to manage which minigame should be played
     */
    public Mouse(Map m, TileManager tileManager, MinigameManager miniM){
    	this.map = m;
        tileM = tileManager;
        this.miniM = miniM;
    }

    /**
     * This method is what handles the algorithm of a mouse press
     * There are 2 uses of the mouse in the main map: tile pressing and dialogue box pressing
     * 
     * To check for each mode it checks if the minigame selected is a minigame at all (as miniM.getMinigame() = -1 means there is no minigame):
     * 1. if miniM.getMinigame() have any other quantized value then it will check the dialog box was clicked in the right spot to then start the minigame
     * 2. if miniM.getMinigame() is -1 then it will check if specific tiles where clicked (such as the house tiles), 
     * and then check which minigame should appear out of that tile
     * @param e
     */
    private void handleMouseClick(MouseEvent e) {
        if(miniM.getMinigame() >= 0 || map.sendWelcomeMessage){
            if(boxClick(e))
                miniM.runMinigame(miniM.getMinigame());
            miniM.setMinigame(-1);
        }else{
            int col = e.getX() / 32;
            int row = (e.getY() / 32)-1;
            int x = tileM.getTopLayer().getTileType(col, row);
            int id = checkRange(x);
            
            System.out.println("Column: " + col + "\nRow: " + row);

            if (col >= 0 && row >= 0 && col < 19 && row < 21) {
                if(id >= 0){
                    if(!miniM.getMinigameCompleted()[id])
                        miniM.setMinigame(id);
                }
            }
        }
        miniM.repaint();
    }

    /**
     * This method checks if, in the dialog box, the start minigame button was pressed
     * @param e so it has the mouse's location
     * @return if the box to start the minigame was clicked or not
     */
    private boolean boxClick(MouseEvent e){
        if(e.getX() >= 363 && e.getX() <= 544 && e.getY() >= 72 && e.getY() <= 119)
            return true;
        return false;
    }

    /**
     * This method will check the minigame that should be played depending on the tile clicked
     * @param tileNumber to check if the tile clicked has an index inside of the predetermined ranges
     * @return what minigame this range is related to
     */
    private int checkRange(int x){
        if(x >= 76 && x <= 93)
            return 0;
        else if(x == 256)
            return 1;
        else if(x >= 149 && x <= 169)
            return 2;
        else if(x >= 118 && x <= 148)
            return 3;
        else if(x >= 49 && x <= 63)
            return 4;
        return -1;
    }

    /**
     * This method takes reads the mouse and checks if it was pressed
     * @param e to get the mouse's location
     */
    @Override
    public void mousePressed(MouseEvent e) {
        handleMouseClick(e);
    }

    /*****************************
     * Since the only function in the map relates to mouse pressing no other mouse input is utilized
     *****************************/
    
    /**
     * Unused
     */
    @Override
    public void mouseClicked(MouseEvent e) {}

    /**
     * Unused
     */
    @Override
    public void mouseEntered(MouseEvent e) {}

    /**
     * Unused
     */
    @Override
    public void mouseExited(MouseEvent e) {}

    /**
     * Unused
     */
    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
     * Unused
     */
    public void mouseDragged(MouseEvent e){}
}
