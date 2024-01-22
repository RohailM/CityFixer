package inputs;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import tile.TileManager;

/**
 * The WindowListener class listens for window events,
 * particularly focusing on the window closing event to
 * save the current state of the game
 * @author Rohail Memon
 */
public class WindowListener extends WindowAdapter {
    
    TileManager tileM;
    String username;

    /**
     * Constructs a WindowListener for saving the game state
     * @param tileM	The TileManager associated with the current game map
     * @param username The username of the player.
     */
    public WindowListener(TileManager tileM, String username) {
        this.tileM = tileM;
        this.username = username;
    }
    
    /**
     * Invoked when the window is in the process of being closed
     * @param event The window event
     */
    @Override
    public void windowClosing(WindowEvent l) {
        System.out.println("Window is closing");
        String filePath = "res/saves/" + username + "_savedMap.txt";
        saveMapToFile(filePath);
    }

    /**
     * Saves the map data to a file for the current user
     * @param path The path where the map data will be saved
     */
    public void saveMapToFile(String path) {
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
    		for (int row = 0; row < 21; row++) {
    			for (int col = 0; col < 19; col++) {
    				int storedTileType = tileM.getTopLayer().getTileType(col, row);
    				writer.write(storedTileType + " ");
    			}
    			
    			writer.newLine();
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
            
}