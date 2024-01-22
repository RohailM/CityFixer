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
	public final int screenCol = 19;
	public final int screenRow = 21;
	public final int screenWidth = screenCol * tileSize;
	public final int screenHeight = screenRow * tileSize;
	
	TileManager tileM = new TileManager(this);
	
	public Map() {
		setPreferredSize(new Dimension(screenWidth, screenHeight));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		tileM.draw(g2);
		g2.dispose();
	}
	
}
