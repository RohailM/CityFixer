package main;
import javax.swing.*;
import tile.TileManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Map extends JPanel {
	
	// SCREEN SETTINGS
	final int originalTileSize = 32;
	final int scale = 1;
	
	public final int tileSize = originalTileSize * scale;
	public final int screenCol = 10;
	public final int screenRow = 10;
	public final int screenWidth = screenCol * tileSize;
	public final int screenHeight = screenRow * tileSize;
	
	TileManager tileM = new TileManager(this);
	
	public Map() {
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                handleMouseClick(e);
            }
		});

	}
	
    private void handleMouseClick(MouseEvent e) {
        int col = e.getX() / tileSize;
        int row = e.getY() / tileSize;

        if (col >= 0 && row >= 0 && col < screenCol && row < screenRow) {
            // Change the tile type; for example, toggle between 0 and 1
            tileM.mapTileNum[col][row] = tileM.mapTileNum[col][row] == 0 ? 1 : 0;
            repaint(); // Repaint to reflect changes
        }
    }
    
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		tileM.draw(g2);
		g2.dispose();
	}
}
