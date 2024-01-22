package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import tile.TileManager;
import minigame.MinigameManager;

/**
 * @author Lucas Namba
 * This class is used to handle any mouse clicks. Being the starting screen or main game 
 * (since minigames hold different logic specific to them this class do not support their properties)
 */
public class Mouse implements MouseListener{

    TileManager tileM;
    MinigameManager miniM;
    int minigame;

    public Mouse(TileManager tileManager, MinigameManager miniM){
        tileM = tileManager;
        this.miniM = miniM;
    }

    private void handleMouseClick(MouseEvent e) {
        if(miniM.getMinigame() >= 0){
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
                    if(!miniM.getMinigameCompleted(id))
                        miniM.setMinigame(id);
                }
            }
        }
        miniM.repaint();
    }

    private boolean boxClick(MouseEvent e){
        if(e.getX() >= 363 && e.getX() <= 544 && e.getY() >= 72 && e.getY() <= 119)
            return true;
        return false;
    }

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

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        handleMouseClick(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    public void mouseDragged(MouseEvent e){
    }
}
