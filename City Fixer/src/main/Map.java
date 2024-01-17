package main;
import javax.swing.*;

import minigame.DialogueManager;
import minigame.DialogueRenderer;
import tile.TileManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Map extends JPanel {
	
	// SCREEN SETTINGS
	final int originalTileSize = 32;
	private final int scale = 1;
	
	public final int tileSize = (originalTileSize * getScale());
	public final int screenCol = 19;
	public final int screenRow = 21;
	public final int screenWidth = screenCol * tileSize;
	public final int screenHeight = screenRow * tileSize;
	
	private TileManager tileM = new TileManager(this);
	private DialogueManager dialogueManager = new DialogueManager();
	private DialogueRenderer dialogueRenderer = new DialogueRenderer(this, dialogueManager);
	
	public int minigame = -1;
	//0 house
	//1 Water
	//2 tree
	//3 garbage 
	//4 factory
	//5 crops 
	//6 cell tower
	
	public Map() {
		setPreferredSize(new Dimension(screenWidth, screenHeight));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		getTileM().draw(g2);
        if (minigame >= 0) { // If in a minigame state, draw the dialogue box.
            dialogueRenderer.drawBox(g2);
        }
		g2.dispose();
	}

	public TileManager getTileM() {
		return tileM;
	}

	public int getScale() {
		return scale;
	}
	
    public DialogueManager getDialogueManager() {
        return dialogueManager;
    }
}
