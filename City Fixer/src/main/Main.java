package main;

import javax.swing.SwingUtilities;

/**
 * This is the main entry point for our game
 * It initializes the game by setting up the start screen and passing the necessary callbacks for the game to start
 * @author Rohail Memon
 */
public class Main {
    /**
     * The main method initiates the game by setting up the start screen on the Event Dispatch Thread.
     * @param args Command line arguments (not used).
     */
	public static void main(String[] args) {
		// lambda is from chatGPT
        SwingUtilities.invokeLater(() -> {
            new StartScreen((username, hasSavedMap) -> {
                Frame window = new Frame("City Fixer", 622, 685, false, username, hasSavedMap);
                window.requestFocusInWindow();
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
            });
        });
	}
}
