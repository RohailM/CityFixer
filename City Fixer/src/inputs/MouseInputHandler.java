package inputs;

import main.Map;
import tile.TileManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputHandler implements MouseListener {
	private Map map;
	private TileManager tileM;
	
	public MouseInputHandler(Map m, TileManager t) {
		this.map = m;
		this.tileM = t;
	}
	
	private void handleMouseClick(MouseEvent e) {
		
		int col = e.getX() / map.tileSize;
		int row = (e.getY() / map.tileSize) - 1;
		
		if (col >= 0 && row >= 0 && col < map.screenCol && row < map.screenRow) {
	 		if (tileM.getTopLayer().getTileIndex(col, row) == 47) {
				System.out.println("Tile 47 clicked");
			}
	 		map.repaint();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		handleMouseClick(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
