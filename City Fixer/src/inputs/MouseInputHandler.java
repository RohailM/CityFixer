package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Game;
import main.Map;
import states.DialogueState;
import tile.TileManager;

public class MouseInputHandler implements MouseListener{

    private Game game;
    private Map map;
    private TileManager tileM;

    public MouseInputHandler(Game game, Map map, TileManager tileM) {
        this.game = game;
        this.map = map;
        this.tileM = tileM;
    }

    private void handleMouseClick(MouseEvent e) {
        System.out.println("clicked");
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
                    System.out.println("clicked");
                    game.setState(new DialogueState(game, 0));
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
