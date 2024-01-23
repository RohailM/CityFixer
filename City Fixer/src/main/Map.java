package main;
import javax.swing.*;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.Arrays;

import minigame.MinigameManager;
import tile.TileManager;

/**
 * The Map class represents the main game area where the tilemap is displayed and can be interacted with
 * It handles the rendering of the game map and responding to completion of minigame by updating the tilemap
 * @author Rohail Memon
 *
 */
public class Map extends JPanel {
	
	// Screen setting constants
	final int originalTileSize = 32;
	final int scale = 1;
	
	// Screen dimensions based on tile size and scale
	public final int tileSize = originalTileSize * scale;
	public final int screenCol = 19;
	public final int screenRow = 21;
	public final int screenWidth = screenCol * tileSize;
	public final int screenHeight = screenRow * tileSize;

	// Game components
	TileManager tileM;;
	MinigameManager miniM = new MinigameManager(this);
	WelcomeDialogue welcomeMessage;
	public boolean sendWelcomeMessage = false;
	
	/**
	 * Constructs the game map and initializes game components based on whether game is a new game or saved state
	 * @param savedMapPath	The path to the saved map (if it exists)
	 * @param username	The username of the player
	 */
	public Map(String savedMapPath, String username) {
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		if (savedMapPath == null) {
			tileM = new TileManager(this, null);
			System.out.println("new user");
            sendWelcomeMessage = true;
			welcomeMessage = new WelcomeDialogue(this, tileSize*2, tileSize, screenWidth - (tileSize*4), tileSize*4, username);
			

            // Timer to hide the welcome message after 13.5 seconds (13500 milliseconds)
            Timer timer = new Timer(13500, e -> {
                sendWelcomeMessage = false;
                repaint(); // Repaint to update the display after the message is removed
            });
            timer.setRepeats(false); // So the timer only runs once
            timer.start();
		} else {
	        tileM = new TileManager(this, savedMapPath); 
	        System.out.println("returning user");
		}
	}
	
	/**
	 * Repairs a specific component of the city based on the part of the city that is clicked (and its minigame successfully completed)
	 * @param partOfCity The part of the city that needs to be repaired
	 */
	public void fixComponent(int partOfCity) {
		if (partOfCity == 0) {
			tileM.fixHouses();
		} else if (partOfCity == 1) {
			tileM.cleanWater();
		} else if (partOfCity == 2) {
			tileM.plantTrees();
		} else if (partOfCity == 3) {
			tileM.rehabilitateFactory();
		} else if (partOfCity == 4){
			tileM.repairCellTower();
		}
		
		repaint(); // Repaints the map to reflect the changes made
	}
	
	/**
	 * Paints each component of the game (tiles and dialogue boxes)
	 * @param g	The Graphics instance
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		tileM.draw(g2);
        if (sendWelcomeMessage) {
            welcomeMessage.drawWelcomeBox(g2);
        }
		int minigamesCompleted = 0;
		for(int i = 0; i < miniM.getMinigameCompleted().length; i++){
			if(miniM.getMinigameCompleted()[i]){
				minigamesCompleted++;
			}
			if(minigamesCompleted == miniM.getMinigameCompleted().length){
				miniM.gameOver(g);
			}
		}
		if(miniM.getMinigame() >= 0){
			miniM.setDialogueBox();
			miniM.getDialogueBox().drawBox(g2);
		}
		g2.dispose();
	}
}
