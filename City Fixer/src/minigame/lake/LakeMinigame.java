package minigame.lake;

import javax.swing.*;

import minigame.MinigameManager;

/* The MiniGame class serves as the main class for the Lake Cleanup Game
 * @author Wahab Sattar
 */

public class LakeMinigame {

    MinigameManager miniM;

    // Create a new JFrame for the game
    JFrame frame;

    WaterLitter cleanupPanel;

    public LakeMinigame(MinigameManager miniM){
        this.miniM = miniM;
        frame = new JFrame("Lake Cleanup Game");
        cleanupPanel = new WaterLitter(frame, miniM);
    }

    // Method to create and show the game GUI
    public void createAndShowGUI() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Display a tutorial message using JOptionPane
        JOptionPane.showMessageDialog(frame,
                "Welcome to the Lake Cleanup Game!\n" +
                        "Drag the dark green trash out of the water and into the black bin to clean the lake.",
                "Game Tutorial",
                JOptionPane.INFORMATION_MESSAGE);

        // Create an instance of the Garbage class (JPanel) for the game
        
        // Add the Garbage panel to the content pane of the frame
        frame.getContentPane().add(cleanupPanel);

        // Set the size of the frame
        frame.setSize(600, 500);
        
        // Center the frame on the screen
        frame.setLocationRelativeTo(null);
        
        // Make the frame visible
        frame.setVisible(true);

        frame.setResizable(false);
    }
}
