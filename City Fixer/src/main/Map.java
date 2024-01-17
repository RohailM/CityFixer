package main;
import javax.swing.*;

import minigame.DialogueManager;
import minigame.DialogueRenderer;
import states.DialogueState;
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
	
	private TileManager tileM;
	private DialogueManager dialogueManager;
	private DialogueRenderer dialogueRenderer;
	
	/*
	 * 	0 house
	 *  1 Water
	 *  2 tree
	 *  3 garbage
	 *  4 factory
	 * 	5 crops 
	 *  6 cell tower
	*/
	public int minigame = -1;
	
    private Game game; 

    public Map(Game game) {
    	this.game = game;
    	this.tileM = new TileManager(this);
    	this.dialogueManager = new DialogueManager();
    	this.dialogueRenderer = new DialogueRenderer(this, dialogueManager);
        setPreferredSize(new Dimension(screenWidth, screenHeight));
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		getTileM().draw(g2);
		
        if (game.getGameState() instanceof DialogueState) {
            dialogueRenderer.drawBox(g2);
        }

		g2.dispose();
	}

	public TileManager getTileM() {
		return tileM;
	}
	
	public Dimension getSizeOfFrame() {
		return new Dimension(screenWidth, screenHeight);
	}
	
	public int getScale() {
		return scale;
	}
	
    public DialogueManager getDialogueManager() {
        return dialogueManager;
    }
}
