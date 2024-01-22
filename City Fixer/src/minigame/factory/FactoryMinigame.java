package minigame.factory;

import javax.swing.*;

import minigame.MinigameManager;

/**
 * The GameFrame class represents the main frame of the Sustainability Factory Restoration Game.
 * This class extends the JFrame class from the Swing framework, serving as the primary window for the game's graphical user interface (GUI).
 * It holds the setup and configuration of the game window, including the title, size, default close operation, and the addition of the main game panel.
 */
public class FactoryMinigame extends JFrame {

    /**
     * Constructor for the GameFrame class.
     */
    public FactoryMinigame(MinigameManager miniM) {
        // Set the title of the frame
        setTitle("Sustainability Factory Restoration Game");

        // Set the default close operation to exit the application when the frame is closed
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        // Set the size of the frame to 800x600 pixels
        setSize(800, 600);

        // Create an instance of the GamePanel class, which represents the main panel of the game
        GamePanel gamePanel = new GamePanel(miniM);

        // Add the game panel to the frame
        add(gamePanel);

        // Set the location of the frame to be centered on the screen
        setLocationRelativeTo(null);

        // Set the frame to be visible
        setVisible(true);

        // Set the frame to be not resizable
        setResizable(false);
    }
}