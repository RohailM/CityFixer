package tile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The MapLayer class represents a single layer of the game map
 * It handles the storage and manipulation of tile data for a specific layer
 * @author Rohail Memon
 */
public class MapLayer {
    private int[][] layerData;

    /**
     * Constructs a MapLayer by loading the tile data from a specified file
     * @param filePath The file path of the map data
     * @param cols The number of columns in the map
     * @param rows The number of rows in the map
     */
    public MapLayer(String filePath, int cols, int rows) {
        layerData = new int[cols][rows];
        loadLayerData(filePath, cols, rows);
    }

    /**
     * Loads the tile data from a file into this layer
     * @param filePath The file path of the map data
     * @param cols The number of columns in the map
     * @param rows The number of rows in the map
     */
    private void loadLayerData(String filePath, int cols, int rows) {
        File file = new File(filePath);
        if (!file.exists()) {
            // Handles the case where the file does not exist.
            System.err.println("File not found: " + filePath);
            return;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            int row = 0;

            while ((line = br.readLine()) != null && row < rows) {
                String[] numbers = line.split(" ");
                
                for (int col = 0; col < cols; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    layerData[col][row] = num;
                }
                row++;
            }
            
        } catch (Exception e) {
            System.err.println("Error loading layer data from: " + filePath);
            e.printStackTrace();
        }
    }
 
    /**
     * Retrieves the tile type at a specified position
     * @param col The column of the tile
     * @param row The row of the tile
     * @return The tile type at the specified position
     */
    public int getTileType(int col, int row) {
        return layerData[col][row];
    }
    
    /**
     * Sets the tile type at a specified position
     * @param col The column of the tile to set
     * @param row The row of the tile to set
     * @return newTileType The new tile type
     */
    public void setTile(int col, int row, int newTileType) {
    	layerData[col][row] = newTileType;
    }
    
    /**
     * Replaces all occurrences of a specific tile with a new tile type.
     * @param oldTileType The tile type to replace
     * @param newTileType The new tile type
     */
    public void replaceTile(int oldTileType, int newTileType) {
    	for (int i = 0; i < layerData.length; i++) {
    		for (int j = 0; j < layerData[i].length; j++) {
    			if (layerData[i][j] == oldTileType) {
    				layerData[i][j] = newTileType;
    			}
    		}
    	}
    }
}
