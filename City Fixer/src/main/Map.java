package main;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.Arrays;

import minigame.MinigameManager;
import tile.TileManager;

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
	MinigameManager miniM = new MinigameManager(this);
	
	public Map() {
		setPreferredSize(new Dimension(screenWidth, screenHeight));
	}
	
    /*public void showWelcomeMessage(String username) {
        // Create a modal JDialog to show the message
        JDialog welcomeDialog = new JDialog();
        welcomeDialog.setModal(true);
        welcomeDialog.setAlwaysOnTop(true);
        welcomeDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        welcomeDialog.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("Welcome " + username + "!", JLabel.CENTER);
        welcomeDialog.add(messageLabel, BorderLayout.CENTER);

        // Size the dialog and position it at the center of the screen
        welcomeDialog.setSize(300, 200);
        welcomeDialog.setLocationRelativeTo(null); // Center the dialog

        // Create a Timer to dispose of the dialog after 5 seconds
        Timer timer = new Timer(5000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                welcomeDialog.dispose();
            }
        });

        timer.setRepeats(false); // Ensure the timer only runs once
        timer.start(); // Start the timer

        welcomeDialog.setVisible(true); // Show the dialog
    }*/
	
	public void fixComponent(int partOfCity) {
		if (partOfCity == 0) {
			tileM.fixHouses();
		} else if (partOfCity == 1) {
			tileM.cleanWater();
		} else if (partOfCity == 2) {
			tileM.plantTrees();
		}
		
		repaint();
	}
    
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		tileM.draw(g2);
		if(miniM.getMinigame() >= 0){
			miniM.setDialogueBox();
			miniM.getDialogueBox().drawBox(g2);
		}
		g2.dispose();
	}
}
