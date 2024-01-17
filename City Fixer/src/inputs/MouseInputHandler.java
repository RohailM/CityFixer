package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Map;
import tile.TileManager;

public class MouseInputHandler implements MouseListener{

    private Map map;
    private TileManager tileM;

    public MouseInputHandler (Map m, TileManager t) {
        this.map = m;
        this.tileM = t;
    }

    private void handleMouseClick(MouseEvent e) {
        if(map.minigame >= 0){
            if(validateClick(e)){
                System.out.println("Hello");
            }else{
                map.minigame = -1;
            }
        }else{
            int col = e.getX() / map.tileSize;
            int row = (e.getY() / map.tileSize)-1;

            if (col >= 0 && row >= 0 && col < map.screenCol && row < map.screenRow) {
                if(tileM.getTopLayer().getTileIndex(col, row) >= 88 && tileM.getTopLayer().getTileIndex(col, row) <= 93){
                    map.minigame = 0; // Enter minigame state
                    map.getDialogueManager().speak(0); // Show initial dialogue
                }
                
            }
        }
        map.repaint(); // Repaint to reflect changes
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        handleMouseClick(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    
    public boolean validateClick(MouseEvent e) {
        if(e.getX() >= 363 && e.getX() <= 544 && e.getY() >= 72 && e.getY() <= 119){
            return true;
        }
        return false;
    }
}
